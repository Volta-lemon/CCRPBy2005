package com.volta.project.controller;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.config.SystemConfig;
import com.volta.project.service.MoodLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "APP帖子点赞相关接口")
@RequestMapping("/moodlike/")
@CrossOrigin
public class MoodLikeController {
    @Resource
    private MoodLikeService service;
    @GetMapping("save.do")//点赞和取消
    @ApiOperation(value = "输入点赞的帖子id，点一下点赞，第二下取消")
    public BaseResponse save(long mid, HttpServletRequest request){
        return ResultUtils.success(service.save(request.getIntHeader(SystemConfig.TOKEN_HEADER),mid));
    }
    @GetMapping("likes.do")//数量
    @ApiOperation(value = "输入帖子id，查看所有评论")
    public BaseResponse likes(long mid){
        return ResultUtils.success(service.queryList(mid));
    }
    @GetMapping("my.do")//数量
    @ApiOperation(value = "通过前端传过来的token，查看用户自己点赞的帖子")
    public BaseResponse my(HttpServletRequest request) {return ResultUtils.success(service.queryMy(request.getIntHeader(SystemConfig.TOKEN_HEADER)));}
}