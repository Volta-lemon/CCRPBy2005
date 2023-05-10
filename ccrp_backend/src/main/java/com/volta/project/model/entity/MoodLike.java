package com.volta.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("mood_like")
@ToString
public class MoodLike implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long mid;
    @TableField("userId")
    private Integer userId;
    private Date time;
}