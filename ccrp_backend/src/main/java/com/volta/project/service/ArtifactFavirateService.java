package com.volta.project.service;

import com.volta.project.common.BaseResponse;
import com.volta.project.common.ResultUtils;

public interface ArtifactFavirateService {
    /**
     * 收藏
     */
    BaseResponse save(int token, Long artifactId);
    /**
     * 收藏数量
     */
    BaseResponse queryList(long artifactId);
    /*
我收藏的
 */
    BaseResponse queryMy(int token);
}
