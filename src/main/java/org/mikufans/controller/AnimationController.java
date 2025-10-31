package org.mikufans.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Animation;
import org.mikufans.entity.MyPage;
import org.mikufans.service.AnimationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 动画控制器
 * 处理动画实体的HTTP请求
 */
@Tag(name = "动画管理", description = "动画作品相关接口")
@RestController
@RequestMapping("/api/animations")
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
            @Parameter(description = "动画ID") @PathVariable Long id) {
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
    public ResponseEntity<Void> createAnimations(
            @Parameter(description = "动画信息列表") @RequestBody List<Animation> animations) {
        animationService.saveBatchAnimations(animations);
        return ResponseEntity.ok().build();
    }

    /**
     * 更新动画
     *
     * @param animation 动画信息
     * @return 更新后的动画
     */
    @Operation(summary = "更新动画", description = "更新动画信息")
    @PutMapping
    public ResponseEntity<Animation> updateAnimation(
            @Parameter(description = "动画信息") @RequestBody Animation animation) {
        if (animationService.getAnimationById(animation.getId()) == null) {
            return ResponseEntity.notFound().build();
        }
        boolean updated = animationService.updateAnimation(animation);
        if (updated) {
            return ResponseEntity.ok(animation);
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
            @Parameter(description = "动画ID") @PathVariable Long id) {
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
}
