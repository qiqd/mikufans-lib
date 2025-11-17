package org.mikufans.entity.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
  /**
   * 小尺寸图片
   */
  @Schema(description = "小尺寸图片", example = "https://example.com/small.jpg")
  private String small;
  /**
   * 中尺寸图片
   */
  @Schema(description = "中尺寸图片", example = "https://example.com/medium.jpg")
  private String medium;
  /**
   * 大尺寸图片
   */
  @Schema(description = "大尺寸图片", example = "https://example.com/large.jpg")
  private String large;
}
