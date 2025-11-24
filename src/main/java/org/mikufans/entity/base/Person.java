package org.mikufans.entity.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.mikufans.entity.enumeration.StaffType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "人物实体类")
public class Person {
  @Id
  @Schema(description = "唯一标识符")
  private String id;

  @Schema(description = "人物名称")
  @Indexed(background = true)
  private String name;

  @Schema(description = "人物中文名称")
  @Indexed(background = true)
  private String nameCn;

  @Schema(description = "人物其他名称列表")
  @Indexed(background = true)
  private List<String> otherNames;

  @Schema(description = "人员类型")
  private StaffType type;

  @Schema(description = "参与的作品列表")
  private List<String> works;

  @Schema(description = "角色列表")
  private String role;

  @Schema(description = "人物图片信息")
  private Image image;
}
