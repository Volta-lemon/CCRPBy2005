package com.volta.project.service;


import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;

public interface AppArtifactService{
    /**
     * 所有动态
     */
    BaseResponse page(int page, int size);
}
