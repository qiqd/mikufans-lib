package org.mikufans.entity.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "分页响应对象")
public class MyPage<T> {
  @Schema(description = "当前页码", example = "1")
  private Integer pageNum;

  @Schema(description = "每页大小", example = "10")
  private Long pageSize;

  @Schema(description = "总页数", example = "20")
  private Long totalPage;

  @Schema(description = "总记录数", example = "100")
  private Long total;

  @Schema(description = "分页数据列表")
  private List<T> data;
}
