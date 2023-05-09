<<<<<<< HEAD
package com.volta.project.common;

/**
 * 错误码
 *
 * @author volta
 */
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部异常", ""),
    SAVE_ERROR(50010, "新增失败", ""),
    UPDATE_ERROR(50020, "更新失败", ""),
    DELETE_ERROR(50030, "删除失败", ""),
    LIKE_ERROR(50040,"点赞数为空","");
    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述（详情）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
=======
package com.volta.project.common;

/**
 * 错误码
 *
 * @author volta
 */
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "请求参数错误", ""),
    NULL_ERROR(40001, "请求数据为空", ""),
    NOT_LOGIN(40100, "未登录", ""),
    NO_AUTH(40101, "无权限", ""),
    SYSTEM_ERROR(50000, "系统内部异常", ""),
    SAVE_ERROR(50010, "新增失败", ""),
    UPDATE_ERROR(50020, "更新失败", ""),
    DELETE_ERROR(50030, "删除失败", ""),
    LIKE_ERROR(50040,"点赞数为空","");
    private final int code;

    /**
     * 状态码信息
     */
    private final String message;

    /**
     * 状态码描述（详情）
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
>>>>>>> 1e22f938e2eb3fa324401ee522c8f440cc7949de
