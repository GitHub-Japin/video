package com.example.video.common;

import com.example.video.constant.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Japin
 */
@Slf4j
@ResponseBody
@ControllerAdvice
@RestControllerAdvice(basePackages = "com.japin.file")
public class GlobalExceptionHandler {
    /**
     * 运行时异常处理
     *
     * @param e 异常
     * @return 结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = RuntimeException.class)
    public Result<String> handler(RuntimeException e) {
        log.error("运行时异常处理{}", e.getMessage());
        return Result.error(ResultCodeEnum.INTERNAL_SERVER_ERROR.getMessage());
    }

    /**
     * 参数校验异常处理
     *
     * @param e 异常
     * @return 结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> validateErrorHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return Result.error(error.getDefaultMessage());
    }

    /**
     * 自定义异常处理
     *
     * @param e 异常
     * @return 结果
     */
    @ExceptionHandler(CustomException.class)
    public Result<String> handleDataAccessException(CustomException e) {
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }
}
