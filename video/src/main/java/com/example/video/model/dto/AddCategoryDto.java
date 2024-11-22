package com.example.video.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.video.model.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @TableName category
 */
@Data
public class AddCategoryDto {

    /**
     * 分类名
     */
    private String name;

    /**
     * 备注
     */
    private String desc;
}
