package com.example.video.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.video.model.base.BaseEntity;
import com.example.video.model.base.BasePageParam;
import com.example.video.model.base.BaseViewVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName category
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryVo extends BaseViewVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 分类id
     */
    private Long id;
    /**
     * 分类名
     */
    private String name;
    /**
     * 备注
     */
    private String desc;
}
