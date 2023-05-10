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
@TableName("comment_mood")
@ToString
public class MoodComment implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("userId")
    private Integer userId;
    private Integer mid;
    private String content;
    //  private Integer flag;
    // private long parentid;
    private Date time;
    public MoodComment(){
        //  this.flag=1;
        this.time=new Date();
    }
}