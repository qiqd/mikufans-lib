package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Character;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.CharacterMapper;
import org.mikufans.service.CharacterService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ACGCharacter服务层实现类
 * 实现人物相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class CharacterServiceImpl extends ServiceImpl<CharacterMapper, Character> implements CharacterService {
  private final CharacterMapper characterMapper;

  @Override
  public MyPage<Character> getAllCharacters(Integer page, Integer size) {
    Page<Character> acgCharacterPage = new Page<>(page, size);
    Page<Character> pageResult = query().page(acgCharacterPage);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public Character getCharacterById(Long id) {
    return characterMapper.selectById(id);
  }

  @Override
  public boolean saveCharacter(Character character) {
    character.setId(null);
    return save(character);
  }

  @Override
  public void saveBatchCharacters(List<Character> characters) {
    characters.forEach(character -> character.setId(null));
    characterMapper.insert(characters);
  }

  @Override
  public boolean updateCharacter(Character character) {
    return updateById(character);
  }

  @Override
  public boolean deleteCharacter(Long id) {
    return removeById(id);
  }

  @Override
  public List<Character> getCharactersByType(String type) {
    QueryWrapper<Character> wrapper = new QueryWrapper<>();
    wrapper.eq("type", type);
    return characterMapper.selectList(wrapper);
  }

  @Override
  public List<Character> searchCharactersByName(String name) {
    QueryWrapper<Character> wrapper = new QueryWrapper<>();
    wrapper.like("name", name);
    return characterMapper.selectList(wrapper);
  }
}
