package org.tetofans.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tetofans.enumeration.AnimeSubtype;
import org.tetofans.enumeration.WorkType;

import java.time.LocalDate;
import java.util.List;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "anime_works")
@Schema(description = "动漫作品（动画、漫画等）")
public class Anime extends Work {

  public Anime() {
    super();
    this.setType(WorkType.ANIME);
  }

  @Schema(description = "子类型", implementation = AnimeSubtype.class)
  private AnimeSubtype subtype;

  // === 动画特有 ===
  @Schema(description = "动画制作公司", example = "MAPPA")
  private String animationStudio;

  @Schema(description = "监督（导演）", example = "神谷纯")
  private String director;

  @Schema(description = "系列构成", example = "冈田麿里")
  private String seriesComposer;

  @Schema(description = "角色设计", example = "马场裕之")
  private String characterDesigner;

  @Schema(description = "声优列表", example = "[\"花泽香菜\", \"小野贤章\"]")
  private List<String> voiceCast;

  @Schema(description = "集数（动画）", example = "12")
  private Integer episodeCount;

  @Schema(description = "播放季度", example = "2024-Summer")
  private String broadcastSeason;

  @Schema(description = "BD发售日期", example = "2024-03-27")
  private LocalDate bdReleaseDate;

  @Schema(description = "制作委员会", example = "[\"Aniplex\", \"KADOKAWA\"]")
  private List<String> productionCommittee;

  // === 漫画特有 ===
  @Schema(description = "漫画家/作者", example = "富坚义博")
  private String mangaka;

  @Schema(description = "连载平台/杂志", example = "周刊少年Jump")
  private String serialization;

  @Schema(description = "章节总数", example = "400")
  private Integer chapterCount;

  @Schema(description = "单行本卷数", example = "38")
  private Integer tankobonVolumeCount;

  @Schema(description = "更新频率", example = "weekly")
  private String updateFrequency;

  @Schema(description = "是否已完结（漫画）", example = "false")
  private Boolean isCompleted;

  // === 通用但有侧重 ===
  @Schema(description = "原作类型",
          allowableValues = {"manga", "light_novel", "game", "original"},
          example = "light_novel")
  private String sourceType;

  @Schema(description = "原作名称", example = "刀剑神域")
  private String originalSource;

  @Schema(description = "音乐制作", example = "Lantis")
  private String musicProducer;

  @Schema(description = "是否为原创（非改编）", example = "false")
  private Boolean isOriginal;

}
