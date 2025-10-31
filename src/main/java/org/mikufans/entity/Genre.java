package org.mikufans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;

/**
 * 作品类型表实体类（Genre）
 * 用于分类作品主题
 */
@Data
public class Genre {
  /**
   * 主键，自增ID
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 类型名称（如 Action, Comedy）
   */
  private String name;

  /**
   * 类型描述
   */
  private String description;

  /**
   * 创建时间
   */
  private LocalDate createdAt;
}