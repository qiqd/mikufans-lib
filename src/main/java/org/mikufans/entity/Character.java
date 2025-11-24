package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.mikufans.entity.base.Image;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * ACG人物统一表实体类
 * 包含剧中角色与现实人物（声优、作者等）
 */
@Data
@Document(collection = "characters")
@Schema(description = "ACG角色信息")
public class Character {
  /**
   * 主键，自增ID
   */
  @Schema(description = "角色ID", example = "1")
  private String id;

  /**
   * 角色或人物名称
   */
  @Indexed(background = true)
  @Schema(description = "角色名称", example = "炭治郎")
  private String name;

  /**
   * 其他名称列表
   */
  @Indexed(background = true)
  @Schema(description = "角色其他名称列表", example = "[\"炭治郎\", \"炭治郎 Jr.\"]")
  private List<String> otherNames;

  /**
   * 头像或照片URL
   */
  @Schema(description = "角色图片URL", example = "[\"small\": \"small.jpg\", \"medium\": \"medium.jpg\", \"large\": \"large.jpg\"]")
  private Image image;

  /**
   * 简介
   */
  @Schema(description = "角色简介", example = "鬼杀队剑士，为了让妹妹祢豆子变回人类而踏上旅程")
  private String description;

  /**
   * 性别（M/F/Other/Unknown）
   */
  @Schema(description = "角色性别", example = "M")
  private String gender;

  /**
   * 声优ID（仅对剧中人有效，指向另一个 acg_character）
   */
  @Schema(description = "配音演员ID", example = "1")
  private String voiceActorId;

  /**
   * 所属媒体id，比如动画id、漫画id等，若是现实人物则为null
   */
  @Schema(description = "所属媒体ID", example = "1")
  private String mediaId;

  /**
   * 创建时间
   */
  @Schema(description = "这条数据创建时间，不用手动设置", example = "2023-01-01")
  private LocalDate createdAt;

  /**
   * 最后更新时间
   */
  @Schema(description = "这条数据最后更新时间，不用手动设置", example = "2023-01-01")
  private LocalDate updatedAt;
}