package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mikufans.entity.Comic;
import org.mikufans.entity.MyPage;

import java.util.List;

/**
 * Comic服务层接口
 * 定义漫画实体的业务逻辑方法
 */
public interface ComicService extends IService<Comic> {
  /**
   * 获取所有漫画（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<Comic> getAllComics(Integer page, Integer size);

  /**
   * 根据ID获取漫画详情
   *
   * @param id 漫画ID
   * @return 漫画实体
   */
  Comic getComicById(Long id);

  /**
   * 创建新漫画
   *
   * @param comic 漫画实体
   */
  void saveBatchComic(List<Comic> comics);

  /**
   * 更新漫画信息
   *
   * @param comic 漫画实体
   */
  void updateComic(Comic comic);

  /**
   * 删除漫画
   *
   * @param id 漫画ID
   * @return 是否删除成功
   */
  boolean deleteComic(Long id);
}