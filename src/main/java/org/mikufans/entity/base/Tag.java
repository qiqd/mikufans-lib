package org.mikufans.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 用户生成标签表实体类（Tag）
 * 支持社区打标
 */
@Data
@Schema(description = "标签信息")
@Document(collection = "tags")
public class Tag {
  /**
   * 主键，自增ID
   */
  @Schema(description = "标签ID", example = "1")
  private String id;

  /**
   * 标签名称（如"热血"、"治愈"）
   */
  @Schema(description = "标签名称", example = "热血")
  private String name;

  /**
   * 创建时间
   */
  @Schema(description = "创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
}