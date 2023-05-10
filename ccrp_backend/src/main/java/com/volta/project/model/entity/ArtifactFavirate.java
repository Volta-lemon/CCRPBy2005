package com.volta.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("favorite")
public class ArtifactFavirate implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("userId")
    private long userId;
    @TableField("artifactId")
    private long artifactId;
    @TableField("favoriteTime")
    private Date favoriteTime;
}
