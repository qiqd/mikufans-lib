package org.mikufans.controller;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.MyPage;
import org.mikufans.entity.Tag;
import org.mikufans.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tag控制器类
 * 处理标签相关的HTTP请求
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

  private final TagService tagService;

  /**
     * 获取所有标签（分页）
     * @param page 页码，默认1
     * @param size 每页大小，默认10
     * @return 分页标签数据
     */
    @GetMapping
    public ResponseEntity<MyPage<Tag>> getAllTags(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        MyPage<Tag> tags = tagService.getAllTags(page, size);
        return ResponseEntity.ok(tags);
    }

  /**
   * 根据ID获取标签
   *
   * @param id 标签ID
   * @return 标签对象
   */
  @GetMapping("/{id}")
  public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
    Tag tag = tagService.getTagById(id);
    if (tag == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(tag);
  }

  /**
   * 创建新标签
   *
   * @param tag 标签对象
   * @return 创建的标签
   */
  @PostMapping
  public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
    // 检查标签名是否已存在
    if (tagService.getTagByName(tag.getName()) != null) {
      return ResponseEntity.badRequest().build();
    }

    if (tagService.saveTag(tag)) {
      return ResponseEntity.ok(tag);
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 更新标签
   *
   * @param id  标签ID
   * @param tag 标签对象
   * @return 更新后的标签
   */
  @PutMapping("/{id}")
  public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
    // 检查标签是否存在
    if (tagService.getTagById(id) == null) {
      return ResponseEntity.notFound().build();
    }

    // 确保ID一致
    tag.setId(id);

    if (tagService.updateTag(tag)) {
      return ResponseEntity.ok(tag);
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 删除标签
   *
   * @param id 标签ID
   * @return 响应状态
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
    // 检查标签是否存在
    if (tagService.getTagById(id) == null) {
      return ResponseEntity.notFound().build();
    }

    if (tagService.deleteTag(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 根据名称查询标签
   *
   * @param name 标签名称
   * @return 标签对象
   */
  @GetMapping("/search")
  public ResponseEntity<Tag> getTagByName(@RequestParam String name) {
    Tag tag = tagService.getTagByName(name);
    if (tag == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(tag);
  }
}
