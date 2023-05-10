package com.volta.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.mapper.MoodLikeMapper;
import com.volta.project.mapper.MoodMapper;
import com.volta.project.model.entity.Mood;
import com.volta.project.model.entity.MoodLike;
import com.volta.project.service.MoodLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

import static com.volta.project.common.ErrorCode.LIKE_ERROR;
import static com.volta.project.common.ErrorCode.PARAMS_ERROR;

@Service
@Slf4j
public class MoodLikeServiceImpl implements MoodLikeService {
    @Resource
    private MoodLikeMapper mapper;
    @Resource
    private MoodMapper mapper1;
    @Override
    public BaseResponse save(int token, Long mid) {
        //验证
        if(mid>0){
            //查询有没有过点赞
            QueryWrapper<MoodLike> queryWrapper=new QueryWrapper<>();
            QueryWrapper<Mood> queryWrapper1=new QueryWrapper<>();
            queryWrapper.eq("userId",token);
            queryWrapper.eq("mid",mid);
            queryWrapper1.eq("id",mid);
            //查询数据库
            MoodLike moodLike=mapper.selectOne(queryWrapper);
            Mood mood=mapper1.selectOne(queryWrapper1);
            //验证是否存在/
            if(moodLike==null){
                //点赞
                moodLike=new MoodLike();
                moodLike.setTime(new Date());
                moodLike.setUserId(token);
                moodLike.setMid(mid);
                if(mapper.insert(moodLike)>0&&mood!=null){
                    mood.setLikeNum((int) mapper.selectNums(mid));
                    mapper1.updateById(mood);
//                    System.out.println("添加完毕");
                    return ResultUtils.success(true);
                }
            }else{
                //删除
                if(mapper.deleteById(moodLike.getId())>0&&mood!=null){
//                    mood.setLike_num(mood.getLike_num()-1);
                    mood.setLikeNum((int) mapper.selectNums(mid));
                    mapper1.updateById(mood);
//                    System.out.println("取消完毕");
                    return ResultUtils.success(true);
                }
            }
        }
        return ResultUtils.error(LIKE_ERROR);
    }

    @Override
    public  BaseResponse queryList(long mid) {
        if(mid>0){

            return ResultUtils.success(mapper.selectNums(mid));
        }
        return ResultUtils.error(PARAMS_ERROR);
    }

    @Override
    public BaseResponse queryMy(int token) {
        QueryWrapper<MoodLike> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("userId",token);
        queryWrapper.orderByDesc("time");
        return ResultUtils.success(mapper.selectList(queryWrapper));
    }

}
