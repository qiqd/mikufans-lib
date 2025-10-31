package org.mikufans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户生成标签表实体类（Tag）
 * 支持社区打标
 */
@Data
public class Tag {
  /**
   * 主键，自增ID
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 标签名称（如"热血"、"治愈"）
   */
  private String name;

  /**
   * 创建时间
   */
  private LocalDateTime createdAt;
}