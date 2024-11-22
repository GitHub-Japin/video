package com.example.video.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.video.dao.VideoMapper;
import com.example.video.model.entity.Video;
import com.example.video.model.param.VideoListParam;
import com.example.video.model.param.VideoParam;
import com.example.video.model.vo.VideoVo;
import com.example.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Japin
 * @description 针对表【video】的数据库操作Service实现
 * @createDate 2024-11-21 09:24:15
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = RuntimeException.class)
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
        implements VideoService {

    private final VideoMapper videoMapper;

    @Override
    public List<Video> getList(VideoListParam param) {
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select()
                .eq(param.getId() != null, Video::getId, param.getId())
                .eq(param.getCategoryId() != null, Video::getCategoryId, param.getCategoryId())
                .eq(param.getState() != null, Video::getState, param.getState())
                .eq(param.getUserId() != null, Video::getUserId, param.getUserId())
                .eq(StringUtils.hasLength(param.getTitle()), Video::getTitle, param.getTitle())
                .eq(StringUtils.hasLength(param.getDesc()), Video::getDesc, param.getDesc());
        queryWrapper.orderByDesc(Video::getId)
                .last(param.getLimit() != null, " limit " + param.getLimit());
        return videoMapper.selectList(queryWrapper);
    }

    @Override
    public Page<VideoVo> getPage(VideoParam param) {
        Page<VideoVo> resPage = new Page<>(param.getCurrentPage(), param.getPageSize());
        return videoMapper.selectLjPage(param, resPage);
    }
}




