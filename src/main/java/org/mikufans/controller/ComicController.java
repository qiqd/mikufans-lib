package org.mikufans.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Comic;
import org.mikufans.entity.MyPage;
import org.mikufans.service.ComicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Comic控制器
 * 处理漫画实体的HTTP请求
 */
@Tag(name = "漫画管理", description = "漫画作品相关接口")
@RestController
@RequestMapping("/api/comics")
@RequiredArgsConstructor
public class ComicController {

  private final ComicService comicService;

  /**
   * 获取所有漫画（分页）
   *
   * @param page 页码，默认1
   * @param size 每页大小，默认10
   * @return 分页漫画数据
   */
  @Operation(summary = "获取所有漫画", description = "分页查询所有漫画作品")
  @GetMapping
  public ResponseEntity<MyPage<Comic>> getAllComics(
          @Parameter(description = "页码，默认1") @RequestParam(defaultValue = "1") Integer page,
          @Parameter(description = "每页大小，默认10") @RequestParam(defaultValue = "10") Integer size) {
    MyPage<Comic> comics = comicService.getAllComics(page, size);
    return ResponseEntity.ok(comics);
  }

  /**
   * 根据ID获取漫画详情
   *
   * @param id 漫画ID
   * @return 漫画详情
   */
  @Operation(summary = "获取漫画详情", description = "根据ID获取漫画详细信息")
  @GetMapping("/{id}")
  public ResponseEntity<Comic> getComicById(
          @Parameter(description = "漫画ID") @PathVariable Long id) {
    Comic comic = comicService.getComicById(id);
    if (comic == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(comic);
  }

  /**
   * 创建新漫画
   *
   * @param comics 漫画实体
   * @return 创建的漫画
   */
  @Operation(summary = "创建漫画", description = "新增漫画作品信息")
  @PostMapping
  public ResponseEntity<Void> createComic(
          @Parameter(description = "漫画信息") @RequestBody List<Comic> comics) {
    comicService.saveBatchComic(comics);
    return ResponseEntity.ok().build();
  }

  /**
   * 更新漫画信息
   *
   * @param comic 漫画实体
   * @return 更新后的漫画
   */
  @Operation(summary = "更新漫画", description = "根据ID更新漫画信息")
  @PutMapping("/{id}")
  public ResponseEntity<Comic> updateComic(
          @Parameter(description = "漫画ID") @PathVariable Long id,
          @Parameter(description = "漫画信息") @RequestBody Comic comic) {
    comicService.updateComic(comic);
    return ResponseEntity.ok(comic);
  }

  /**
   * 删除漫画
   *
   * @param id 漫画ID
   * @return 删除结果
   */
  @Operation(summary = "删除漫画", description = "根据ID删除漫画作品")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteComic(
          @Parameter(description = "漫画ID") @PathVariable Long id) {
    boolean deleted = comicService.deleteComic(id);
    if (!deleted) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().build();
  }
}