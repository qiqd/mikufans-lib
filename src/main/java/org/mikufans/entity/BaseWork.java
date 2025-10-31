package org.mikufans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 作品基类
 * 封装动画、漫画、游戏等ACG作品的公共属性
 */
@Data
public abstract class BaseWork {
  /**
   * 主键，自增ID
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 作品名称（中文名）
   */
  private String title;

  /**
   * 原名（如日文名）
   */
  private String originalTitle;

  /**
   * 英文名称
   */
  private String englishTitle;

  /**
   * 作品简介
   */
  private String description;

  /**
   * 发布日期
   */
  private Date releaseDate;

  /**
   * 制作/开发国家/地区
   */
  private String country;

  /**
   * 原始语言
   */
  private String language;

  /**
   * 状态：ONGOING, COMPLETED, CANCELLED, HIATUS
   */
  private String status;

  /**
   * 封面图URL
   */
  private String coverImageUrl;

  /**
   * 官方网站链接
   */
  private String officialWebsite;

  /**
   * 平均评分（0.00~10.00）
   */
  private Float averageRating;

  /**
   * 总观看/阅读/浏览次数
   */
  private Long totalViews;

  /**
   * 制作公司/作者/开发商（通用字段）
   */
  private String studioOrAuthor;

  /**
   * 评级系统（如 CERO、ESRB、PEGI、MPAA）
   */
  private String ageRatingSystem;

  /**
   * 评级代码（如 A/B/C 或 E/T/M）
   */
  private String ageRatingCode;

  /**
   * 建议最小年龄
   */
  private Integer ageRatingMinAge;

  /**
   * 评级说明
   */
  private String ageRatingDescription;

  /**
   * 创建时间
   */
  private LocalDateTime createdAt;

  /**
   * 最后更新时间
   */
  private LocalDateTime updatedAt;
}