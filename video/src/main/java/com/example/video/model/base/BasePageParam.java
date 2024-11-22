package com.example.video.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author : Japin
 * @className : BasePageParam
 * @description : BasePageParam
 * @date: 2023-12-03 12:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BasePageParam {

    @Min(value = 1, message = "currentPage最小不能小于 1")
    @NotNull(message = "currentPage不能为空")
    private Integer currentPage;

    @Min(value = 1, message = "pageSize最小不能小于 1")
    @Max(value = 200, message = "pageSize最大不能超过 200")
    @NotNull(message = "pageSize不能为空")
    private Integer pageSize;
}
