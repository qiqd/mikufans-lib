package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mikufans.entity.Character;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * ACGCharacter服务层接口
 * 定义人物相关的业务操作
 */
public interface CharacterService extends IService<Character> {

  /**
   * 获取所有人物列表（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<Character> getAllCharacters(Integer page, Integer size);

  /**
   * 根据ID获取人物
   *
   * @param id 人物ID
   * @return 人物对象
   */
  Character getCharacterById(Long id);

  /**
   * 新增人物
   *
   * @param character 人物对象
   * @return 是否新增成功
   */
  boolean saveCharacter(Character character);

  void saveBatchCharacters(List<Character> characters);

  /**
   * 更新人物
   *
   * @param character 人物对象
   * @return 是否更新成功
   */
  boolean updateCharacter(Character character);

  /**
   * 删除人物
   *
   * @param id 人物ID
   * @return 是否删除成功
   */
  boolean deleteCharacter(Long id);

  /**
   * 根据类型获取人物列表
   *
   * @param type 类型：FICTIONAL或REAL
   * @return 人物列表
   */
  List<Character> getCharactersByType(String type);

  /**
   * 根据名称搜索人物
   *
   * @param name 人物名称
   * @return 人物列表
   */
  List<Character> searchCharactersByName(String name);
}
