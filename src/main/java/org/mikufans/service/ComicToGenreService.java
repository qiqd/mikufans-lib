package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Mapper;
import org.mikufans.entity.ComicToGenre;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * ComicToGenre服务层接口
 * 定义漫画-类型关联关系的业务逻辑方法
 */
@Mapper
public interface ComicToGenreService extends IService<ComicToGenre> {
  /**
   * 获取所有漫画-类型关联关系（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<ComicToGenre> getAllComicToGenres(Integer page, Integer size);

  /**
   * 根据漫画ID获取类型ID列表
   *
   * @param comicId 漫画ID
   * @return 类型ID列表
   */
  List<ComicToGenre> getGenresByComicId(Long comicId);

  /**
   * 根据类型ID获取漫画ID列表
   *
   * @param genreId 类型ID
   * @return 漫画ID列表
   */
  List<ComicToGenre> getComicsByGenreId(Long genreId);

  /**
   * 为漫画添加类型
   *
   * @param comicId 漫画ID
   * @param genreId 类型ID
   * @return 是否添加成功
   */
  boolean addGenreToComic(Long comicId, Long genreId);

  /**
   * 批量为漫画添加类型
   *
   * @param comicId  漫画ID
   * @param genreIds 类型ID列表
   */
  void addGenresToComic(Long comicId, List<Long> genreIds);

  /**
   * 删除漫画的类型关联
   *
   * @param comicId 漫画ID
   * @param genreId 类型ID
   * @return 是否删除成功
   */
  boolean removeGenreFromComic(Long comicId, Long genreId);

  /**
   * 清空漫画的所有类型关联
   *
   * @param comicId 漫画ID
   * @return 删除的数量
   */
  int clearComicGenres(Long comicId);
}
