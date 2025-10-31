package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.mikufans.entity.AnimationToGenre;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * AnimationToGenre服务层接口
 * 定义动画-类型关联关系的业务逻辑方法
 */
@Mapper
public interface AnimationToGenreService extends IService<AnimationToGenre> {
  /**
   * 获取所有动画-类型关联关系（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<AnimationToGenre> getAllAnimationToGenres(Integer page, Integer size);

  /**
   * 根据动画ID获取类型ID列表
   *
   * @param animationId 动画ID
   * @return 类型ID列表
   */
  List<AnimationToGenre> getGenresAnimationId(Long animationId);

  /**
   * 根据类型ID获取动画ID列表
   *
   * @param genreId 类型ID
   * @return 动画ID列表
   */
  List<AnimationToGenre> getAnimationsByGenreId(Long genreId);

  /**
   * 批量保存动画-类型关联关系
   *
   * @param animationToGenres 关联关系列表
   */
  void saveBatchAnimationToGenres(List<AnimationToGenre> animationToGenres);

  /**
   * 根据动画ID删除所有关联关系
   *
   * @param animationId 动画ID
   * @return 是否删除成功
   */
  boolean deleteByAnimationId(Long animationId);

  /**
   * 根据类型ID删除所有关联关系
   *
   * @param genreId 类型ID
   * @return 是否删除成功
   */
  boolean deleteByGenreId(Long genreId);
}
