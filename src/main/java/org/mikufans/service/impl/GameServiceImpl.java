package org.mikufans.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.Game;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.GameMapper;
import org.mikufans.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Game服务层实现类
 * 实现游戏实体的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

  private final GameMapper gameMapper;

  @Override
  public MyPage<Game> getAllGames(Integer page, Integer size) {
    Page<Game> pageResult = gameMapper.selectPage(new Page<>(page, size), null);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public Game getGameById(Long id) {
    return gameMapper.selectById(id);
  }

  @Override
  public void saveBatchGames(List<Game> games) {
    games.forEach(game -> game.setId(null));
    gameMapper.insert(games);
  }

  @Override
  public void updateGame(Game game) {
    gameMapper.updateById(game);
  }

  @Override
  public boolean deleteGame(Long id) {
    return gameMapper.deleteById(id) > 0;
  }
}