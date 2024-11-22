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
 * @TableName category
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "category")
@Data
public class Category extends BaseEntity {
    /**
     * 分类id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分类名
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 备注
     */
    @TableField(value = "`desc`")
    private String desc;
}
