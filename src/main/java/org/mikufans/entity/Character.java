package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
   * 类型：FICTIONAL（虚构角色）或 REAL（现实人物）
   */
  private String type;

  /**
   * 头像或照片URL
   */
  @Schema(description = "角色图片URL", example = "https://example.com/char.png")
  private String imageUrl;

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
   * 标签列表，存储为 JSON 字符串
   */
  private List<String> tags;

  /**
   * 种族（如人类、机器人、恶魔）
   */
  private String fictionalSpecies;

  /**
   * 设定年龄
   */
  @Schema(description = "角色年龄", example = "15")
  private Integer fictionalAge;

  /**
   * 设定生日（MM-DD格式）
   */
  @Schema(description = "角色生日", example = "12-27")
  private String fictionalBirthday;

  /**
   * 性格特征（如热血、冷静、腹黑）
   */
  private String fictionalPersonality;

  /**
   * 真实出生日期
   */
  private LocalDate realBirthDate;

  /**
   * 国籍
   */
  private String realNationality;

  /**
   * 所属事务所或公司
   */
  private String realAgency;

  /**
   * 技能列表，存储为 JSON 字符串
   */
  private List<String> realSkills;

  /**
   * 社交账号，存储为 JSON 字符串
   */
  private Map<String, String> socialAccounts;

  /**
   * 声优ID（仅对剧中人有效，指向另一个 acg_character）
   */
  @Schema(description = "配音演员ID", example = "1")
  private Long voiceActorId;

  /**
   * 创建时间
   */
  private LocalDate createdAt;

  /**
   * 最后更新时间
   */
  private LocalDate updatedAt;
}