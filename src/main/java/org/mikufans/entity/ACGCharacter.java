package org.mikufans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * ACG人物统一表实体类
 * 包含剧中角色与现实人物（声优、作者等）
 */
@Data
public class ACGCharacter {
  /**
   * 主键，自增ID
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 角色或人物名称
   */
  private String name;

  /**
   * 类型：FICTIONAL（虚构角色）或 REAL（现实人物）
   */
  private String type;

  /**
   * 头像或照片URL
   */
  private String imageUrl;

  /**
   * 简介
   */
  private String description;

  /**
   * 性别（M/F/Other/Unknown）
   */
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
  private Integer fictionalAge;

  /**
   * 设定生日（MM-DD格式）
   */
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