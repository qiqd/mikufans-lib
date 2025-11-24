package org.mikufans.service.impl;

import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Game;
import org.mikufans.entity.base.MyPage;
import org.mikufans.repository.GameRepository;
import org.mikufans.service.GameService;
import org.springframework.stereotype.Service;

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
    return null;
  }

  @Override
  public Game getGameById(String id) {
    return null;
  }

  @Override
  public void saveBatchGames(List<Game> games) {

  }

  @Override
  public void updateGame(Game game) {

  }

  @Override
  public boolean deleteGame(String id) {
    return false;
  }

  @Override
  public List<Game> getGameByTitle(String name) {
    return List.of();
  }
}
