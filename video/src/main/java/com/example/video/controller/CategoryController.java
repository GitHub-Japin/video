package com.example.video.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.video.common.Result;
import com.example.video.model.entity.Category;
import com.example.video.model.entity.Video;
import com.example.video.service.CategoryService;
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
@RequestMapping("/v1/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    private Result<List<Category>> getList() {
        return Result.success(categoryService.list());
    }

    @GetMapping("/page")
    private Result<IPage<Category>> getPage() {
        return Result.success(categoryService.page(new Page<>(1, 10), null));
    }

    @GetMapping("/{id}")
    private Result<Category> getById(@PathVariable("id") String id) {
        return Result.success(categoryService.getById(id));
    }

    @DeleteMapping("/{id}")
    private Result<?> removeById(@PathVariable("id") String id) {
        return Result.success(categoryService.removeById(id));
    }

    @PostMapping
    private Result<?> add(@RequestBody Category category) {
        return Result.success(categoryService.save(category));
    }

    @PutMapping
    private Result<?> update(@RequestBody Category category) {
        return Result.success(categoryService.updateById(category));
    }
}
