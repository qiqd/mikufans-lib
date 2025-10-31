package org.mikufans.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * 动画作品表实体类
 * 包含所有动画的完整信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Animation extends BaseWork {
  /**
   * 总集数
   */
  private Integer episodeCount;

  /**
   * 单集时长（分钟）
   */
  private Integer durationPerEpisode;

  /**
   * 播放平台（如 Bilibili、Crunchyroll）
   */
  private String broadcastPlatform;

  /**
   * 开播日期
   */
  private Date startDate;

  /**
   * 完结日期
   */
  private Date endDate;

  /**
   * 导演
   */
  private String director;

  /**
   * 编剧
   */
  private String scriptWriter;

  /**
   * 音乐制作
   */
  private String musicComposer;

  /**
   * 动画制作公司
   */
  private String animationStudio;

  /**
   * 主要声优列表，存储为 JSON 字符串
   */
  private List<String> mainVoiceActors;

  /**
   * 改编来源（如"漫画改编"、"轻小说改编"）
   */
  private String sourceMaterial;
}