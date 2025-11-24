package org.mikufans.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Animation;
import org.mikufans.entity.response.TitleResponse;
import org.mikufans.entity.response.UpdateResponse;
import org.mikufans.service.AnimationService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController("/temp")
@RequiredArgsConstructor
public class TempController {
  private final AnimationService animationService;

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
   * 批量查询动画标题
   *
   * @param start 开始日期
   * @param end   结束日期
   * @param page  页码
   * @param size  每页大小
   * @return 动画标题列表
   */
  @Operation(summary = "批量查询动画标题", description = "查询指定条件内的所有动画的所有标题，包括标题、原始标题、英文标题和其他标题")
  @GetMapping("/by-option")
  public ResponseEntity<?> getAnimationTitleListByOption(
          @Parameter(description = "是否展示全部信息") @RequestParam(value = "showAll", defaultValue = "false") Boolean showAll,
          @Parameter(description = "开始日期") @RequestParam(value = "start", required = false) LocalDate start,
          @Parameter(description = "结束日期") @RequestParam(value = "end", required = false) LocalDate end,
          @Parameter(description = "页码") @RequestParam(value = "page", defaultValue = "1") Integer page,
          @Parameter(description = "每页大小") @RequestParam(value = "size", defaultValue = "10") Integer size) {
    Page<Animation> animations = animationService.getAnimationOptionally(start, end, page, size);
    if (showAll) {
      return ResponseEntity.ok(animations.getContent());
    }
    List<TitleResponse> titleResponses = animations.getContent().stream()
            .map(animation -> {
              TitleResponse response = new TitleResponse();
              response.setId(animation.getId());
              response.setTitle(animation.getTitle());
              response.setTitleCn(animation.getTitleCn());
              response.setTitleEn(animation.getTitleEn());
              return response;
            })
            .toList();
    return ResponseEntity.ok(titleResponses);
  }

  /**
   * 完善番剧信息
   *
   * @param titleListResponses 动画标题列表请求
   * @return 更新结果
   */
  @Operation(summary = "完善番剧信息")
  @PostMapping("/fill")
  public ResponseEntity<UpdateResponse> updateAnimationBatchByTitles(
          @Parameter(description = "动画标题列表请求") @RequestBody List<TitleResponse> titleListResponses) throws Exception {
    UpdateResponse response = animationService.updateByTitles(titleListResponses);
    return ResponseEntity.badRequest().body(response);
  }
}
