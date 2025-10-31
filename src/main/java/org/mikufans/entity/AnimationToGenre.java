package org.mikufans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 动画与类型多对多关联表实体类
 */
@Data
public class AnimationToGenre {
  /**
   * 主键，自增ID
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 动画ID
   */
  private Long animationId;

  /**
   * 类型ID
   */
  private Long genreId;
}