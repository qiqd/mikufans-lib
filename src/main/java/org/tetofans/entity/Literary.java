package org.tetofans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tetofans.enumeration.LiterarySubtype;
import org.tetofans.enumeration.WorkType;

import java.time.LocalDate;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Document(collection = "literary_works")
@Schema(description = "文学作品（小说、轻小说等）")
public class Literary extends Work {

  public Literary() {
    super();
    this.setType(WorkType.LITERARY);
  }

  @Schema(description = "子类型", implementation = LiterarySubtype.class)
  private LiterarySubtype subtype;

  @Schema(description = "作者", example = "刘慈欣")
  private String author;

  @Schema(description = "译者（翻译作品）", example = "Ken Liu")
  private String translator;

  @Schema(description = "出版社", example = "重庆出版社")
  private String publisher;

  @Schema(description = "ISBN", example = "9787536692930")
  private String isbn;

  @Schema(description = "丛书/系列名", example = "三体系列")
  private String series;

  @Schema(description = "卷号", example = "1")
  private Integer volumeNumber;

  @Schema(description = "首次出版日期", example = "2006-05-01")
  private LocalDate firstPublicationDate;

  @Schema(description = "页数", example = "302")
  private Integer pageCount;

  @Schema(description = "字数（估算）", example = "300000")
  private Integer wordCount;

  @Schema(description = "装帧", example = "平装")
  private String binding;

  @Schema(description = "版次", example = "第1版")
  private String edition;

  // === 轻小说特有 ===
  @Schema(description = "插画师（轻小说）", example = "abec")
  private String illustrator;

  @Schema(description = "所属文库", example = "电击文库")
  private String imprint;

  @Schema(description = "封面插图URL", example = "https://example.com/cover.jpg")
  private String coverIllustrationUrl;

  // === 普通小说特有 ===
  @Schema(description = "文学流派", example = "硬科幻")
  private String literarySchool;

  @Schema(description = "是否为网络文学", example = "false")
  private Boolean isWebNovel;

  @Schema(description = "首发平台（网文）", example = "起点中文网")
  private String firstPublishPlatform;
}