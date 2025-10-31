package org.mikufans.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mikufans.entity.Animation;

import java.util.List;

@Mapper
public interface AnimationMapper extends BaseMapper<Animation> {

    /**
     * 根据动画制作公司查询动画
     * 
     * @param studio 动画制作公司
     * @return 动画列表
     */
    List<Animation> selectByStudio(@Param("studio") String studio);

    /**
     * 根据导演查询动画
     * 
     * @param director 导演
     * @return 动画列表
     */
    List<Animation> selectByDirector(@Param("director") String director);

    /**
     * 根据播放平台查询动画
     * 
     * @param platform 播放平台
     * @return 动画列表
     */
    List<Animation> selectByBroadcastPlatform(@Param("platform") String platform);
}
