package com.volta.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volta.project.model.entity.MoodLike;
import org.apache.ibatis.annotations.Select;

public interface MoodLikeMapper extends BaseMapper<MoodLike> {
    @Select("select count(*) from mood_like where mid=#{mid}")
    long selectNums(long mid);
}
