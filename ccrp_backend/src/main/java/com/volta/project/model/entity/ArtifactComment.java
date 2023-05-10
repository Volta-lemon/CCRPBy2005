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
@TableName("comment")
@ToString
public class ArtifactComment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("userId")
    private Integer userId;
    @TableField("artifactId")
    private Integer artifactId;
    private String content;
    private Date time;
    public ArtifactComment(){
        this.time=new Date();
    }
}
