package com.volta.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.mapper.MoodMapper;
import com.volta.project.model.dto.MoodAddDto;
import com.volta.project.model.entity.Mood;
import com.volta.project.service.MoodService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.volta.project.common.ErrorCode.NULL_ERROR;

@Service
@Slf4j
public class MoodServiceImpl implements MoodService {
    @Resource
    MoodMapper mapper;
    @Override
    public BaseResponse save(MoodAddDto dto, int token) {
        //验证参数
        if(dto!=null){
            //准备对象 添加数据库
            Mood mood=new Mood();
            mood.setContent(dto.getContent());
            //BeanUtils.copyProperties(dto,mood);
            mood.setUserId((long) token);
            if(mapper.insert(mood)>0){
                return ResultUtils.success(true);
            }
        }
        return ResultUtils.error(NULL_ERROR);
    }

    @Override
    public BaseResponse queryMy(int token) {
        QueryWrapper<Mood> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",token);
        queryWrapper.orderByDesc("likeNum");

        return ResultUtils.success(mapper.selectList(queryWrapper));
    }

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
        Page<Mood> p=new Page<>(page,size);
//        Mood_app mood=new Mood_app();
//        mood.getLike_num();
        QueryWrapper<Mood> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("likeNum");
        return ResultUtils.success(mapper.selectPage(p,queryWrapper));
    }
    @Override
    public BaseResponse page1(int page, int size) {
        //1.验证
        if(page<1){
            page=1;
        }
        if(size<1){
            size=10;
        }
        //2.实例化分页对象
        Page<Mood> p=new Page<>(page,size);
//        Mood_app mood=new Mood_app();
//        mood.getLike_num();
        QueryWrapper<Mood> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("moodTime");
        return ResultUtils.success(mapper.selectPage(p,queryWrapper));
    }
}
