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
@TableName("mood_app")
@ToString
public class Mood implements Serializable {
    @TableId(type= IdType.AUTO)
    private Long id;
    @TableField("userId")
    private Long userId;
    private String content;
    @TableField("moodTime")
    private Date moodTime;
    @TableField("likeNum")
    private Integer likeNum;

//    public String getContent() {
////        System.out.print(content);
//        return content;
//    }

    public Mood(){
//        this.flag=1;
        this.moodTime=new Date();
        //this.like_num= Long.valueOf(0);
    }

    public Integer getLikeNum() {
        return this.likeNum != null ? this.likeNum : 0;
    }
}
