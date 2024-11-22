package com.example.video.config;

import com.example.video.common.JacksonObjectMapper;
import com.example.video.interceptor.MyInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * @author Japin
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class SpringMvcConfig implements WebMvcConfigurer {

    private final MyInterceptor myInterceptor;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器启动...");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中，级别调整为0 最高，项目启动时最先启动我们重写的消息转换器。
        converters.add(0, messageConverter);
    }

    /**
     * 拦截器
     *
     * @param registry 拦截注册
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor)
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/v2/**")
                .excludePathPatterns("/swagger-ui.html/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/account/*")
                .excludePathPatterns("/file/download/**")
                .excludePathPatterns("/file/url/**")
                .excludePathPatterns("/file/page")
                .excludePathPatterns("/file/qrCode/**")
                .excludePathPatterns("/comments/list")
                .excludePathPatterns("/comments/getPageWithNotLogin")
                .excludePathPatterns("/sse/**")
                .excludePathPatterns("/advertiseNotice/**");
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 跨域配置  所有接口 是否发送 Cookie 支持域 支持方法
     *
     * @param registry 跨域注册
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
