package org.english.operation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.english.operation.utils.SpringContextUtil;

@SpringBootApplication
@MapperScan(basePackages = {"org.english.operation.mapper.base"})
@EnableAsync
@EnableScheduling
public class Application extends SpringBootServletInitializer{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
    

    public static void main(String[] args) {
        SpringContextUtil.setApplicationContext(SpringApplication.run(Application.class, args));
    }

}
