package com.example.video.common;

import com.example.video.constant.ResultCodeEnum;
import lombok.Data;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Japin
 */
@Data
public class Result<T> implements Serializable {

    //    @Schema(description = "响应编码:200-请求处理成功，400-失败")
    private Integer code;

    //    @Schema(description = "响应消息")
    private String msg;

    //    @Schema(description = "响应数据")
    private T data;

    //    @Schema(description = "时间,格式yyyy-MM-dd HH:mm:ss")
    private String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

    /**
     * 成功处理
     */
    public static <T> Result<T> success(T data) {
        Result<T> res = new Result<>();
        res.data = data;
        res.msg = ResultCodeEnum.SUCCESS.getMessage();
        res.code = ResultCodeEnum.SUCCESS.getCode();
        return res;
    }

    /**
     * 成功处理
     */
    public static <T> Result<T> success() {
        Result<T> res = new Result<>();
        res.msg = ResultCodeEnum.SUCCESS.getMessage();
        res.code = ResultCodeEnum.SUCCESS.getCode();
        return res;
    }

    /**
     * 异常处理
     */
    public static <T> Result<T> error() {
        Result<T> res = new Result<>();
        res.msg = ResultCodeEnum.ERROR.getMessage();
        res.code = ResultCodeEnum.ERROR.getCode();
        return res;
    }

    /**
     * 异常处理
     */
    public static <T> Result<T> error(String msg) {
        Result<T> res = new Result<>();
        res.msg = msg;
        res.code = ResultCodeEnum.ERROR.getCode();
        return res;
    }

    /**
     * 异常处理
     */
    public static <T> Result<T> fail(int code, String msg, T data) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

}
