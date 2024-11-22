package com.example.video.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.video.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName video
 */
@Data
public class AddVideoDto {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
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
    private String desc;
    /**
     * 视频地址
     */
    private String videoUrl;
    /**
     * 缩略图地址
     */
    private String thumbnailUrl;
}
