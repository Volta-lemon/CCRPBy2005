package com.volta.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volta.project.model.entity.ArtifactFavirate;
import org.apache.ibatis.annotations.Select;

public interface ArtifactFavirateMapper extends BaseMapper<ArtifactFavirate> {
    @Select("select count(*) from favorite where artifactId=#{artifactId}")
    long selectNums(long artifactId);
}
