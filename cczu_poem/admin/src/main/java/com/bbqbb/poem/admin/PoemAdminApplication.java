package com.bbqbb.poem.admin;

import com.bbqbb.poem.admin.modules.api.model.ConfigInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.bbqbb.poem.admin.modules.*.dao"})
@ComponentScan("com.bbqbb.poem")
@EnableConfigurationProperties
public class PoemAdminApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PoemAdminApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PoemAdminApplication.class);
    }
//    @Bean
//    @ConfigurationProperties(prefix = "my")
//    public ConfigInfo configInfo() {
//
//        return new ConfigInfo();
//    }
}