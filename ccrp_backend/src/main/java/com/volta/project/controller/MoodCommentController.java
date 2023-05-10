package com.volta.project.controller;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.config.SystemConfig;
import com.volta.project.model.dto.MoodCommentAddDto;
import com.volta.project.service.MoodCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "APP帖子评论相关接口")
@RequestMapping("/moodcomment/")
@CrossOrigin
public class MoodCommentController {
    @Resource
    private MoodCommentService service;
    @GetMapping("all.do")//
    @ApiOperation(value = "输入帖子id，获取帖子的所有评论")
    public BaseResponse all(int mid){return ResultUtils.success(service.queryByMid(mid));}
    @PostMapping("save.do")//发表评论
    @ApiOperation(value = "输入评论内容和帖子id，发表评论")
    public BaseResponse save(@RequestBody MoodCommentAddDto dto, HttpServletRequest request){
        return ResultUtils.success(service.save(dto, request.getIntHeader(SystemConfig.TOKEN_HEADER)));
    }}