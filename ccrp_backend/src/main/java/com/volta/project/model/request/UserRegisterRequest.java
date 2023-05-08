package com.volta.project.model.request;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 用户注册请求体
 *
 * @author volta
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;//非空

    private String userPassword;//非空

    private String checkPassword;//非空且和userPassword一样

    private String userName;

    private String avatarUrl;

    private String phone;

    private String id_card;

    private String email;

    private String dian_zan;

    private String ping_lun;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}
