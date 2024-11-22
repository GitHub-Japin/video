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
public class BaseListParam {

    @Min(value = 1, message = "列表长度最小不能小于 1")
    @NotNull(message = "列表最大长度不能为空")
    private Integer limit;
}
