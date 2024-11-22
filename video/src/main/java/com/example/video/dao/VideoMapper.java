package com.example.video.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.video.model.entity.Video;
import com.example.video.model.param.VideoParam;
import com.example.video.model.vo.VideoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Japin
 * @description 针对表【video】的数据库操作Mapper
 * @createDate 2024-11-21 09:24:15
 * @Entity com.example.video.model.entity.Video
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

    /**
     * 联表分页
     *
     * @param param param
     * @param page  结果
     * @return 分页
     */
    Page<VideoVo> selectLjPage(@Param("param") VideoParam param, @Param("page") Page<VideoVo> page);
}




