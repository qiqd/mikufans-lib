package org.mikufans.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 游戏与类型多对多关联表实体类
 */
@Data
public class GameToGenre {
  /**
   * 主键，自增ID
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 游戏ID
   */
  private Long gameId;

  /**
   * 类型ID
   */
  private Long genreId;
}