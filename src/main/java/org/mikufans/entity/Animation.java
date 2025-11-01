package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * 动画作品表实体类
 * 包含所有动画的完整信息
 */
@Data
@Document(collection = "animations")
@Schema(description = "动画作品信息")
@EqualsAndHashCode(callSuper = true)
public class Animation extends BaseWork {
  /**
   * 总集数
   */
  @Schema(description = "总集数", example = "26")
  private Integer episodeCount;

  /**
   * 单集时长（分钟）
   */
  @Schema(description = "单集时长（分钟）", example = "24")
  private Integer durationPerEpisode;

  /**
   * 播放平台（如 Bilibili、Crunchyroll）
   */
  @Schema(description = "播放平台", example = "Bilibili")
  private String broadcastPlatform;

  /**
   * 开播日期
   */
  @Schema(description = "开播日期", example = "2019-04-06")
  private LocalDate startDate;

  /**
   * 完结日期
   */
  @Schema(description = "完结日期", example = "2019-09-28")
  private LocalDate endDate;

  /**
   * 导演
   */
  @Indexed(background = true)
  @Schema(description = "导演", example = "新海诚")
  private String director;

  /**
   * 编剧
   */
  @Indexed(background = true)
  @Schema(description = "编剧", example = "虚渊玄")
  private String scriptWriter;

  /**
   * 音乐制作
   */
  @Schema(description = "音乐制作", example = "梶浦由记")
  private String musicComposer;

  /**
   * 动画制作公司
   */
  @Schema(description = "动画制作公司", example = "Ufotable")
  private String animationStudio;

  /**
   * 主要声优列表，存储为 JSON 字符串
   */
  @Indexed(background = true)
  @Schema(description = "主要声优列表", example = "[\"花江夏树\", \"鬼头明里\", \"下野纮\"]")
  private List<String> mainVoiceActors;

  /**
   * 改编来源（如"漫画改编"、"轻小说改编"）
   */
  @Schema(description = "改编来源", example = "漫画改编")
  private String sourceMaterial;
}