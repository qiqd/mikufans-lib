package org.mikufans.service.impl;

import com.mongodb.bulk.BulkWriteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mikufans.entity.Animation;
import org.mikufans.entity.base.Image;
import org.mikufans.entity.base.MyPage;
import org.mikufans.entity.request.UpdateFieldById;
import org.mikufans.entity.response.TitleListResponse;
import org.mikufans.entity.response.UpdateResponse;
import org.mikufans.repository.AnimationRepository;
import org.mikufans.service.AnimationService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnimationServiceImpl implements AnimationService {
  private final AnimationRepository animationRepository;
  private final MongoTemplate mongoTemplate;
  private final HtmlParser htmlParser;


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
    return animationRepository.findAnimationByDirectors(List.of(director));
  }

  @Override
  public List<Animation> getAnimationsByBroadcastPlatform(String platform) {
    return animationRepository.findAnimationByBroadcastPlatform(platform);
  }

  @Override
  public List<Animation> getAnimationsByTitle(String name) {
    // 首先尝试在中文标题中搜索
    List<Animation> results = animationRepository.findAnimationByTitleContaining(name);

    // 如果中文标题没有结果，尝试搜索原名
    if (results.isEmpty()) {
      results = animationRepository.findAnimationByOriginalTitleContaining(name);
    }

    // 如果原名也没有结果，尝试搜索英文标题
    if (results.isEmpty()) {
      results = animationRepository.findAnimationByEnglishTitleContaining(name);
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
      return new UpdateResponse(true, (long) updateFieldById.size(),
              (long) result.getMatchedCount(),
              (long) result.getModifiedCount());
    } catch (Exception e) {
      log.error("批量更新动画字段失败：{}", e.getMessage());
      return new UpdateResponse(false, (long) updateFieldById.size(), 0L, 0L);
    }
  }

  @Override
  public Page<Animation> getAnimationsByYear(LocalDate start, LocalDate end, Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "releaseDate"));

    return animationRepository.findByReleaseDateBetween(start, end, pageable);
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
            Image image = Image.builder()
                    .small(coverUrl.substring(0, coverUrl.indexOf("?")))
                    .medium(coverUrl)
                    .build();
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
}
