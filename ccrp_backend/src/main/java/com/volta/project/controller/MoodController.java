package com.volta.project.controller;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.config.SystemConfig;
import com.volta.project.model.dto.MoodAddDto;
import com.volta.project.service.MoodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mood/")
@Api(tags = "APP帖子相关接口")
@CrossOrigin
public class MoodController {
    @Resource
    private MoodService service;
    @PostMapping("add.do")//发帖
    @ApiOperation(value = "输入帖子内容发帖")
    public BaseResponse add(@RequestBody MoodAddDto dto, HttpServletRequest request){
        return ResultUtils.success(service.save(dto,request.getIntHeader(SystemConfig.TOKEN_HEADER)));
    }
    @GetMapping("my.do")//我的
    @ApiOperation(value = "通过前端传过来的token，查看用户自己发出的帖子")
    public BaseResponse my(HttpServletRequest request){
        return ResultUtils.success(service.queryMy(request.getIntHeader(SystemConfig.TOKEN_HEADER)));
    }
    @GetMapping("allbylikenum.do")//所有
    @ApiOperation(value = "输入page和size，分页查询所有帖子(最热版本)")
    public BaseResponse allbylikenum(int page,int size){ return ResultUtils.success(service.page(page, size));}
    @GetMapping("allbytime.do")//所有
    @ApiOperation(value = "输入page和size，分页查询所有帖子(最新版本)")
    public BaseResponse allbytime(int page,int size){ return ResultUtils.success(service.page1(page, size));}
}