package com.volta.project.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "artifact")
@Data
@ToString
public class AppArtifact implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("artifactName")
    private String artifactName;
    private String author;
    @TableField("relicTime")
    private String relicTime;
    private String description;
    @TableField("imageUrl")
    private String imageUrl;
    @TableField("moreUrl")
    private String moreUrl;
    @TableField("collectNum")
    private Integer collectNum;
    @TableField("charaValue")
    private String charaValue;

    public String getMoreUrl() {
        return moreUrl;
    }

    public String getCharaValue() {
        return charaValue;
    }
}
