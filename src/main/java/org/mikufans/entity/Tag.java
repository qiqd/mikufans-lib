package org.mikufans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户生成标签表实体类（Tag）
 * 支持社区打标
 */
@Schema(description = "标签信息")
@Data
public class Tag {
  /**
   * 主键，自增ID
   */
  @Schema(description = "标签ID", example = "1")
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 标签名称（如"热血"、"治愈"）
   */
  @Schema(description = "标签名称", example = "热血")
  private String name;

  /**
   * 创建时间
   */
  @Schema(description = "创建时间")
  private LocalDateTime createdAt;
}