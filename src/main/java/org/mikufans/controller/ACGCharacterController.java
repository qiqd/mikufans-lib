package org.mikufans.controller;

import org.mikufans.entity.ACGCharacter;
import org.mikufans.service.ACGCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * ACGCharacter控制器类
 * 处理人物相关的HTTP请求
 */
@RestController
@RequestMapping("/api/characters")
public class ACGCharacterController {
    
    @Autowired
    private ACGCharacterService acgCharacterService;
    
    /**
     * 获取所有人物（分页）
     * @param page 页码，默认1
     * @param size 每页大小，默认10
     * @return 分页人物数据
     */
    @GetMapping
    public ResponseEntity<MyPage<ACGCharacter>> getAllCharacters(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        MyPage<ACGCharacter> characters = acgCharacterService.getAllCharacters(page, size);
        return ResponseEntity.ok(characters);
    }
    
    /**
     * 根据ID获取人物
     * @param id 人物ID
     * @return 人物对象
     */
    @GetMapping("/{id}")
    public ResponseEntity<ACGCharacter> getCharacterById(@PathVariable Long id) {
        ACGCharacter character = acgCharacterService.getCharacterById(id);
        if (character == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(character);
    }
    
    /**
     * 创建新人物
     * @param character 人物对象
     * @return 创建的人物
     */
    @PostMapping
    public ResponseEntity<ACGCharacter> createCharacter(@RequestBody ACGCharacter character) {
        if (acgCharacterService.saveCharacter(character)) {
            return ResponseEntity.ok(character);
        }
        return ResponseEntity.internalServerError().build();
    }
    
    /**
     * 更新人物
     * @param id 人物ID
     * @param character 人物对象
     * @return 更新后的人物
     */
    @PutMapping("/{id}")
    public ResponseEntity<ACGCharacter> updateCharacter(@PathVariable Long id, @RequestBody ACGCharacter character) {
        // 检查人物是否存在
        if (acgCharacterService.getCharacterById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        
        // 确保ID一致
        character.setId(id);
        
        if (acgCharacterService.updateCharacter(character)) {
            return ResponseEntity.ok(character);
        }
        return ResponseEntity.internalServerError().build();
    }
    
    /**
     * 删除人物
     * @param id 人物ID
     * @return 响应状态
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        // 检查人物是否存在
        if (acgCharacterService.getCharacterById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        
        if (acgCharacterService.deleteCharacter(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.internalServerError().build();
    }
    
    /**
     * 根据类型获取人物列表
     * @param type 类型：FICTIONAL或REAL
     * @return 人物列表
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<ACGCharacter>> getCharactersByType(@PathVariable String type) {
        List<ACGCharacter> characters = acgCharacterService.getCharactersByType(type);
        return ResponseEntity.ok(characters);
    }
    
    /**
     * 根据名称搜索人物
     * @param name 人物名称
     * @return 人物列表
     */
    @GetMapping("/search")
    public ResponseEntity<List<ACGCharacter>> searchCharactersByName(@RequestParam String name) {
        List<ACGCharacter> characters = acgCharacterService.searchCharactersByName(name);
        return ResponseEntity.ok(characters);
    }
}
