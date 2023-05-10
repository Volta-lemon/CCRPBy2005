package com.volta.project.controller;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.config.SystemConfig;
import com.volta.project.service.ArtifactFavirateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/artifactFavorite/")
@Api(tags = "APP文物收藏接口")
public class ArtifactFavirateController {
    @Resource
    private ArtifactFavirateService service;
    @GetMapping("save.do")//收藏和取消
    @ApiOperation(value = "输入收藏的文物id，点一下收藏，第二下取消")
    public BaseResponse save(long artifactId, HttpServletRequest request){
        return ResultUtils.success(service.save(request.getIntHeader(SystemConfig.TOKEN_HEADER),artifactId));
    }
    @GetMapping("collects.do")//收藏数量
    @ApiOperation(value = "输入收藏的文物id，获取收藏数量")
    public BaseResponse collect(long artifactId){
        return ResultUtils.success(service.queryList(artifactId));
    }
    @GetMapping("my.do")//数量
    @ApiOperation(value = "通过前端传过来的token，查看用户自己收藏的文物")
    public BaseResponse my(HttpServletRequest request) {return ResultUtils.success(service.queryMy(request.getIntHeader(SystemConfig.TOKEN_HEADER)));}
}
