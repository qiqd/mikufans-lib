package org.tetofans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "关联作品引用")
public class RelatedWorkRef {

  @Schema(description = "关联作品ID（MongoDB ObjectId）", example = "673d8f1a2b3c4d5e6f7a8b9c")
  private String workId;

  @Schema(description = "关联类型",
          allowableValues = {"adaptation", "prequel", "sequel", "spin_off", "same_universe"},
          example = "adaptation")
  private String relationType;
}