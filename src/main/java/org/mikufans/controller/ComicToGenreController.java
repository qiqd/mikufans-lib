package org.mikufans.controller;

import org.mikufans.entity.MyPage;
import org.mikufans.service.ComicToGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ComicToGenre控制器类
 * 处理漫画与类型关联的HTTP请求
 */
@RestController
@RequestMapping("/api/comic-genres")
public class ComicToGenreController {
    
    @Autowired
    private ComicToGenreService comicToGenreService;
    
    /**
     * 获取所有漫画-类型关联关系（分页）
     * @param page 页码，默认1
     * @param size 每页大小，默认10
     * @return 分页关联关系数据
     */
    @GetMapping
    public ResponseEntity<MyPage<ComicToGenre>> getAllComicToGenres(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        MyPage<ComicToGenre> data = comicToGenreService.getAllComicToGenres(page, size);
        return ResponseEntity.ok(data);
    }
    
    /**
     * 根据漫画ID获取类型ID列表
     * @param comicId 漫画ID
     * @return 类型ID列表
     */
    @GetMapping("/comic/{comicId}")
    public ResponseEntity<List<Long>> getGenreIdsByComicId(@PathVariable Long comicId) {
        List<Long> genreIds = comicToGenreService.getGenreIdsByComicId(comicId);
        return ResponseEntity.ok(genreIds);
    }
    
    /**
     * 根据类型ID获取漫画ID列表
     * @param genreId 类型ID
     * @return 漫画ID列表
     */
    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Long>> getComicIdsByGenreId(@PathVariable Long genreId) {
        List<Long> comicIds = comicToGenreService.getComicIdsByGenreId(genreId);
        return ResponseEntity.ok(comicIds);
    }
    
    /**
     * 为漫画添加类型
     * @param comicId 漫画ID
     * @param genreId 类型ID
     * @return 响应状态
     */
    @PostMapping
    public ResponseEntity<Void> addGenreToComic(
            @RequestParam Long comicId, 
            @RequestParam Long genreId) {
        if (comicToGenreService.addGenreToComic(comicId, genreId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build(); // 可能已存在
    }
    
    /**
     * 批量为漫画添加类型
     * @param comicId 漫画ID
     * @param genreIds 类型ID列表
     * @return 响应状态
     */
    @PostMapping("/batch")
    public ResponseEntity<Void> addGenresToComic(
            @RequestParam Long comicId, 
            @RequestBody List<Long> genreIds) {
        if (comicToGenreService.addGenresToComic(comicId, genreIds)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
    
    /**
     * 删除漫画的类型关联
     * @param comicId 漫画ID
     * @param genreId 类型ID
     * @return 响应状态
     */
    @DeleteMapping
    public ResponseEntity<Void> removeGenreFromComic(
            @RequestParam Long comicId, 
            @RequestParam Long genreId) {
        if (comicToGenreService.removeGenreFromComic(comicId, genreId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 清空漫画的所有类型关联
     * @param comicId 漫画ID
     * @return 删除的数量
     */
    @DeleteMapping("/comic/{comicId}")
    public ResponseEntity<Integer> clearComicGenres(@PathVariable Long comicId) {
        int deletedCount = comicToGenreService.clearComicGenres(comicId);
        return ResponseEntity.ok(deletedCount);
    }
}
