package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.CharacterWork;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.CharacterWorkMapper;
import org.mikufans.service.CharacterWorkService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CharacterWork服务层实现类
 * 实现人物与作品关联关系的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class CharacterWorkServiceImpl extends ServiceImpl<CharacterWorkMapper, CharacterWork> implements CharacterWorkService {

  private final CharacterWorkMapper characterWorkMapper;

  @Override
  public MyPage<CharacterWork> getAllCharacterWorks(Integer page, Integer size) {
    Page<CharacterWork> pageResult = characterWorkMapper.selectPage(new Page<>(page, size), null);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public List<CharacterWork> getWorksByCharacterId(Long characterId) {
    QueryWrapper<CharacterWork> wrapper = new QueryWrapper<>();
    wrapper.eq("character_id", characterId);
    return characterWorkMapper.selectList(wrapper);
  }

  @Override
  public List<CharacterWork> getCharactersByWork(Long workId, String workType) {
    QueryWrapper<CharacterWork> wrapper = new QueryWrapper<>();
    wrapper.eq("work_id", workId).eq("work_type", workType);
    return characterWorkMapper.selectList(wrapper);
  }

  @Override
  public boolean saveCharacterWork(CharacterWork characterWork) {
    // 检查是否已存在相同的关联关系
    QueryWrapper<CharacterWork> wrapper = new QueryWrapper<>();
    wrapper.eq("character_id", characterWork.getCharacterId())
            .eq("work_id", characterWork.getWorkId())
            .eq("work_type", characterWork.getWorkType());

    if (characterWorkMapper.selectOne(wrapper) != null) {
      return false; // 已存在
    }

    return save(characterWork);
  }

  @Override
  public boolean saveBatchCharacterWorks(List<CharacterWork> characterWorks) {
    return saveBatch(characterWorks);
  }

  @Override
  public boolean deleteCharacterWork(Long characterId, Long workId, String workType) {
    QueryWrapper<CharacterWork> wrapper = new QueryWrapper<>();
    wrapper.eq("character_id", characterId)
            .eq("work_id", workId)
            .eq("work_type", workType);
    return remove(wrapper);
  }

  @Override
  public int deleteByCharacterId(Long characterId) {
    QueryWrapper<CharacterWork> wrapper = new QueryWrapper<>();
    wrapper.eq("character_id", characterId);
    return characterWorkMapper.delete(wrapper);
  }

  @Override
  public int deleteByWork(Long workId, String workType) {
    QueryWrapper<CharacterWork> wrapper = new QueryWrapper<>();
    wrapper.eq("work_id", workId).eq("work_type", workType);
    return characterWorkMapper.delete(wrapper);
  }
}
