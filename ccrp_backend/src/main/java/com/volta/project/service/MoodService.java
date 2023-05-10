package com.volta.project.service;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;
import com.volta.project.model.dto.MoodAddDto;

public interface MoodService{
    /**
     * 发布动态
     */
    BaseResponse save(MoodAddDto dto, int token);
    /**
     * 我的动态
     */
    BaseResponse queryMy(int token);
    /**
     * 所有动态(按点赞数)
     */
    BaseResponse page(int page,int size);
    /**
     * 所有动态(按时间)
     */
    BaseResponse page1(int page,int size);
}
