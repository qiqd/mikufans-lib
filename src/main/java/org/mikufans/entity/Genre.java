package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * 作品类型表实体类（Genre）
 * 用于分类作品主题
 */
@Data
@Document(collection = "genres")
@Schema(description = "分类信息")
public class Genre {
  /**
   * 主键，自增ID
   */
  @Schema(description = "分类ID", example = "1")
  private String id;

  /**
   * 类型名称（如 Action, Comedy）
   */
  @Schema(description = "分类名称", example = "动作冒险")
  private String name;

  /**
   * 类型描述
   */
  @Schema(description = "分类描述", example = "包含动作元素的冒险类作品")
  private String description;

  /**
   * 创建时间
   */
  @Schema(description = "创建时间")
  private LocalDate createdAt;
}