package org.tetofans.enumeration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "作品通用状态")
public enum WorkStatus {
  @Schema(description = "已完结")
  @JsonProperty("completed")
  COMPLETED("completed"),

  @Schema(description = "连载/播出中")
  @JsonProperty("ongoing")
  ONGOING("ongoing"),

  @Schema(description = "暂停更新")
  @JsonProperty("hiatus")
  HIATUS("hiatus"),

  @Schema(description = "已取消/腰斩")
  @JsonProperty("cancelled")
  CANCELLED("cancelled"),

  @Schema(description = "已上映/发布")
  @JsonProperty("released")
  RELEASED("released"),

  @Schema(description = "即将发布")
  @JsonProperty("upcoming")
  UPCOMING("upcoming");

  private final String value;

  WorkStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }
}