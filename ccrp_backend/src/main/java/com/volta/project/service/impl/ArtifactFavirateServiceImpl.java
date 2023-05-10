package com.volta.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.mapper.AppArtifactMapper;
import com.volta.project.mapper.ArtifactFavirateMapper;
import com.volta.project.mapper.ArtifactMapper;
import com.volta.project.model.entity.AppArtifact;
import com.volta.project.model.entity.Artifact;
import com.volta.project.model.entity.ArtifactFavirate;
import com.volta.project.model.entity.MoodLike;
import com.volta.project.service.ArtifactFavirateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.volta.project.common.ErrorCode.NULL_ERROR;
import static com.volta.project.common.ErrorCode.SAVE_ERROR;

@Service
@Slf4j
public class ArtifactFavirateServiceImpl implements ArtifactFavirateService {
    @Resource
    private ArtifactFavirateMapper mapper;
    @Resource
    private AppArtifactMapper mapper1;
    @Override
    public BaseResponse save(int token, Long artifactId) {
        //验证
        if(artifactId>0){
            //查询有没有过收藏
            QueryWrapper<ArtifactFavirate> queryWrapper=new QueryWrapper<>();
            QueryWrapper<AppArtifact> queryWrapper1=new QueryWrapper<>();
            queryWrapper.eq("userId",token);
            queryWrapper.eq("artifactId",artifactId);
            queryWrapper1.eq("id",artifactId);
            //查询数据库
            ArtifactFavirate ArtifactFavirate=mapper.selectOne(queryWrapper);
            AppArtifact appartifact=mapper1.selectOne(queryWrapper1);
            //验证是否存在/
            if(ArtifactFavirate==null){
                //收藏
                ArtifactFavirate=new ArtifactFavirate();
                ArtifactFavirate.setFavoriteTime(new Date());
                ArtifactFavirate.setUserId(token);
                ArtifactFavirate.setArtifactId(artifactId);
                if(mapper.insert(ArtifactFavirate)>0){
                    appartifact.setCollectNum((int) mapper.selectNums(artifactId));
                    mapper1.updateById(appartifact);
                    return ResultUtils.success(true);
                }
            }else{
                //删除
                if(mapper.deleteById(ArtifactFavirate.getId())>0){
                    appartifact.setCollectNum((int) mapper.selectNums(artifactId));
                    mapper1.updateById(appartifact);
                    return ResultUtils.success(true);
                }
            }
        }
        return ResultUtils.error(SAVE_ERROR);
    }

    @Override
    public BaseResponse queryList(long artifactId) {
        if(artifactId>0){
            return ResultUtils.success(mapper.selectNums(artifactId));
        }
        return ResultUtils.error(NULL_ERROR);
    }
    @Override
    public BaseResponse queryMy(int token) {
        QueryWrapper<ArtifactFavirate> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",token);
        queryWrapper.orderByDesc("favoriteTime");
        return ResultUtils.success(mapper.selectList(queryWrapper));
    }
}
