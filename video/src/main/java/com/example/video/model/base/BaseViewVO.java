package com.example.video.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author : Japin
 * @className : BaseViewVO
 * @description : BaseViewVO
 * @date: 2023-12-03 12:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseViewVO {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedTime;
}
