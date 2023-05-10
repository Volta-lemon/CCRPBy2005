package com.volta.project.controller;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.service.AppArtifactService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/appartifact/")
@Api(tags = "APP文物首页展示接口")
public class AppArtifactController {
    @Resource
    private AppArtifactService service;
    @GetMapping("all.do")//所有
    @ApiOperation(value = "输入分页信息获取文物")
    public BaseResponse all(int page, int size){ return ResultUtils.success(service.page(page, size));}
}
