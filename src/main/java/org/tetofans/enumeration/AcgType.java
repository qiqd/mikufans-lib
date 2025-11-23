package org.tetofans.enumeration;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Acg作品类型")
public enum AcgType {
  @Schema(description = "电视动画")
  @JsonProperty("电视动画")
  TV("电视动画"),

  @Schema(description = "小说")
  @JsonProperty("轻小说")
  LIGHT_NOVEL("轻小说"),

  @Schema(description = "漫画")
  @JsonProperty("漫画")
  COMIC("漫画");

  private final String value;

  AcgType(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}