package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.mikufans.entity.GameToGenre;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * GameToGenre服务层接口
 * 定义游戏-类型关联关系的业务逻辑方法
 */
@Mapper
public interface GameToGenreService extends IService<GameToGenre> {
  /**
   * 获取所有游戏-类型关联关系（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<GameToGenre> getAllGameToGenres(Integer page, Integer size);

  /**
   * 根据游戏ID获取类型ID列表
   *
   * @param gameId 游戏ID
   * @return 类型ID列表
   */
  List<Long> getGenreIdsByGameId(Long gameId);

  /**
   * 根据类型ID获取游戏ID列表
   *
   * @param genreId 类型ID
   * @return 游戏ID列表
   */
  List<Long> getGameIdsByGenreId(Long genreId);

  /**
   * 为游戏添加类型
   *
   * @param gameId  游戏ID
   * @param genreId 类型ID
   * @return 是否添加成功
   */
  boolean addGenreToGame(Long gameId, Long genreId);

  /**
   * 批量为游戏添加类型
   *
   * @param gameId   游戏ID
   * @param genreIds 类型ID列表
   * @return 是否添加成功
   */
  boolean addGenresToGame(Long gameId, List<Long> genreIds);

  /**
   * 删除游戏的类型关联
   *
   * @param gameId  游戏ID
   * @param genreId 类型ID
   * @return 是否删除成功
   */
  boolean removeGenreFromGame(Long gameId, Long genreId);

  /**
   * 清空游戏的所有类型关联
   *
   * @param gameId 游戏ID
   * @return 删除的数量
   */
  int clearGameGenres(Long gameId);
}
