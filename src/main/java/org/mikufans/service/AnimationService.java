package org.mikufans.service;

import org.mikufans.entity.Animation;
import org.mikufans.entity.base.MyPage;
import org.mikufans.entity.request.UpdateFieldById;
import org.mikufans.entity.response.TitleListResponse;
import org.mikufans.entity.response.UpdateResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
  Animation getAnimationById(String id);

  /**
   * 添加动画
   *
   * @param animations 动画对象
   */
  void saveBatchAnimations(List<Animation> animations);

  /**
   * 更新动画
   *
   * @param animation 动画对象
   * @return 是否更新成功
   */
  boolean updateAnimation(Animation animation);

  /**
   * 删除动画
   *
   * @param id 动画ID
   * @return 是否删除成功
   */
  boolean deleteAnimation(String id);

  /**
   * 根据动画制作公司查询动画
   *
   * @param studio 动画制作公司
   * @return 动画列表
   */
  List<Animation> getAnimationsByStudio(String studio);

  /**
   * 根据导演查询动画
   *
   * @param director 导演
   * @return 动画列表
   */
  List<Animation> getAnimationsByDirector(String director);

  /**
   * 根据播放平台查询动画
   *
   * @param platform 播放平台
   * @return 动画列表
   */
  List<Animation> getAnimationsByBroadcastPlatform(String platform);

  /**
   * 根据名称查询动画
   *
   * @param name 动画名称
   * @return 动画列表
   */
  List<Animation> getAnimationsByTitle(String name);

  /**
   * 批量更新动画字段
   *
   * @param updateFieldById 包含动画ID和要更新的字段的列表
   * @return 是否更新成功
   */
  <T> UpdateResponse bulkUpdateByIdsAndField(String fieldName, List<UpdateFieldById<T>> updateFieldById);

  /**
   * 根据年份分页查询动画
   *
   * @param start 开始日期
   * @param end   结束日期
   * @param page  页码
   * @param size  每页大小
   * @return 动画列表
   */
  Page<Animation> getAnimationsByYear(LocalDate start, LocalDate end, Integer page, Integer size);

  Map<String, Integer> updateImageBatch(List<TitleListResponse> titleListResponses);
}
