package com.example.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.video.model.entity.Video;
import com.example.video.service.VideoService;
import com.example.video.dao.VideoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}




