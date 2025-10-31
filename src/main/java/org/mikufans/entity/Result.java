package org.mikufans.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "通用响应结果封装类")
public class Result<T> {
  @Schema(description = "响应数据")
  private T data;

  @Schema(description = "响应消息", example = "操作成功")
  private String message;

  @Schema(description = "响应状态码", example = "200")
  private Integer code;

  public static <T> Result<T> success(T data) {
    Result<T> result = new Result<>();
    result.setData(data);
    result.setCode(200);
    return result;
  }

  public static <T> Result<T> success(String message, T data) {
    Result<T> result = new Result<>();
    result.setMessage(message);
    result.setData(data);
    result.setCode(200);
    return result;
  }


  public static <T> Result<T> error(String message) {
    Result<T> result = new Result<>();
    result.setMessage(message);
    result.setCode(500);
    return result;
  }

  public static <T> Result<T> error(String message, Integer code) {
    Result<T> result = new Result<>();
    result.setMessage(message);
    result.setCode(code);
    return result;
  }

  public static <T> Result<T> error(T data, String message, Integer code) {
    Result<T> result = new Result<>();
    result.setData(data);
    result.setMessage(message);
    result.setCode(code);
    return result;
  }
}
