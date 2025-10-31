package org.mikufans.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.mikufans.entity.GameToGenre;
import org.mikufans.entity.MyPage;
import org.mikufans.mapper.GameToGenreMapper;
import org.mikufans.service.GameToGenreService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * GameToGenre服务层实现类
 * 实现游戏-类型关联关系的业务逻辑
 */
@Service
@RequiredArgsConstructor
public class GameToGenreServiceImpl extends ServiceImpl<GameToGenreMapper, GameToGenre> implements GameToGenreService {

  private final GameToGenreMapper gameToGenreMapper;

  @Override
  public MyPage<GameToGenre> getAllGameToGenres(Integer page, Integer size) {
    Page<GameToGenre> gameToGenrePage = new Page<>(page, size);
    Page<GameToGenre> pageResult = query().page(gameToGenrePage);
    return new MyPage<>(page, size.longValue(), pageResult.getPages(), pageResult.getTotal(), pageResult.getRecords());
  }

  @Override
  public List<Long> getGenreIdsByGameId(Long gameId) {
    QueryWrapper<GameToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("game_id", gameId);
    List<GameToGenre> list = gameToGenreMapper.selectList(wrapper);
    return list.stream().map(GameToGenre::getGenreId).collect(Collectors.toList());
  }

  @Override
  public List<Long> getGameIdsByGenreId(Long genreId) {
    QueryWrapper<GameToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("genre_id", genreId);
    List<GameToGenre> list = gameToGenreMapper.selectList(wrapper);
    return list.stream().map(GameToGenre::getGameId).collect(Collectors.toList());
  }

  @Override
  public boolean addGenreToGame(Long gameId, Long genreId) {
    // 检查是否已存在
    QueryWrapper<GameToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("game_id", gameId).eq("genre_id", genreId);
    if (gameToGenreMapper.selectOne(wrapper) != null) {
      return false;
    }

    GameToGenre gameToGenre = new GameToGenre();
    gameToGenre.setGameId(gameId);
    gameToGenre.setGenreId(genreId);
    return save(gameToGenre);
  }

  @Override
  public boolean addGenresToGame(Long gameId, List<Long> genreIds) {
    List<GameToGenre> list = genreIds.stream().map(genreId -> {
      GameToGenre gameToGenre = new GameToGenre();
      gameToGenre.setGameId(gameId);
      gameToGenre.setGenreId(genreId);
      return gameToGenre;
    }).collect(Collectors.toList());

    return saveBatch(list);
  }

  @Override
  public boolean removeGenreFromGame(Long gameId, Long genreId) {
    QueryWrapper<GameToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("game_id", gameId).eq("genre_id", genreId);
    return remove(wrapper);
  }

  @Override
  public int clearGameGenres(Long gameId) {
    QueryWrapper<GameToGenre> wrapper = new QueryWrapper<>();
    wrapper.eq("game_id", gameId);
    return gameToGenreMapper.delete(wrapper);
  }
}
