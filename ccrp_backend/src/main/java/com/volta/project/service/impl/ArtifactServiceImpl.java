package com.volta.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.volta.project.common.PageUtils;
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
    public Artifact getSimilarArtifacts(Integer id) {
        Artifact artifact=artifactMapper.selectById(id);
        return null;
    }

    @Override
    public PageUtils getArtifactByDetail(int curPage,int pageSize, Integer startTime, Integer endTime, Integer isAscend, String author, String artifactName) {

/*        if(startTime>endTime){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }*/
/*        if(isAscend!=0&&isAscend!=1&&isAscend!=-1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }*/
        QueryWrapper<Artifact> wrapper=new QueryWrapper<>();
        if(author!=null)
            wrapper.eq("author",author);
        if(artifactName!=null)
            wrapper.eq("artifactName",artifactName);
        System.out.println(artifactName+" "+artifactName);
        if(startTime!=null&&endTime!=null)
            wrapper.between("relicTime",startTime,endTime);
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
}
