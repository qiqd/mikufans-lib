package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 作品基类
 * 封装动画、漫画、游戏等ACG作品的公共属性
 */
@Data
@Schema(description = "作品基类，封装ACG作品的公共属性")
public class BaseWork {
  /**
   * 主键，自增ID
   */
  @Schema(description = "主键ID", example = "1")
  @Id
  private String id;

  /**
   * 作品名称（中文名）
   */
  @Indexed(background = true)
  @Schema(description = "作品名称（中文名）", example = "鬼灭之刃")
  private String title;

  /**
   * 原名（如日文名）
   */
  @Indexed(background = true)
  @Schema(description = "原名（如日文名）", example = "鬼滅の刃")
  private String originalTitle;

  /**
   * 英文名称
   */
  @Indexed(background = true)
  @Schema(description = "英文名称", example = "Demon Slayer")
  private String englishTitle;

  /**
   * 作品简介
   */
  @Schema(description = "作品简介", example = "大正时期，主人公炭治郎的家人被鬼杀害...")
  private String description;

  /**
   * 发布日期
   */
  @Schema(description = "发布日期", example = "2019-04-06")
  private Date releaseDate;

  /**
   * 制作/开发国家/地区
   */
  @Schema(description = "制作/开发国家/地区", example = "日本")
  private String country;

  /**
   * 原始语言
   */
  @Schema(description = "原始语言", example = "日语")
  private String language;

  /**
   * 状态：ONGOING, COMPLETED, CANCELLED, HIATUS
   */
  @Schema(description = "作品状态", allowableValues = "ONGOING,COMPLETED,CANCELLED,HIATUS", example = "COMPLETED")
  private String status;

  /**
   * 封面图URL
   */
  @Schema(description = "封面图URL", example = "https://example.com/cover.jpg")
  private String coverImageUrl;

  /**
   * 官方网站链接
   */
  @Schema(description = "官方网站链接", example = "https://example.com")
  private String officialWebsite;

  /**
   * 平均评分（0.00~10.00）
   */
  @Schema(description = "平均评分", allowableValues = "0.00-10.00", example = "9.5")
  private Float averageRating;

  /**
   * 总观看/阅读/浏览次数
   */
  @Schema(description = "总观看/阅读/浏览次数", example = "1000000")
  private Long totalViews;

  /**
   * 制作公司/作者/开发商（通用字段）
   */
  @Schema(description = "制作公司/作者/开发商", example = "Ufotable")
  private String studioOrAuthor;

  /**
   * 评级系统（如 CERO、ESRB、PEGI、MPAA）
   */
  @Schema(description = "评级系统", example = "CERO")
  private String ageRatingSystem;

  /**
   * 评级代码（如 A/B/C 或 E/T/M）
   */
  @Schema(description = "评级代码", example = "A")
  private String ageRatingCode;

  /**
   * 建议最小年龄
   */
  @Schema(description = "建议最小年龄", example = "12")
  private Integer ageRatingMinAge;

  /**
   * 评级说明
   */
  @Schema(description = "评级说明", example = "对12岁以上用户推荐")
  private String ageRatingDescription;

  /**
   * 创建时间
   */
  @Schema(description = "这条数据的创建时间", example = "2023-01-01T12:00:00")
  private LocalDateTime createdAt;

  /**
   * 最后更新时间
   */
  @Schema(description = "这条数据的最后更新时间", example = "2023-01-01T12:00:00")
  private LocalDateTime updatedAt;
  /**
   * 分类
   */
  @Indexed(background = true)
  @Schema(description = "分类", example = "爱情")
  private String genre;

  /**
   * 单集更新时间，比如“每周三/20：00”
   */
  @Schema(description = "单集更新时间", example = "每周三20：00")
  private String singleUpdateTime;
}