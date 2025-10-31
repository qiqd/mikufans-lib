package org.mikufans.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Character;
import org.mikufans.entity.MyPage;
import org.mikufans.service.CharacterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ACGCharacter控制器类
 * 处理人物相关的HTTP请求
 */
@Tag(name = "角色管理", description = "ACG角色相关接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/characters")
public class CharacterController {

  private final CharacterService characterService;

  /**
   * 获取所有人物（分页）
   *
   * @param page 页码，默认1
   * @param size 每页大小，默认10
   * @return 分页人物数据
   */
  @Operation(summary = "获取所有角色", description = "分页查询所有ACG角色")
  @GetMapping
  public ResponseEntity<MyPage<Character>> getAllCharacters(
          @Parameter(description = "页码，默认1") @RequestParam(defaultValue = "1") Integer page,
          @Parameter(description = "每页大小，默认10") @RequestParam(defaultValue = "10") Integer size) {
    MyPage<Character> characters = characterService.getAllCharacters(page, size);
    return ResponseEntity.ok(characters);
  }

  /**
   * 根据ID获取人物
   *
   * @param id 人物ID
   * @return 人物对象
   */
  @Operation(summary = "获取角色详情", description = "根据角色ID获取详细信息")
  @GetMapping("/{id}")
  public ResponseEntity<Character> getCharacterById(
          @Parameter(description = "角色ID") @PathVariable Long id) {
    Character character = characterService.getCharacterById(id);
    if (character == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(character);
  }

  /**
   * 创建新人物
   *
   * @param character 人物对象
   * @return 创建的人物
   */
  @Operation(summary = "创建角色", description = "新增ACG角色信息")
  @PostMapping
  public ResponseEntity<Character> createCharacter(
          @Parameter(description = "角色信息") @RequestBody Character character) {
    if (characterService.saveCharacter(character)) {
      return ResponseEntity.ok(character);
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 更新人物
   *
   * @param id        人物ID
   * @param character 人物对象
   * @return 更新后的人物
   */
  @Operation(summary = "更新角色", description = "根据角色ID更新角色信息")
  @PutMapping("/{id}")
  public ResponseEntity<Character> updateCharacter(
          @Parameter(description = "角色ID") @PathVariable Long id,
          @Parameter(description = "角色信息") @RequestBody Character character) {
    // 检查人物是否存在
    if (characterService.getCharacterById(id) == null) {
      return ResponseEntity.notFound().build();
    }

    // 确保ID一致
    character.setId(id);

    if (characterService.updateCharacter(character)) {
      return ResponseEntity.ok(character);
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 删除人物
   *
   * @param id 人物ID
   * @return 响应状态
   */
  @Operation(summary = "删除角色", description = "根据角色ID删除角色")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCharacter(
          @Parameter(description = "角色ID") @PathVariable Long id) {
    // 检查人物是否存在
    if (characterService.getCharacterById(id) == null) {
      return ResponseEntity.notFound().build();
    }

    if (characterService.deleteCharacter(id)) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.internalServerError().build();
  }

  /**
   * 根据类型获取人物列表
   *
   * @param type 类型：FICTIONAL或REAL
   * @return 人物列表
   */
  @Operation(summary = "根据类型获取角色", description = "根据角色类型查询角色列表")
  @GetMapping("/type/{type}")
  public ResponseEntity<List<Character>> getCharactersByType(
          @Parameter(description = "角色类型") @PathVariable String type) {
    List<Character> characters = characterService.getCharactersByType(type);
    return ResponseEntity.ok(characters);
  }

  /**
   * 根据名称搜索人物
   *
   * @param name 人物名称
   * @return 人物列表
   */
  @Operation(summary = "搜索角色", description = "根据名称搜索角色")
  @GetMapping("/search")
  public ResponseEntity<List<Character>> searchCharactersByName(
          @Parameter(description = "角色名称") @RequestParam String name) {
    List<Character> characters = characterService.searchCharactersByName(name);
    return ResponseEntity.ok(characters);
  }
}
