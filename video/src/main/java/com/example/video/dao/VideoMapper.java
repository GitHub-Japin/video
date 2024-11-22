package com.example.video.dao;

import com.example.video.model.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Japin
 * @description 针对表【video】的数据库操作Mapper
 * @createDate 2024-11-21 09:24:15
 * @Entity com.example.video.model.entity.Video
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {

}




