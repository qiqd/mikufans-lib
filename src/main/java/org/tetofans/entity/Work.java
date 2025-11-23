package org.tetofans.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.tetofans.enumeration.WorkStatus;
import org.tetofans.enumeration.WorkType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
@SuperBuilder
@Schema(description = "作品通用基类（由子类继承）")
public class Work {

  @Id
  @Schema(description = "MongoDB 文档ID", example = "673d8f1a2b3c4d5e6f7a8b9c")
  private String id;

  @Schema(description = "主标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "三体")
  private String title;

  @Schema(description = "原始语言标题", example = "The Three-Body Problem")
  private String originalTitle;

  @Schema(description = "一级类型", implementation = WorkType.class)
  private WorkType type;

  @Schema(description = "别名列表（含译名、昵称）", example = "[\"地球往事\", \"Three Body\"]")
  private List<String> aliases;

  @Schema(description = "体裁/标签", example = "[\"科幻\", \"硬科幻\"]")
  private List<String> genres;

  @Schema(description = "关键词/SEO标签", example = "[\"外星文明\", \"黑暗森林\"]")
  private List<String> tags;

  @Schema(description = "主要语言（ISO 639-1）", example = "zh")
  private String language;

  @Schema(description = "原始创作语言", example = "zh")
  private String originalLanguage;

  @Schema(description = "出品国家/地区", example = "中国")
  private String country;

  @Schema(description = "首次发布日期", example = "2006-05-01")
  private LocalDate releaseDate;

  @Schema(description = "简介", example = "地球文明与三体文明的首次接触...")
  private String summary;

  @Schema(description = "多语言标题映射", example = "{\"en\": \"The Three-Body Problem\"}")
  private Map<String, String> localizedTitles;

  @Schema(description = "多语言简介映射", example = "{\"en\": \"A sci-fi epic...\"}")
  private Map<String, String> localizedSummaries;

  @Schema(description = "平均评分（0.0~10.0）", example = "9.4")
  private Double rating;

  @Schema(description = "评分人数", example = "528763")
  private Integer ratingCount;

  @Schema(description = "封面/海报URL", example = "https://example.com/santi.jpg")
  private String coverUrl;

  @Schema(description = "内容分级", example = "PG-13")
  private String contentRating;

  @Schema(description = "官方主页", example = "https://www.santi-book.com")
  private String officialWebsite;

  @Schema(description = "是否仍在更新/连载中", example = "false")
  private Boolean isOngoing;

  @Schema(description = "总部分数（如系列共3部）", example = "3")
  private Integer totalParts;

  @Schema(description = "获奖信息", example = "[\"雨果奖最佳长篇小说\"]")
  private List<String> awards;

  @Schema(description = "作品状态", implementation = WorkStatus.class)
  private WorkStatus status;

  @Schema(description = "关联作品列表")
  private List<RelatedWorkRef> relatedWorks;

  @Schema(description = "创建时间", example = "2025-11-20T10:23:45Z")
  private LocalDateTime createdAt;

  @Schema(description = "最后更新时间", example = "2025-11-20T10:23:45Z")
  private LocalDateTime updatedAt;
}