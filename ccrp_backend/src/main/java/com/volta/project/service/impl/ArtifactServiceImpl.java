package com.volta.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volta.project.common.ErrorCode;
import com.volta.project.common.PageUtils;
import com.volta.project.controller.exception.BusinessException;
import com.volta.project.mapper.ArtifactMapper;
import com.volta.project.model.entity.Artifact;
import com.volta.project.service.ArtifactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class ArtifactServiceImpl extends ServiceImpl<ArtifactMapper,Artifact>
    implements ArtifactService
{
    @Resource
    private ArtifactMapper artifactMapper;
    @Override
    public Artifact getArtifactById(Integer id) {
        Artifact artifact=artifactMapper.selectById(id);
        return artifact;
    }

    @Override
    public PageUtils getSimilarArtifacts(int curPage,int pageSize,Integer id) {
        if(id==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Artifact artifact=artifactMapper.selectById(id);
        QueryWrapper<Artifact> wrapper=new QueryWrapper<>();

        wrapper.ne("id",id).
                and(i->i.like("artifactName",artifact.getArtifactName()).or()
                .like("author",artifact.getAuthor()));
        Page<Artifact>page=new Page<>(curPage,pageSize);
        Page<Artifact>result=artifactMapper.selectPage(page,wrapper);
        return new PageUtils(result);
    }

    @Override
    public PageUtils getArtifactsByDetail(int curPage,int pageSize, Integer startTime, Integer endTime, Integer isAscend, String author, String artifactName) {
        QueryWrapper<Artifact> wrapper=new QueryWrapper<>();
        if(author!=null)
            wrapper.like("author",author);
        if(artifactName!=null)
            wrapper.like("artifactName",artifactName);
//        if(startTime!=null&&endTime!=null)
//            wrapper.between("relicTime",startTime,endTime);
        if(isAscend!=null&&(isAscend==1||isAscend==-1)){
            if(isAscend==1)
                wrapper.orderByAsc("relicTime");
            else if(isAscend==0)
                wrapper.orderByDesc("relicTime");
        }
        Page<Artifact>page=new Page<>(curPage,pageSize);
        Page<Artifact>result=artifactMapper.selectPage(page,wrapper);
        return new PageUtils(result);
    }

    @Override
    public PageUtils searchArtifactByKeyword(int curPage, int pageSize, String keyword) {
        QueryWrapper<Artifact> wrapper=new QueryWrapper<>();
        wrapper.like("artifactName",keyword).or()
                .like("author",keyword).or()
                .like("description",keyword);
        Page<Artifact>page=new Page<>(curPage,pageSize);
        Page<Artifact>result=artifactMapper.selectPage(page,wrapper);
        return new PageUtils(result);
    }

}
