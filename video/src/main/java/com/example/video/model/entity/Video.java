package com.example.video.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.example.video.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName video
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "video")
@Data
public class Video extends BaseEntity implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 分类id
     */
    private Long categoryId;
    /**
     * 名字
     */
    private String title;
    /**
     * 备注
     */
    @TableField(value = "`desc`")
    private String desc;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 缩略图地址
     */
    private String thumbnailUrl;
    /**
     * 状态
     */
    private Integer state;
}
