package com.example.video.interceptor;

import com.example.video.common.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Japin
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MyInterceptor implements HandlerInterceptor {

    public static final String TOKEN_HEADER = "Authorization";
    private static final int UNAUTHORIZED_STATUS = HttpServletResponse.SC_UNAUTHORIZED;
    private static final String UNAUTHORIZED_MESSAGE = "拒绝访问，请登录";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle============================>");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle============================>");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion============================>");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 统一响应
     *
     * @param response response
     * @param code     响应码
     * @param message  消息
     * @return boolean
     */
    private boolean sendResponseAndAbort(HttpServletResponse response, int code, String message) throws IOException {
        Result<Object> vo = new Result<>();
        vo.setCode(code);
        vo.setMsg(message);
        response.setStatus(code);
        response.setContentType("application/json;charset=utf-8");

        try (PrintWriter writer = response.getWriter()) {
            String json = new ObjectMapper().writeValueAsString(vo);
            writer.println(json);
        }

        return false;
    }
}
