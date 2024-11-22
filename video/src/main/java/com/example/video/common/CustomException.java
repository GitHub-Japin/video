package com.example.video.common;

/**
 * 自定义业务异常
 *
 * @author Japin
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
