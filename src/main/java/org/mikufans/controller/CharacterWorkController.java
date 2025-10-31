package org.mikufans.controller;

import org.mikufans.entity.CharacterWork;
import org.mikufans.service.CharacterWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * CharacterWork控制器类
 * 处理人物与作品关联关系的HTTP请求
 */
@RestController
@RequestMapping("/api/character-works")
public class CharacterWorkController {
    
    @Autowired
    private CharacterWorkService characterWorkService;
    
    /**
     * 获取所有关联关系（分页）
     * @param page 页码，默认1
     * @param size 每页大小，默认10
     * @return 分页关联关系数据
     */
    @GetMapping
    public ResponseEntity<MyPage<CharacterWork>> getAllCharacterWorks(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        MyPage<CharacterWork> characterWorks = characterWorkService.getAllCharacterWorks(page, size);
        return ResponseEntity.ok(characterWorks);
    }
    
    /**
     * 根据人物ID获取关联作品列表
     * @param characterId 人物ID
     * @return 关联关系列表
     */
    @GetMapping("/character/{characterId}")
    public ResponseEntity<List<CharacterWork>> getWorksByCharacterId(@PathVariable Long characterId) {
        List<CharacterWork> characterWorks = characterWorkService.getWorksByCharacterId(characterId);
        return ResponseEntity.ok(characterWorks);
    }
    
    /**
     * 根据作品ID和类型获取关联人物列表
     * @param workId 作品ID
     * @param workType 作品类型
     * @return 关联关系列表
     */
    @GetMapping("/work")
    public ResponseEntity<List<CharacterWork>> getCharactersByWork(
            @RequestParam Long workId, 
            @RequestParam String workType) {
        List<CharacterWork> characterWorks = characterWorkService.getCharactersByWork(workId, workType);
        return ResponseEntity.ok(characterWorks);
    }
    
    /**
     * 创建关联关系
     * @param characterWork 关联关系对象
     * @return 创建的关联关系
     */
    @PostMapping
    public ResponseEntity<CharacterWork> createCharacterWork(@RequestBody CharacterWork characterWork) {
        if (characterWorkService.saveCharacterWork(characterWork)) {
            return ResponseEntity.ok(characterWork);
        }
        return ResponseEntity.badRequest().build(); // 可能已存在
    }
    
    /**
     * 批量创建关联关系
     * @param characterWorks 关联关系列表
     * @return 创建的关联关系数量
     */
    @PostMapping("/batch")
    public ResponseEntity<Integer> createBatchCharacterWorks(@RequestBody List<CharacterWork> characterWorks) {
        if (characterWorkService.saveBatchCharacterWorks(characterWorks)) {
            return ResponseEntity.ok(characterWorks.size());
        }
        return ResponseEntity.internalServerError().build();
    }
    
    /**
     * 删除关联关系
     * @param characterId 人物ID
     * @param workId 作品ID
     * @param workType 作品类型
     * @return 响应状态
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteCharacterWork(
            @RequestParam Long characterId, 
            @RequestParam Long workId, 
            @RequestParam String workType) {
        if (characterWorkService.deleteCharacterWork(characterId, workId, workType)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    
    /**
     * 删除人物的所有关联关系
     * @param characterId 人物ID
     * @return 删除的数量
     */
    @DeleteMapping("/character/{characterId}")
    public ResponseEntity<Integer> deleteByCharacterId(@PathVariable Long characterId) {
        int deletedCount = characterWorkService.deleteByCharacterId(characterId);
        return ResponseEntity.ok(deletedCount);
    }
    
    /**
     * 删除作品的所有关联关系
     * @param workId 作品ID
     * @param workType 作品类型
     * @return 删除的数量
     */
    @DeleteMapping("/work")
    public ResponseEntity<Integer> deleteByWork(
            @RequestParam Long workId, 
            @RequestParam String workType) {
        int deletedCount = characterWorkService.deleteByWork(workId, workType);
        return ResponseEntity.ok(deletedCount);
    }
}
