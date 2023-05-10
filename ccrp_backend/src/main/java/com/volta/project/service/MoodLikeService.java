package com.volta.project.service;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.PageUtils;
import com.volta.project.common.ResultUtils;

public interface MoodLikeService {
    /**
     * 点赞
     */
    BaseResponse save(int token, Long mid);
    /**
     * 点赞数量
     */
    BaseResponse queryList(long mid);
/*
我点赞的
 */
    BaseResponse queryMy(int token);
}
