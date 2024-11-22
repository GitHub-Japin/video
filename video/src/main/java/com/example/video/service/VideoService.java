package com.example.video.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.video.model.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.video.model.param.VideoListParam;
import com.example.video.model.param.VideoParam;
import com.example.video.model.vo.VideoVo;

import java.util.List;

/**
 * @author Japin
 * @description 针对表【video】的数据库操作Service
 * @createDate 2024-11-21 09:24:15
 */
public interface VideoService extends IService<Video> {

    /**
     * 获取列表
     *
     * @param param 参数
     * @return list
     */
    List<Video> getList(VideoListParam param);

    /**
     * 获取分页
     *
     * @param param 参数
     * @return page
     */
    Page<VideoVo> getPage(VideoParam param);
}
