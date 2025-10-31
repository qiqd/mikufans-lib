package org.mikufans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.mikufans.entity.Character;

/**
 * ACGCharacter实体类的Mapper接口
 * 继承BaseMapper获得常用的CRUD操作方法
 */
@Mapper
public interface CharacterMapper extends BaseMapper<Character> {
  // 可以在此添加自定义的SQL查询方法
}
