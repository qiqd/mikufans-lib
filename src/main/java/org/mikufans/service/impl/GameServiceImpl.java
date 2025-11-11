package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Game;
import org.mikufans.entity.base.MyPage;
import org.mikufans.repository.GameRepository;
import org.mikufans.service.GameService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Game服务层实现类
 * 实现游戏实体的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {

  private final GameRepository gameRepository;

  @Override
  public MyPage<Game> getAllGames(Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page - 1, size);
    org.springframework.data.domain.Page<Game> pageResult = gameRepository.findAll(pageable);
    List<Game> records = pageResult.getContent();
    return new MyPage<>(page, size.longValue(), (long) pageResult.getTotalPages(), pageResult.getTotalElements(), records);
  }

  @Override
  public Game getGameById(String id) {
    return gameRepository.findById(id).orElse(null);
  }

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void saveBatchGames(List<Game> games) {
    games.forEach(game -> game.setId(null));
    gameRepository.saveAll(games);
  }

  @Override
  public void updateGame(Game game) {
    gameRepository.save(game);
  }

  @Override
  public boolean deleteGame(String id) {
    gameRepository.deleteById(id);
    return true;
  }

  @Override
  public List<Game> getGameByTitle(String name) {
    List<Game> result = gameRepository.findByTitleContaining(name);
    if (result.isEmpty()) {
      result = gameRepository.findByOriginalTitleContaining(name);
    }
    if (result.isEmpty()) {
      result = gameRepository.findByEnglishTitleContaining(name);
    }
    return result;
  }
}
