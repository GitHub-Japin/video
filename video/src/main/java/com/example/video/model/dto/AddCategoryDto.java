package com.example.video.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @TableName category
 */
@Data
public class AddCategoryDto {

    /**
     * 分类名
     */
    @NotBlank(message = "分类名不能为空")
    private String name;

    /**
     * 备注
     */
    private String desc;
}
