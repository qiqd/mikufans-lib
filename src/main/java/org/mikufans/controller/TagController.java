package org.mikufans.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.base.MyPage;
import org.mikufans.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Tag控制器类
 * 处理标签相关的HTTP请求
 */
@Tag(name = "标签管理", description = "标签相关接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tags")
public class TagController {

  private final TagService tagService;

  /**
   * 获取所有标签（分页）
   *
   * @param page 页码，默认1
   * @param size 每页大小，默认10
   * @return 分页标签数据
   */
  @Operation(summary = "获取所有标签", description = "分页查询所有标签")
  @GetMapping
  public ResponseEntity<MyPage<org.mikufans.entity.base.Tag>> getAllTags(
          @Parameter(description = "页码，默认1") @RequestParam(defaultValue = "1") Integer page,
          @Parameter(description = "每页大小，默认10") @RequestParam(defaultValue = "10") Integer size) {
    MyPage<org.mikufans.entity.base.Tag> tags = tagService.getAllTags(page, size);
    return ResponseEntity.ok(tags);
  }

  /**
   * 根据ID获取标签
   *
   * @param id 标签ID
   * @return 标签对象
   */
  @Operation(summary = "获取标签详情", description = "根据ID获取标签详细信息")
  @GetMapping("/{id}")
  public ResponseEntity<org.mikufans.entity.base.Tag> getTagById(@Parameter(description = "标签ID") @PathVariable String id) {
    org.mikufans.entity.base.Tag tag = tagService.getTagById(id);
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
  @Operation(summary = "创建标签", description = "新增标签信息")
  @PostMapping
  public ResponseEntity<String> createTag(@Parameter(description = "标签信息") @RequestBody org.mikufans.entity.base.Tag tag) {
    // 检查标签名是否已存在
    if (!tagService.getTagByName(tag.getName()).isEmpty()) {
      return ResponseEntity.badRequest().body("标签名已存在");
    }

    if (tagService.saveTag(tag)) {
      return ResponseEntity.ok("标签创建成功");
    }
    return ResponseEntity.internalServerError().body("标签创建失败");
  }

  /**
   * 更新标签
   *
   * @param id  标签ID
   * @param tag 标签对象
   * @return 更新后的标签
   */
  @Operation(summary = "更新标签", description = "根据ID更新标签信息")
  @PutMapping("/{id}")
  public ResponseEntity<org.mikufans.entity.base.Tag> updateTag(
          @Parameter(description = "标签ID") @PathVariable String id,
          @Parameter(description = "标签信息") @RequestBody org.mikufans.entity.base.Tag tag) {
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
  @Operation(summary = "删除标签", description = "根据ID删除标签")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTag(@Parameter(description = "标签ID") @PathVariable String id) {
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
  @Operation(summary = "根据名称查询标签", description = "根据标签名称查询标签信息")
  @GetMapping("/search")
  public ResponseEntity<List<org.mikufans.entity.base.Tag>> getTagByName(@Parameter(description = "标签名称") @RequestParam String name) {
    List<org.mikufans.entity.base.Tag> tags = tagService.getTagByName(name);
    if (tags.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(tags);
  }
}
