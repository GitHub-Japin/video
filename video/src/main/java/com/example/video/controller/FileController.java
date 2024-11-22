package com.example.video.controller;

import com.example.video.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author : Japin
 * @className : FileController
 * @description : FileController
 * @date: 2024-11-21 09:44
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/file")
public class FileController {

    @PostMapping("/uploadFile")
    private Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // 校验文件是否为空
        if (file.isEmpty()) {
            return Result.error("上传失败，文件为空");
        }

        try {
            // 保存文件到服务器路径
            String uploadDir = "E:\\aliyun_workspace\\";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File destFile = new File(uploadDir + fileName);
            file.transferTo(destFile);

            // 返回文件URL
            String fileUrl = "http://localhost:80/v1/file/" + fileName;
            return Result.success(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("文件上传失败：" + e.getMessage());
        }
    }
}
