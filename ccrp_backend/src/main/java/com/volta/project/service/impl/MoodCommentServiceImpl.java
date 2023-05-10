package com.volta.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.mapper.MoodCommentMapper;
import com.volta.project.model.dto.MoodCommentAddDto;
import com.volta.project.model.entity.MoodComment;
import com.volta.project.service.MoodCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.volta.project.common.ErrorCode.PARAMS_ERROR;
import static com.volta.project.common.ErrorCode.SAVE_ERROR;

@Service
@Slf4j
public class MoodCommentServiceImpl implements MoodCommentService {
    @Resource
    MoodCommentMapper mapper;
    @Override
    public BaseResponse queryByMid(int mid) {
        //1.校验
        if(mid>0){
            QueryWrapper<MoodComment> queryWrapper=new QueryWrapper<>();
            queryWrapper.orderByDesc("time");
            return ResultUtils.success(mapper.selectList(queryWrapper));
        }
        return ResultUtils.error( PARAMS_ERROR);
    }

    @Override
    public BaseResponse save(MoodCommentAddDto dto, int token) {
        //1.校验
        if(dto!=null){
            //2.准备对象
            MoodComment comment=new MoodComment();
            comment.setContent(dto.getContent());
            comment.setMid(dto.getMid());
            //  BeanUtils.copyProperties(dto,comment);
            comment.setTime(new Date());
            // comment.setFlag(1);
            comment.setUserId(token);
            //添加数据到数据库
            if(mapper.insert(comment)>0){
                return ResultUtils.success(true);
            }
        }
        return ResultUtils.error(SAVE_ERROR);
    }
}
