package com.example.video.model.param;

import com.example.video.model.base.BaseListParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName category
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoListParam extends BaseListParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * id
     */
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
    private String desc;
    /**
     * 状态
     */
    private Integer state;
}
