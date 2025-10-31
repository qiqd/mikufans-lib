package org.mikufans.controller;

import org.mikufans.entity.GameToGenre;
import org.mikufans.entity.MyPage;
import org.mikufans.service.GameToGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * GameToGenre控制器类
 * 处理游戏与类型关联的HTTP请求
 */
@RestController
@RequestMapping("/api/game-genres")
public class GameToGenreController {

    @Autowired
    private GameToGenreService gameToGenreService;

    /**
     * 获取所有游戏-类型关联关系（分页）
     * 
     * @param page 页码，默认1
     * @param size 每页大小，默认10
     * @return 分页关联关系数据
     */
    @GetMapping("/all")
    public ResponseEntity<MyPage<GameToGenre>> getAllGameToGenres(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        MyPage<GameToGenre> data = gameToGenreService.getAllGameToGenres(page, size);
        return ResponseEntity.ok(data);
    }

    /**
     * 根据游戏ID获取类型ID列表
     * 
     * @param gameId 游戏ID
     * @return 类型ID列表
     */
    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<Long>> getGenreIdsByGameId(@PathVariable Long gameId) {
        List<Long> genreIds = gameToGenreService.getGenreIdsByGameId(gameId);
        return ResponseEntity.ok(genreIds);
    }

    /**
     * 根据类型ID获取游戏ID列表
     * 
     * @param genreId 类型ID
     * @return 游戏ID列表
     */
    @GetMapping("/genre/{genreId}")
    public ResponseEntity<List<Long>> getGameIdsByGenreId(@PathVariable Long genreId) {
        List<Long> gameIds = gameToGenreService.getGameIdsByGenreId(genreId);
        return ResponseEntity.ok(gameIds);
    }

    /**
     * 为游戏添加类型
     * 
     * @param gameId  游戏ID
     * @param genreId 类型ID
     * @return 响应状态
     */
    @PostMapping
    public ResponseEntity<Void> addGenreToGame(
            @RequestParam Long gameId,
            @RequestParam Long genreId) {
        if (gameToGenreService.addGenreToGame(gameId, genreId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build(); // 可能已存在
    }

    /**
     * 批量为游戏添加类型
     * 
     * @param gameId   游戏ID
     * @param genreIds 类型ID列表
     * @return 响应状态
     */
    @PostMapping("/batch")
    public ResponseEntity<Void> addGenresToGame(
            @RequestParam Long gameId,
            @RequestBody List<Long> genreIds) {
        if (gameToGenreService.addGenresToGame(gameId, genreIds)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }

    /**
     * 删除游戏的类型关联
     * 
     * @param gameId  游戏ID
     * @param genreId 类型ID
     * @return 响应状态
     */
    @DeleteMapping
    public ResponseEntity<Void> removeGenreFromGame(
            @RequestParam Long gameId,
            @RequestParam Long genreId) {
        if (gameToGenreService.removeGenreFromGame(gameId, genreId)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * 清空游戏的所有类型关联
     * 
     * @param gameId 游戏ID
     * @return 删除的数量
     */
    @DeleteMapping("/game/{gameId}")
    public ResponseEntity<Integer> clearGameGenres(@PathVariable Long gameId) {
        int deletedCount = gameToGenreService.clearGameGenres(gameId);
        return ResponseEntity.ok(deletedCount);
    }
}
