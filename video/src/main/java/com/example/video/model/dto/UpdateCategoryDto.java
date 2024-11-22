package com.example.video.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @TableName category
 */
@Data
public class UpdateCategoryDto {
    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空")
    private Long id;

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
