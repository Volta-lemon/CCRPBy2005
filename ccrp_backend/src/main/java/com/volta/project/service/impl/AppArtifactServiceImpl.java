package com.volta.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.mapper.AppArtifactMapper;
import com.volta.project.model.entity.AppArtifact;
import com.volta.project.service.AppArtifactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AppArtifactServiceImpl implements AppArtifactService {
    @Resource
    private AppArtifactMapper mapper;
    @Override
    public BaseResponse page(int page, int size) {
        //1.验证
        if(page<1){
            page=1;
        }
        if(size<1){
            size=10;
        }
        //2.实例化分页对象
        Page<AppArtifact> p=new Page<>(page,size);
        QueryWrapper<AppArtifact> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("collectNum");
        return ResultUtils.success(mapper.selectPage(p,queryWrapper));
    }
}
