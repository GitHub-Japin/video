package com.example.video.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.video.common.Result;
import com.example.video.model.entity.Video;
import com.example.video.model.param.VideoListParam;
import com.example.video.model.param.VideoParam;
import com.example.video.model.vo.VideoVo;
import com.example.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Japin
 * @className : VideoController
 * @description : VideoController
 * @date: 2024-11-21 09:26
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/video")
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/all")
    private Result<List<Video>> getList(VideoListParam param) {
        return Result.success(videoService.getList(param));
    }

    @GetMapping("/page")
    private Result<IPage<VideoVo>> getPage(VideoParam param) {
        return Result.success(videoService.getPage(param));
    }

    @GetMapping("/{id}")
    private Result<Video> getById(@PathVariable("id") String id) {
        return Result.success(videoService.getById(id));
    }

    @DeleteMapping("/{id}")
    private Result<?> removeById(@PathVariable("id") String id) {
        return Result.success(videoService.removeById(id));
    }

    @PostMapping
    private Result<?> add(@RequestBody Video video) {
        return Result.success(videoService.save(video));
    }

    @PutMapping
    private Result<?> update(@RequestBody Video video) {
        return Result.success(videoService.updateById(video));
    }
}
