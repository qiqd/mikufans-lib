package org.tetofans.enumeration;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "动漫子类型")
public enum AnimeSubtype {
  // 动画类
  @Schema(description = "电视动画")
  @JsonProperty("tv_animation")
  TV_ANIMATION("tv_animation"),

  @Schema(description = "动画电影")
  @JsonProperty("movie_animation")
  MOVIE_ANIMATION("movie_animation"),

  @Schema(description = "OVA")
  @JsonProperty("ova")
  OVA("ova"),

  @Schema(description = "网络动画（ONA）")
  @JsonProperty("ona")
  ONA("ona"),

  // 漫画类
  @Schema(description = "日式漫画")
  @JsonProperty("manga")
  MANGA("manga"),

  @Schema(description = "韩漫")
  @JsonProperty("manhwa")
  MANHWA("manhwa"),

  @Schema(description = "国漫")
  @JsonProperty("manhua")
  MANHUA("manhua"),

  @Schema(description = "条漫（Webtoon）")
  @JsonProperty("webtoon")
  WEBTOON("webtoon");

  private final String value;

  AnimeSubtype(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}