package org.mikufans.service;

import org.mikufans.entity.Animation;
import org.mikufans.entity.MyPage;
import org.mikufans.entity.Tag;

import java.util.List;

public interface AnimationService {
  /**
   * 获取所有动画（分页）
   *
   * @param page 页码
   * @param size 每页大小
   * @return 分页数据
   */
  MyPage<Animation> getAllAnimations(Integer page, Integer size);

  /**
   * 获取动画详情
   *
   * @param id 动画ID
   * @return 动画详情
   */
  Animation getAnimationById(Long id);

  /**
   * 添加动画
   *
   * @param animations 动画对象
   */
  void saveBatchAnimations(List<Animation> animations, List<Tag> tags);

  /**
   * 更新动画
   *
   * @param animation 动画对象
   * @return
   */
  boolean updateAnimation(Animation animation);

  /**
   * 删除动画
   *
   * @param id 动画ID
   * @return
   */
  boolean deleteAnimation(Long id);
}
