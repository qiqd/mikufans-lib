package org.mikufans.service.impl;

import com.mongodb.bulk.BulkWriteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mikufans.entity.Animation;
import org.mikufans.entity.base.Image;
import org.mikufans.entity.base.Item;
import org.mikufans.entity.base.MyPage;
import org.mikufans.entity.base.Person;
import org.mikufans.entity.request.UpdateFieldById;
import org.mikufans.entity.response.TitleListResponse;
import org.mikufans.entity.response.TitleResponse;
import org.mikufans.entity.response.UpdateResponse;
import org.mikufans.parser.DoubanParse;
import org.mikufans.repository.AnimationRepository;
import org.mikufans.service.AnimationService;
import org.mikufans.service.playwright.WebScraperService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tetofans.enumeration.AcgType;
import org.tetofans.util.HtmlParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimationServiceImpl implements AnimationService {
  private final AnimationRepository animationRepository;
  private final MongoTemplate mongoTemplate;
  private final HtmlParser htmlParser;
  private final WebScraperService webScraperService;
  private final DoubanParse doubanParse;

  @Override
  public MyPage<Animation> getAllAnimations(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    org.springframework.data.domain.Page<Animation> animationPage = animationRepository.findAll(pageable);
    List<Animation> records = animationPage.getContent();
    return new MyPage<>(page, size.longValue(), (long) animationPage.getTotalPages(), animationPage.getTotalElements(), records);
  }

  @Override
  public Animation getAnimationById(String id) {
    return animationRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveBatchAnimations(List<Animation> animations) {
    animations.forEach(animation -> {
      animation.setId(null);
      animation.setCreatedAt(LocalDateTime.now());
    });
    animationRepository.saveAll(animations);
  }

  @Override
  public boolean updateAnimation(Animation animation) {
    animation.setUpdatedAt(LocalDateTime.now());
    animationRepository.save(animation);
    return true;
  }

  @Override
  public boolean deleteAnimation(String id) {
    animationRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Animation> getAnimationsByStudio(String studio) {
    return animationRepository.findAnimationByAnimationStudio(studio);
  }

  @Override
  public List<Animation> getAnimationsByDirector(String director) {
    Person person = new Person();
    person.setName(director);
    return List.of();
  }

  @Override
  public List<Animation> getAnimationsByBroadcastPlatform(String platform) {
    return animationRepository.findAnimationByBroadcastPlatform(platform);
  }

  @Override
  public List<Animation> getAnimationsByTitle(String name) {
    // 首先尝试在中文标题中搜索
    List<Animation> results = animationRepository.findAnimationByTitleCnContaining(name);

    // 如果中文标题没有结果，尝试搜索原名
    if (results.isEmpty()) {
      results = animationRepository.findAnimationByTitleContaining(name);
    }

    // 如果原名也没有结果，尝试搜索英文标题
    if (results.isEmpty()) {
      results = animationRepository.findAnimationByTitleEnContaining(name);
    }
    return results;
  }

  @Override
  public <T> UpdateResponse bulkUpdateByIdsAndField(String fieldName, List<UpdateFieldById<T>> updateFieldById) {
    try {
      BulkOperations bulkOps = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Animation.class);
      for (UpdateFieldById<T> fieldUpdate : updateFieldById) {
        Query query = new Query(Criteria.where("_id").is(fieldUpdate.getId()));
        Update update = new Update().set(fieldName, fieldUpdate.getField());
        bulkOps.updateOne(query, update);
      }
      BulkWriteResult result = bulkOps.execute();
      return new UpdateResponse(true, (long) updateFieldById.size(), (long) result.getMatchedCount(), (long) result.getModifiedCount());
    } catch (Exception e) {
      log.error("批量更新动画字段失败：{}", e.getMessage());
      return new UpdateResponse(false, (long) updateFieldById.size(), 0L, 0L);
    }
  }

  @Override
  public Page<Animation> getAnimationOptionally(LocalDate start, LocalDate end, Integer page, Integer size) {
    Query query = new Query();
    PageRequest request = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "releaseDate"));
    query.with(request);
    if (end == null && start != null) {
      end = start.plusYears(1).withMonth(1).withDayOfMonth(1);
    }
    if (start != null) {
      return animationRepository.findByReleaseDateBetween(start, end, request);
    }
    return animationRepository.findAll(request);
  }

  @Override
  public Map<String, Integer> updateImageBatch(List<TitleListResponse> titleListResponses) {
    Map<String, Integer> map = new HashMap<>();
    map.put("expected", titleListResponses.size());
    map.put("success", 0);
    titleListResponses.forEach(titleListResponse -> {
      List<String> titles = titleListResponse.getTitles();
      for (String title : titles) {
        try {
          String coverUrl = htmlParser.parseCoverUrl(title, AcgType.TV);
          if (!coverUrl.isBlank()) {
            Query query = new Query(Criteria.where("_id").is(titleListResponse.getId()));
            Image image = Image.builder().small(coverUrl.substring(0, coverUrl.indexOf("?"))).medium(coverUrl).build();
            Update update = new Update().set("coverUrl", image);
            mongoTemplate.updateFirst(query, update, Animation.class);
            map.merge("success", 1, Integer::sum);
            break;
          }
        } catch (Exception e) {
          log.error("解析动画封面URL失败：{}, 标题：{}", e.getMessage(), title);
          break;
        }
      }
    });
    return map;
  }

  @Override
  public UpdateResponse updateByTitles(List<TitleResponse> titleListResponses) throws Exception {
    String BASE_URL = "https://movie.douban.com";
    Long successCount = 0L;

    // 请求间隔控制配置
    final long MIN_INTERVAL_MS = 5000; // 最小间隔8秒
    final long MAX_JITTER_MS = 3000;   // 最大随机抖动3秒
    long lastRequestTime = 0;

    Random random = new Random();

    for (int i = 0; i < titleListResponses.size(); i++) {
      TitleResponse item = titleListResponses.get(i);
      long start = System.currentTimeMillis();

      try {
        // 确保请求间隔
        long currentTime = System.currentTimeMillis();
        long timeSinceLastRequest = currentTime - lastRequestTime;
        long requiredWait = MIN_INTERVAL_MS - timeSinceLastRequest;

        if (requiredWait > 0 && lastRequestTime > 0) {
          log.info("等待 {} 毫秒以确保请求间隔", requiredWait);
          Thread.sleep(requiredWait);
        }

        // 执行搜索请求
        String searchUrl = "https://search.douban.com/movie/subject_search?search_text=";
        String html = webScraperService.fetchRenderedHtml(searchUrl + item.getTitleCn());
        List<Item> idItem = doubanParse.getId(html, item.getTitleCn());

        // 检查数据库中是否已存在
        List<Animation> record = animationRepository.findBySubIdIn(
                idItem.stream().map(f -> f.getId().toString()).toList()
        );
        Map<String, Animation> animationMap = record.stream()
                .collect(Collectors.toMap(Animation::getSubId, v -> v));
        List<Item> target = idItem.stream()
                .filter(f -> !animationMap.containsKey(f.getId().toString()))
                .toList();

        // 记录当前请求时间
        lastRequestTime = System.currentTimeMillis();

        // 处理每个目标项
        ArrayList<Animation> animations = new ArrayList<>();
        for (int j = 0; j < target.size(); j++) {
          Item subject = target.get(j);

          // 每个详情页也添加间隔
          if (j > 0) {
            long detailInterval = MIN_INTERVAL_MS / 2 + random.nextInt(2000); // 4-6秒间隔
            Thread.sleep(detailInterval);
          }

          try {
            // 获取详情页
            String detailHtml = webScraperService.fetchRenderedHtml(
                    "https://movie.douban.com/subject/" + subject.getId() + "/"
            );
            Animation detail = doubanParse.getDetail(detailHtml);
            detail.setSubId(subject.getId().toString());

            // 获取职员表
            Thread.sleep(2000 + random.nextInt(2000)); // 2-4秒间隔
            String staffHtml = webScraperService.fetchRenderedHtml(
                    "https://movie.douban.com/subject/" + subject.getId() + "/celebrities"
            );
            Animation staff = doubanParse.getStaff(staffHtml);

            // 合并数据
            detail.setAnimator(staff.getAnimator());
            detail.setActor(staff.getActor());
            detail.setProducer(staff.getProducer());
            detail.setWriter(staff.getWriter());
            detail.setMusician(staff.getMusician());
            detail.setDirector(staff.getDirector());
            detail.setCreatedAt(LocalDateTime.now());

            animations.add(detail);
            successCount++;

          } catch (Exception e) {
            log.error("解析番剧详情失败，标题: {}, ID: {}", subject.getTitle(), subject.getId(), e);
            // 出错时增加额外等待时间
            Thread.sleep(5000 + random.nextInt(3000));
          }
        }

        // 批量保存
        if (!animations.isEmpty()) {
          animationRepository.saveAll(animations);
        }

        // 批次间间隔（最后一个不等待）
        if (i < titleListResponses.size() - 1) {
          long batchInterval = MIN_INTERVAL_MS + random.nextLong(MAX_JITTER_MS) + random.nextLong(MAX_JITTER_MS, 2 * MAX_JITTER_MS);
          log.info("批次 {} 完成，等待 {} 毫秒后继续", i + 1, batchInterval);
          Thread.sleep(batchInterval);
        }

      } catch (Exception e) {
        log.error("处理番剧失败，标题: {}", item.getTitleCn(), e);
        // 出错时增加等待时间
        Thread.sleep(3000 + random.nextInt(4000));
      }

      log.info("解析:{} 完成，消耗时间：{} ms", item.getTitleCn(), System.currentTimeMillis() - start);
    }

    return new UpdateResponse(true, (long) titleListResponses.size(), successCount, successCount);
  }

}
