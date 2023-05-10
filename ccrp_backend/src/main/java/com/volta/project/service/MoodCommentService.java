package com.volta.project.service;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.PageUtils;
import com.volta.project.common.ResultUtils;
import com.volta.project.model.dto.MoodCommentAddDto;

public interface MoodCommentService {
    /**
     * 查询指定动态的评论列表 时间倒序
     */
    BaseResponse queryByMid(int mid);
    /**
     * 发表评论
     */
    BaseResponse save(MoodCommentAddDto dto, int token);
}
