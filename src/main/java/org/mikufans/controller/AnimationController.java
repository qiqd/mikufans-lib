package org.mikufans.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Animation;
import org.mikufans.entity.base.MyPage;
import org.mikufans.entity.request.UpdateFieldById;
import org.mikufans.entity.response.TitleListResponse;
import org.mikufans.entity.response.TitleResponse;
import org.mikufans.entity.response.UpdateResponse;
import org.mikufans.service.AnimationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 动画控制器
 * 处理动画实体的HTTP请求
 */
@Tag(name = "动画管理", description = "管理员接口，用于管理动画作品")
@RestController
@RequestMapping("/api/admin/animations")
@RequiredArgsConstructor
public class AnimationController {

  private final AnimationService animationService;

  /**
   * 获取所有动画
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页动画数据
   */
  @Operation(summary = "获取所有动画", description = "分页查询所有动画作品")
  @GetMapping
  public ResponseEntity<MyPage<Animation>> getAllAnimations(
          @Parameter(description = "页码，默认1") @RequestParam(defaultValue = "1") Integer page,
          @Parameter(description = "每页大小，默认10") @RequestParam(defaultValue = "10") Integer size) {
    MyPage<Animation> animations = animationService.getAllAnimations(page, size);
    return ResponseEntity.ok(animations);
  }

  /**
   * 获取动画详情
   *
   * @param id 动画ID
   * @return 动画详情
   */
  @Operation(summary = "获取动画详情", description = "根据ID获取动画详细信息")
  @GetMapping("/{id}")
  public ResponseEntity<Animation> getAnimationById(
          @Parameter(description = "动画ID") @PathVariable String id) {
    Animation animation = animationService.getAnimationById(id);
    if (animation == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(animation);
  }

  /**
   * 批量创建动画
   *
   * @param animations 动画列表
   * @return 创建结果
   */
  @Operation(summary = "批量创建动画", description = "批量新增动画作品信息")
  @PostMapping
  public ResponseEntity<String> createAnimations(
          @Parameter(description = "动画信息列表") @RequestBody List<Animation> animations) {
    animationService.saveBatchAnimations(animations);
    return ResponseEntity.ok("批量创建动画成功，最后一条创建时间为：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }

  /**
   * 更新动画
   *
   * @param animation 动画信息
   * @return 更新后的动画
   */
  @Operation(summary = "更新动画", description = "更新动画信息")
  @PutMapping
  public ResponseEntity<String> updateAnimation(
          @Parameter(description = "动画信息") @RequestBody Animation animation) {
    if (animationService.getAnimationById(animation.getId()) == null) {
      return ResponseEntity.notFound().build();
    }
    boolean updated = animationService.updateAnimation(animation);
    if (updated) {
      return ResponseEntity.ok("更新动画成功，更新时间为：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 删除动画
   *
   * @param id 动画ID
   * @return 删除结果
   */
  @Operation(summary = "删除动画", description = "根据ID删除动画作品")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAnimation(
          @Parameter(description = "动画ID") @PathVariable String id) {
    boolean deleted = animationService.deleteAnimation(id);
    if (!deleted) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.noContent().build();
  }

  /**
   * 根据动画制作公司查询动画
   *
   * @param studio 动画制作公司
   * @return 动画列表
   */
  @Operation(summary = "根据动画制作公司查询", description = "查询指定动画制作公司的所有动画")
  @GetMapping("/by-studio/{studio}")
  public ResponseEntity<List<Animation>> getAnimationsByStudio(
          @Parameter(description = "动画制作公司名称") @PathVariable String studio) {
    List<Animation> animations = animationService.getAnimationsByStudio(studio);
    return ResponseEntity.ok(animations);
  }

  /**
   * 根据导演查询动画
   *
   * @param director 导演
   * @return 动画列表
   */
  @Operation(summary = "根据导演查询", description = "查询指定导演的所有动画")
  @GetMapping("/by-director/{director}")
  public ResponseEntity<List<Animation>> getAnimationsByDirector(
          @Parameter(description = "导演名称") @PathVariable String director) {
    List<Animation> animations = animationService.getAnimationsByDirector(director);
    return ResponseEntity.ok(animations);
  }

  /**
   * 根据播放平台查询动画
   *
   * @param platform 播放平台
   * @return 动画列表
   */
  @Operation(summary = "根据播放平台查询", description = "查询指定播放平台的所有动画")
  @GetMapping("/by-platform/{platform}")
  public ResponseEntity<List<Animation>> getAnimationsByBroadcastPlatform(
          @Parameter(description = "播放平台名称") @PathVariable String platform) {
    List<Animation> animations = animationService.getAnimationsByBroadcastPlatform(platform);
    return ResponseEntity.ok(animations);
  }

  /**
   * 根据名称查询动画
   *
   * @param name 动画名称
   * @return 动画列表
   */
  @Operation(summary = "根据名称查询", description = "查询指定名称的所有动画")
  @GetMapping("/by-name")
  public ResponseEntity<List<Animation>> getAnimationsByName(
          @Parameter(description = "动画名称") @RequestParam("name") String name) {
    List<Animation> animations = animationService.getAnimationsByTitle(name);
    return ResponseEntity.ok(animations);
  }

  /**
   * 更新动画字符串数组字段
   *
   * @param updateFieldById 更新字段请求
   * @return 更新结果
   */
  @Operation(summary = "批量更新动画字符串数组字段", description = "根据ID批量更新动画的字符串数组字段")
  @PutMapping("/array-field")
  public ResponseEntity<String> updateArrayFieldByIds(
          @Parameter(description = "字段名，例如：originalTitle、englishTitle") @RequestParam("field") String field,
          @Parameter(description = "更新字段请求") @RequestBody List<UpdateFieldById<List<String>>> updateFieldById) {
    UpdateResponse response = animationService.bulkUpdateByIdsAndField(field, updateFieldById);
    if (response.getSuccess()) {
      return ResponseEntity.ok(response.toString());
    }
    return ResponseEntity.badRequest().body("更新其他标题失败，更新时间为：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }

  /**
   * 更新动画字符串字段
   *
   * @param updateFieldById 更新字段请求
   * @return 更新结果
   */
  @Operation(summary = "批量更新动画字符串字段", description = "根据ID批量更新动画的字符串字段")
  @PutMapping("/string-field")
  public ResponseEntity<String> updateStringFieldByIds(
          @Parameter(description = "字段名，例如：originalTitle、englishTitle") @RequestParam("field") String field,
          @Parameter(description = "更新字段请求") @RequestBody List<UpdateFieldById<String>> updateFieldById) {
    UpdateResponse response = animationService.bulkUpdateByIdsAndField(field, updateFieldById);
    if (response.getSuccess()) {
      return ResponseEntity.ok(response.toString());
    }
    return ResponseEntity.badRequest().body("更新其他标题失败，更新时间为：" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
  }

  /**
   * 根据年份查询动画
   *
   * @param start 开始日期
   * @param end   结束日期
   * @param page  页码
   * @param size  每页大小
   * @return 动画标题列表
   */
  @Operation(summary = "根据年份查询", description = "查询指定年份的所有动画的所有标题,只包含标题、原始标题、英文标题")
  @GetMapping("/by-year")
  public ResponseEntity<List<TitleResponse>> getAnimationsByYear(
          @Parameter(description = "开始日期") @RequestParam("start") LocalDate start,
          @Parameter(description = "结束日期") @RequestParam(value = "end", required = false) LocalDate end,
          @Parameter(description = "页码") @RequestParam(value = "page", defaultValue = "1") Integer page,
          @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") Integer size) {
    if (end == null) {
      end = start.plusYears(1).withMonth(1).withDayOfMonth(1);
    }
    Page<Animation> animations = animationService.getAnimationsByYear(start, end, page, size);
    List<TitleResponse> titleResponses = animations.getContent().stream()
            .map(animation -> new TitleResponse(animation.getId(), animation.getTitle(), animation.getOriginalTitle(), animation.getEnglishTitle(), animation.getReleaseDate()))
            .toList();
    return ResponseEntity.ok(titleResponses);
  }

  /**
   * 根据年份查询动画
   *
   * @param start 开始日期
   * @param end   结束日期
   * @param page  页码
   * @param size  每页大小
   * @return 动画标题列表
   */
  @Operation(summary = "根据年份查询-full-title", description = "查询指定年份的所有动画的所有标题，包括标题、原始标题、英文标题和其他标题")
  @GetMapping("/by-year-title")
  public ResponseEntity<List<TitleListResponse>> getAnimationTitleListByYear(
          @Parameter(description = "开始日期") @RequestParam("start") LocalDate start,
          @Parameter(description = "结束日期") @RequestParam(value = "end", required = false) LocalDate end,
          @Parameter(description = "页码") @RequestParam(value = "page", defaultValue = "1") Integer page,
          @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") Integer size) {
    if (end == null) {
      end = start.plusYears(1).withMonth(1).withDayOfMonth(1);
    }
    Page<Animation> animations = animationService.getAnimationsByYear(start, end, page, size);
    List<TitleListResponse> titleResponses = animations.getContent().stream()
            .map(animation -> {
              TitleListResponse response = new TitleListResponse();
              response.setId(animation.getId());
              ArrayList<String> list = new ArrayList<>();
              list.add(animation.getTitle());
              list.add(animation.getOriginalTitle());
              list.add(animation.getEnglishTitle());
              list.addAll(animation.getOtherTitle());
              response.setTitles(list);
              return response;
            })
            .toList();
    return ResponseEntity.ok(titleResponses);
  }

  /**
   * 更新动画封面URL
   *
   * @param titleListResponses 动画标题列表请求
   * @return 更新结果
   */
  @Operation(summary = "更新动画封面URL", description = "根据动画标题列表请求更新动画的封面URL")
  @PostMapping("/by-year-title")
  public ResponseEntity<Map<String, Integer>> postAnimationTitleList(@RequestBody List<TitleListResponse> titleListResponses) {
    Map<String, Integer> map = animationService.updateImageBatch(titleListResponses);
    return ResponseEntity.ok(map);
  }
}
