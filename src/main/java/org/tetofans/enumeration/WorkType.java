package org.tetofans.enumeration;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "作品一级类型")
public enum WorkType {
  @Schema(description = "文学作品")
  @JsonProperty("literary")
  LITERARY("literary"),

  @Schema(description = "动漫作品（含动画与漫画）")
  @JsonProperty("anime")
  ANIME("anime"),

  @Schema(description = "影视作品（电影/电视剧）")
  @JsonProperty("film_tv")
  FILM_TV("film_tv");

  private final String value;

  WorkType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}