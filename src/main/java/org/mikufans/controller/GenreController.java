package org.mikufans.controller;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Genre;
import org.mikufans.entity.MyPage;
import org.mikufans.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Genre控制器类
 * 处理类型相关的HTTP请求
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

  private final GenreService genreService;

  /**
   * 获取所有类型（分页）
   *
   * @param page 页码，默认1
   * @param size 每页大小，默认10
   * @return 分页类型数据
   */
  @GetMapping
  public ResponseEntity<MyPage<Genre>> getAllGenres(
          @RequestParam(defaultValue = "1") Integer page,
          @RequestParam(defaultValue = "10") Integer size) {
    MyPage<Genre> genres = genreService.getAllGenres(page, size);
    return ResponseEntity.ok(genres);
  }

  /**
   * 根据ID获取类型
   *
   * @param id 类型ID
   * @return 类型对象
   */
  @GetMapping("/{id}")
  public ResponseEntity<Genre> getGenreById(@PathVariable Long id) {
    Genre genre = genreService.getGenreById(id);
    if (genre == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(genre);
  }

  /**
   * 创建新类型
   *
   * @param genre 类型对象
   * @return 创建的类型
   */
  @PostMapping
  public ResponseEntity<Genre> createGenre(@RequestBody Genre genre) {
    // 检查类型名是否已存在
    if (genreService.getGenreByName(genre.getName()) != null) {
      return ResponseEntity.badRequest().build();
    }

    if (genreService.saveGenre(genre)) {
      return ResponseEntity.ok(genre);
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 更新类型
   *
   * @param id    类型ID
   * @param genre 类型对象
   * @return 更新后的类型
   */
  @PutMapping("/{id}")
  public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
    // 检查类型是否存在
    if (genreService.getGenreById(id) == null) {
      return ResponseEntity.notFound().build();
    }

    // 确保ID一致
    genre.setId(id);

    if (genreService.updateGenre(genre)) {
      return ResponseEntity.ok(genre);
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 删除类型
   *
   * @param id 类型ID
   * @return 响应状态
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
    // 检查类型是否存在
    if (genreService.getGenreById(id) == null) {
      return ResponseEntity.notFound().build();
    }

    if (genreService.deleteGenre(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 根据名称查询类型
   *
   * @param name 类型名称
   * @return 类型对象
   */
  @GetMapping("/search")
  public ResponseEntity<Genre> getGenreByName(@RequestParam String name) {
    Genre genre = genreService.getGenreByName(name);
    if (genre == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(genre);
  }
}
