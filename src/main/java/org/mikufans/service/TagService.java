package org.mikufans.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.mikufans.entity.MyPage;
import org.mikufans.entity.Tag;

/**
 * Tag服务层接口
 * 定义标签相关的业务操作
 */
public interface TagService extends IService<Tag> {

  /**
   * 获取所有标签（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<Tag> getAllTags(Integer page, Integer size);

  /**
   * 根据ID获取标签
   *
   * @param id 标签ID
   * @return 标签对象
   */
  Tag getTagById(Long id);

  /**
   * 新增标签
   *
   * @param tag 标签对象
   * @return 是否新增成功
   */
  boolean saveTag(Tag tag);

  /**
   * 更新标签
   *
   * @param tag 标签对象
   * @return 是否更新成功
   */
  boolean updateTag(Tag tag);

  /**
   * 删除标签
   *
   * @param id 标签ID
   * @return 是否删除成功
   */
  boolean deleteTag(Long id);

  /**
   * 根据标签名查询标签
   *
   * @param name 标签名
   * @return 标签对象，不存在则返回null
   */
  Tag getTagByName(String name);
}
