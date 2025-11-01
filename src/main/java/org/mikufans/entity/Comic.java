package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * 漫画作品表实体类
 * 包含所有漫画的完整信息
 */
@Data
@Document(collection = "comics")
@Schema(description = "漫画作品信息")
@EqualsAndHashCode(callSuper = true)
public class Comic extends BaseWork {
  /**
   * 总章节数
   */
  @Schema(description = "总章节数", example = "205")
  private Integer chapterCount;

  /**
   * 总卷数
   */
  @Schema(description = "总卷数", example = "23")
  private Integer volumeCount;

  /**
   * 作者
   */
  @Indexed(background = true)
  @Schema(description = "作者", example = "吾峠呼世晴")
  private String author;

  /**
   * 画师（若与作者不同）
   */
  @Indexed(background = true)
  @Schema(description = "作画", example = "大久保笃")
  private String artist;

  /**
   * 出版社
   */
  @Indexed(background = true)
  @Schema(description = "出版社", example = "集英社")
  private String publisher;

  /**
   * 连载杂志（如周刊少年Jump）
   */
  @Schema(description = "连载杂志", example = "周刊少年JUMP")
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