package org.tetofans.enumeration;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "文学子类型")
public enum LiterarySubtype {
  @Schema(description = "长篇小说")
  @JsonProperty("novel")
  NOVEL("novel"),

  @Schema(description = "轻小说")
  @JsonProperty("light_novel")
  LIGHT_NOVEL("light_novel"),

  @Schema(description = "短篇小说")
  @JsonProperty("short_story")
  SHORT_STORY("short_story"),

  @Schema(description = "诗歌")
  @JsonProperty("poetry")
  POETRY("poetry"),

  @Schema(description = "散文/随笔")
  @JsonProperty("essay")
  ESSAY("essay");

  private final String value;

  LiterarySubtype(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}