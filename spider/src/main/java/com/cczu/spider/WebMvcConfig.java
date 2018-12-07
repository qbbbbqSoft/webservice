package com.cczu.spider;

import com.cczu.spider.config.HandlerInterceptorConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Value("${FILE_PATH}")
    private String FilePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println(FilePath);
        registry.addResourceHandler("/file/**").addResourceLocations("file:"+FilePath);
        super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorConfig()).addPathPatterns("/cczu/**");
        super.addInterceptors(registry);
    }
}
