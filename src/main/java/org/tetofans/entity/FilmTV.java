package org.tetofans.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tetofans.enumeration.FilmTVSubtype;
import org.tetofans.enumeration.WorkType;

import java.util.List;


@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "film_tv_works")
@Schema(description = "影视作品（电影、电视剧等）")
public class FilmTV extends Work {

  public FilmTV() {
    super();
    this.setType(WorkType.FILM_TV);
  }

  @Schema(description = "子类型", implementation = FilmTVSubtype.class)
  private FilmTVSubtype subtype;

  // 通用
  @Schema(description = "导演", example = "郭帆")
  private String director;

  @Schema(description = "编剧", example = "[\"刘慈欣\", \"龚格尔\"]")
  private List<String> writers;

  @Schema(description = "主演", example = "[\"吴京\", \"李雪健\"]")
  private List<String> cast;

  @Schema(description = "制片公司", example = "中影股份")
  private String productionCompany;

  // === 电影特有 ===
  @Schema(description = "片长（分钟，电影）", example = "145")
  private Integer runtimeMinutes;

  @Schema(description = "预算（美元）", example = "50000000")
  private Long budget;

  @Schema(description = "全球票房（美元）", example = "700000000")
  private Long boxOffice;

  @Schema(description = "首映电影节", example = "戛纳电影节")
  private String premiereFestival;

  @Schema(description = "是否为系列电影", example = "true")
  private Boolean isFranchiseFilm;

  // === 电视剧特有 ===
  @Schema(description = "季数", example = "1")
  private Integer seasonNumber;

  @Schema(description = "总季数", example = "3")
  private Integer totalSeasons;

  @Schema(description = "单集时长（分钟）", example = "45")
  private Integer episodeDurationMinutes;

  @Schema(description = "总集数", example = "12")
  private Integer totalEpisodes;

  @Schema(description = "电视剧状态",
          allowableValues = {"returning", "ended", "cancelled", "upcoming"},
          example = "ended")
  private String tvStatus;

  @Schema(description = "流媒体平台", example = "[\"Netflix\", \"腾讯视频\"]")
  private List<String> streamingOn;

  @Schema(description = "拍摄地", example = "[\"北京\", \"新西兰\"]")
  private List<String> filmingLocations;
}