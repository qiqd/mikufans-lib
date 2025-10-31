package org.mikufans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mikufans.entity.Game;

/**
 * Game数据访问层接口
 * 提供游戏实体的数据库操作方法
 */
@Mapper
public interface GameMapper extends BaseMapper<Game> {
  // BaseMapper已经提供了基本的CRUD操作
  // 如果需要自定义SQL查询，可以在此接口中定义
}