package org.mikufans.service;

import org.mikufans.entity.Genre;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * Genre服务层接口
 * 定义类型相关的业务操作
 */
public interface GenreService {

  /**
   * 获取所有类型（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<Genre> getAllGenres(Integer page, Integer size);

  /**
   * 根据ID获取类型
   *
   * @param id 类型ID
   * @return 类型对象
   */
  Genre getGenreById(String id);

  /**
   * 新增类型
   *
   * @param genre 类型对象
   * @return 是否新增成功
   */
  boolean saveGenre(Genre genre);

  /**
   * 更新类型
   *
   * @param genre 类型对象
   * @return 是否更新成功
   */
  boolean updateGenre(Genre genre);

  /**
   * 删除类型
   *
   * @param id 类型ID
   * @return 是否删除成功
   */
  boolean deleteGenre(String id);

  /**
   * 根据类型名查询类型
   *
   * @param name 类型名
   * @return 类型对象，不存在则返回null
   */
  List<Genre> getGenreByName(String name);
}
