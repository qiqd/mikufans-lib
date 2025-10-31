package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mikufans.entity.Game;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * Game服务层接口
 * 定义游戏实体的业务逻辑方法
 */
public interface GameService extends IService<Game> {
  /**
   * 获取所有游戏（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<Game> getAllGames(Integer page, Integer size);

  /**
   * 根据ID获取游戏详情
   *
   * @param id 游戏ID
   * @return 游戏实体
   */
  Game getGameById(Long id);

  /**
   * 创建新游戏
   *
   * @param games 游戏实体
   */
  void saveBatchGames(List<Game> games);

  /**
   * 更新游戏信息
   *
   * @param game 游戏实体
   */
  void updateGame(Game game);

  /**
   * 删除游戏
   *
   * @param id 游戏ID
   * @return 是否删除成功
   */
  boolean deleteGame(Long id);
}