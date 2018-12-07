package com.cczu.spider.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.DispatcherType;

@Configuration
public class FilterConfig implements HandlerInterceptor {

    @Bean
    public boolean donothing(){
        System.out.println("donothing");
        return false;
    }
//    @Bean
//    public FilterRegistrationBean xssFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setDispatcherTypes(DispatcherType.REQUEST);
//        registration.setFilter(new HandlerInterceptorConfig());
//        registration.addUrlPatterns("/*");
//        registration.setName("xssFilter");
//        registration.setOrder(Integer.MAX_VALUE);
//        return registration;
//    }
}

