package com.volta.project.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 文物
 *
 * @TableName Artifact
 */
@TableName(value = "artifact")
@Data
@ToString
public class Artifact implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String artifactName;
    private String author;
    private String relicTime;
    private String description;
    private String imageUrl;
    private String moreUrl;
    private Date createTime;
    private Date updateTime;
    private Integer isDelete;
}