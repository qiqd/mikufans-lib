package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.mikufans.entity.base.Person;
import org.mikufans.entity.base.Work;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 动画作品表实体类
 * 包含所有动画的完整信息
 */
@Data
@SuperBuilder
@Document(collection = "animation")
@Schema(description = "动画作品信息")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Animation extends Work {

  /**
   * 子ID
   */
  @Schema(description = "子ID", example = "4232")
  private String subId;
  /**
   * 总集数
   */
  @Schema(description = "总集数", example = "26")
  private String episodeCount;

  /**
   * 单集时长（分钟）
   */
  @Schema(description = "单集时长（分钟）", example = "24")
  private String duration;

  /**
   * 播放平台（如 Bilibili、Crunchyroll）
   */
  @Schema(description = "播放平台", example = "Bilibili")
  private String broadcastPlatform;

  /**
   * 导演
   */
  @Indexed(background = true)
  @Schema(description = "导演")
  private List<Person> director;

  /**
   * 演员
   */

  @Indexed(background = true)
  @Schema(description = "演员")
  private List<Person> actor;
  /**
   * 编剧
   */
  @Indexed(background = true)
  @Schema(description = "编剧")
  private List<Person> writer;

  /**
   * 音乐制作
   */
  @Schema(description = "音乐制作")
  private List<Person> musician;

  /**
   * 动画师
   */
  @Schema(description = "动画师")
  private List<Person> animator;

  /**
   * 制片人
   */
  @Schema(description = "制片人")
  private List<Person> producer;

  /**
   * 动画制作公司
   */
  @Schema(description = "动画制作公司", example = "Ufotable")
  private String animationStudio;


  /**
   * 改编来源（如"漫画改编"、"轻小说改编"）
   */
  @Schema(description = "改编来源", example = "漫画改编")
  private String sourceMaterial;

  /**
   * 所属系列
   */
  @Schema(description = "所属系列")
  private List<Animation> series;
}