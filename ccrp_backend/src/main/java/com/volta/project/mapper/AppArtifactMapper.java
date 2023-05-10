package com.volta.project.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.volta.project.model.entity.AppArtifact;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AppArtifactMapper extends BaseMapper<AppArtifact>{
    @Select("select id,artifactName,author,relicTime,description,imageUrl,moreUrl,collectNum,charaValue from artifact")
    List<AppArtifact> finaAll();
}
