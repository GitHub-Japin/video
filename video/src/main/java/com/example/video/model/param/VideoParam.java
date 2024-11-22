package com.example.video.model.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.video.model.base.BasePageParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName category
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoParam extends BasePageParam implements Serializable {
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
