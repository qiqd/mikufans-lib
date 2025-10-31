package org.mikufans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mikufans.entity.Comic;

/**
 * Comic数据访问层接口
 * 提供漫画实体的数据库操作方法
 */
@Mapper
public interface ComicMapper extends BaseMapper<Comic> {
  // BaseMapper已经提供了基本的CRUD操作
  // 如果需要自定义SQL查询，可以在此接口中定义
}