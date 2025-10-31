package org.mikufans.controller;

import org.mikufans.entity.AnimationToGenre;
import org.mikufans.entity.MyPage;
import org.mikufans.service.AnimationToGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * AnimationToGenre控制器类
 * 处理动画与类型关联的HTTP请求
 */
@RestController
@RequestMapping("/api/animation-genres")
public class AnimationToGenreController {
    
    @Autowired
    private AnimationToGenreService animationToGenreService;
    
    /**
     * 获取所有动画-类型关联关系（分页）
     * @param page 页码，默认1
     * @param size 每页大小，默认10
     * @return 分页关联关系数据
     */
    @GetMapping
    public ResponseEntity<MyPage<AnimationToGenre>> getAllAnimationToGenres(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        MyPage<AnimationToGenre> data = animationToGenreService.getAllAnimationToGenres(page, size);
        return ResponseEntity.ok(data);
    }
    
    /**
     * 根据动画ID获取类型ID列表
     * @param animationId 动画ID
     * @return 类型ID列表
     */
    @GetMapping("/animation/{animationId}")
    public ResponseEntity<List<Long>> getGenreIdsByAnimationId(@PathVariable Long animationId) {
        List<Long> genreIds = animationToGenreService.getGenreIdsByAnimationId(animationId);
        return ResponseEntity.ok(genreIds);
    }
    
    /**
     * 根据类型ID获取动画ID列表
     * @param genreId 类型ID
     * @return 动画ID列表
     */
    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Long>> getAnimationIdsByGenreId(@PathVariable Long genreId) {
        List<Long> animationIds = animationToGenreService.getAnimationIdsByGenreId(genreId);
        return ResponseEntity.ok(animationIds);
    }
    
    /**
     * 为动画添加类型
     * @param animationId 动画ID
     * @param genreId 类型ID
     * @return 响应状态
     */
    @PostMapping
    public ResponseEntity<Void> addGenreToAnimation(
            @RequestParam Long animationId, 
            @RequestParam Long genreId) {
        if (animationToGenreService.addGenreToAnimation(animationId, genreId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build(); // 可能已存在
    }
    
    /**
     * 批量为动画添加类型
     * @param animationId 动画ID
     * @param genreIds 类型ID列表
     * @return 响应状态
     */
    @PostMapping("/batch")
    public ResponseEntity<Void> addGenresToAnimation(
            @RequestParam Long animationId, 
            @RequestBody List<Long> genreIds) {
        if (animationToGenreService.addGenresToAnimation(animationId, genreIds)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
    
    /**
     * 删除动画的类型关联
     * @param animationId 动画ID
     * @param genreId 类型ID
     * @return 响应状态
     */
    @DeleteMapping
    public ResponseEntity<Void> removeGenreFromAnimation(
            @RequestParam Long animationId, 
            @RequestParam Long genreId) {
        if (animationToGenreService.removeGenreFromAnimation(animationId, genreId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 清空动画的所有类型关联
     * @param animationId 动画ID
     * @return 删除的数量
     */
    @DeleteMapping("/animation/{animationId}")
    public ResponseEntity<Integer> clearAnimationGenres(@PathVariable Long animationId) {
        int deletedCount = animationToGenreService.clearAnimationGenres(animationId);
        return ResponseEntity.ok(deletedCount);
    }
}
