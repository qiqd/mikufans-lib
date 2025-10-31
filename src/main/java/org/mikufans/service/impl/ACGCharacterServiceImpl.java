package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.ACGCharacter;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.ACGCharacterMapper;
import org.mikufans.service.ACGCharacterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ACGCharacter服务层实现类
 * 实现人物相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class ACGCharacterServiceImpl extends ServiceImpl<ACGCharacterMapper, ACGCharacter> implements ACGCharacterService {
  private final ACGCharacterMapper acgCharacterMapper;

  @Override
  public MyPage<ACGCharacter> getAllCharacters(Integer page, Integer size) {
    Page<ACGCharacter> acgCharacterPage = new Page<>(page, size);
    Page<ACGCharacter> pageResult = query().page(acgCharacterPage);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public ACGCharacter getCharacterById(Long id) {
    return acgCharacterMapper.selectById(id);
  }

  @Override
  public boolean saveCharacter(ACGCharacter character) {
    character.setId(null);
    return save(character);
  }

  @Override
  public void saveBatchCharacters(List<ACGCharacter> characters) {
    characters.forEach(character -> character.setId(null));
    acgCharacterMapper.insert(characters);
  }

  @Override
  public boolean updateCharacter(ACGCharacter character) {
    return updateById(character);
  }

  @Override
  public boolean deleteCharacter(Long id) {
    return removeById(id);
  }

  @Override
  public List<ACGCharacter> getCharactersByType(String type) {
    QueryWrapper<ACGCharacter> wrapper = new QueryWrapper<>();
    wrapper.eq("type", type);
    return acgCharacterMapper.selectList(wrapper);
  }

  @Override
  public List<ACGCharacter> searchCharactersByName(String name) {
    QueryWrapper<ACGCharacter> wrapper = new QueryWrapper<>();
    wrapper.like("name", name);
    return acgCharacterMapper.selectList(wrapper);
  }
}
