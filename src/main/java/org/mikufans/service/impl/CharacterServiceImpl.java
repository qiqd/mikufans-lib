package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Character;
import org.mikufans.entity.base.MyPage;
import org.mikufans.repository.CharacterRepository;
import org.mikufans.service.CharacterService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ACGCharacter服务层实现类
 * 实现人物相关的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
  private final CharacterRepository characterRepository;

  @Override
  public MyPage<Character> getAllCharacters(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    org.springframework.data.domain.Page<Character> characterPage = characterRepository.findAll(pageable);
    List<Character> records = characterPage.getContent();
    return new MyPage<>(page, size.longValue(), (long) characterPage.getTotalPages(), characterPage.getTotalElements(), records);
  }

  @Override
  public Character getCharacterById(String id) {
    return characterRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public boolean saveCharacter(Character character) {
    character.setId(null);
    characterRepository.save(character);
    return true;
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveBatchCharacters(List<Character> characters) {
    characters.forEach(character -> character.setId(null));
    characterRepository.saveAll(characters);
  }

  @Override
  public boolean updateCharacter(Character character) {
    characterRepository.save(character);
    return true;
  }

  @Override
  public boolean deleteCharacter(String id) {
    characterRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Character> getCharactersByType(String type) {
    return List.of();
  }


  @Override
  public List<Character> searchCharactersByName(String name) {
    return characterRepository.findByNameContaining(name);
  }
}
