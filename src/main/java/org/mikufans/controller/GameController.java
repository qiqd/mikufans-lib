package org.mikufans.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Game;
import org.mikufans.entity.MyPage;
import org.mikufans.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Game控制器
 * 处理游戏实体的HTTP请求
 */
@Tag(name = "游戏管理", description = "游戏作品相关接口")
@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

  private final GameService gameService;

  /**
   * 获取所有游戏（分页）
   *
   * @param page 页码，默认1
   * @param size 每页大小，默认10
   * @return 分页游戏数据
   */
  @Operation(summary = "获取所有游戏", description = "分页查询所有游戏作品")
  @GetMapping
  public ResponseEntity<MyPage<Game>> getAllGames(
          @Parameter(description = "页码，默认1") @RequestParam(defaultValue = "1") Integer page,
          @Parameter(description = "每页大小，默认10") @RequestParam(defaultValue = "10") Integer size) {
    MyPage<Game> games = gameService.getAllGames(page, size);
    return ResponseEntity.ok(games);
  }

  /**
   * 根据ID获取游戏详情
   *
   * @param id 游戏ID
   * @return 游戏详情
   */
  @Operation(summary = "获取游戏详情", description = "根据ID获取游戏详细信息")
  @GetMapping("/{id}")
  public ResponseEntity<Game> getGameById(@Parameter(description = "游戏ID") @PathVariable String id) {
    Game game = gameService.getGameById(id);
    if (game == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(game);
  }

  /**
   * 创建新游戏
   *
   * @param games 游戏实体
   * @return 创建的游戏
   */
  @Operation(summary = "创建游戏", description = "新增游戏作品信息")
  @PostMapping
  public ResponseEntity<Void> createGame(@Parameter(description = "游戏信息列表") @RequestBody List<Game> games) {
    gameService.saveBatchGames(games);
    return ResponseEntity.ok().build();
  }

  /**
   * 更新游戏信息
   *
   * @param game 游戏实体
   * @return 更新后的游戏
   */
  @Operation(summary = "更新游戏", description = "根据ID更新游戏信息")
  @PutMapping("/{id}")
  public ResponseEntity<Game> updateGame(@Parameter(description = "游戏信息") @RequestBody Game game) {
    gameService.updateGame(game);
    return ResponseEntity.ok(game);
  }

  /**
   * 删除游戏
   *
   * @param id 游戏ID
   * @return 删除结果
   */
  @Operation(summary = "删除游戏", description = "根据ID删除游戏作品")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGame(@Parameter(description = "游戏ID") @PathVariable String id) {
    boolean deleted = gameService.deleteGame(id);
    if (!deleted) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().build();
  }
}