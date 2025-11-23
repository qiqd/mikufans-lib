package org.tetofans.enumeration;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "影视子类型")
public enum FilmTVSubtype {
  @Schema(description = "电影")
  @JsonProperty("movie")
  MOVIE("movie"),

  @Schema(description = "电视剧")
  @JsonProperty("tv_series")
  TV_SERIES("tv_series"),

  @Schema(description = "迷你剧")
  @JsonProperty("mini_series")
  MINI_SERIES("mini_series"),

  @Schema(description = "纪录片")
  @JsonProperty("documentary")
  DOCUMENTARY("documentary"),

  @Schema(description = "短片")
  @JsonProperty("short_film")
  SHORT_FILM("short_film");

  private final String value;

  FilmTVSubtype(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}