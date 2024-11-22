package com.example.video.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.video.model.base.BaseViewVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @TableName category
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoVo extends BaseViewVO implements Serializable {
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
