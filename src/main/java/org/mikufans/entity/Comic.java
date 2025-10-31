package org.mikufans.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 漫画作品表实体类
 * 包含所有漫画的完整信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Comic extends BaseWork {
  /**
   * 总章节数
   */
  private Integer chapterCount;

  /**
   * 总卷数
   */
  private Integer volumeCount;

  /**
   * 作者
   */
  private String author;

  /**
   * 画师（若与作者不同）
   */
  private String artist;

  /**
   * 出版社
   */
  private String publisher;

  /**
   * 连载杂志（如周刊少年Jump）
   */
  private String serializationMagazine;

  /**
   * 首次发表日期
   */
  private LocalDate firstPublishedDate;

  /**
   * 最后发表日期
   */
  private LocalDate lastPublishedDate;

  /**
   * 目标受众：SHONEN, SHOJO, SEINEN, JOSEI, KIDS
   */
  private String demographic;

  /**
   * 是否已完结（冗余字段，便于快速查询）
   */
  private Boolean isCompleted;
}