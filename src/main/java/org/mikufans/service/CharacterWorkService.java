package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.mikufans.entity.CharacterWork;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * CharacterWork服务层接口
 * 定义人物与作品关联关系的业务操作
 */
@Mapper
public interface CharacterWorkService extends IService<CharacterWork> {

  /**
   * 获取所有关联关系（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<CharacterWork> getAllCharacterWorks(Integer page, Integer size);

  /**
   * 根据人物ID获取关联作品列表
   *
   * @param characterId 人物ID
   * @return 关联关系列表
   */
  List<CharacterWork> getWorksByCharacterId(Long characterId);

  /**
   * 根据作品ID和类型获取关联人物列表
   *
   * @param workId   作品ID
   * @param workType 作品类型
   * @return 关联关系列表
   */
  List<CharacterWork> getCharactersByWork(Long workId, String workType);

  /**
   * 新增关联关系
   *
   * @param characterWork 关联关系对象
   * @return 是否新增成功
   */
  boolean saveCharacterWork(CharacterWork characterWork);

  /**
   * 批量新增关联关系
   *
   * @param characterWorks 关联关系列表
   * @return 是否新增成功
   */
  boolean saveBatchCharacterWorks(List<CharacterWork> characterWorks);

  /**
   * 删除关联关系
   *
   * @param characterId 人物ID
   * @param workId      作品ID
   * @param workType    作品类型
   * @return 是否删除成功
   */
  boolean deleteCharacterWork(Long characterId, Long workId, String workType);

  /**
   * 删除人物的所有关联关系
   *
   * @param characterId 人物ID
   * @return 删除的数量
   */
  int deleteByCharacterId(Long characterId);

  /**
   * 删除作品的所有关联关系
   *
   * @param workId   作品ID
   * @param workType 作品类型
   * @return 删除的数量
   */
  int deleteByWork(Long workId, String workType);
}
