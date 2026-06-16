package com.neuedu;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableFeignClients
@MapperScan("com.neuedu.mapper")
@EnableCaching

public class BaseApp {
    public static void main(String[] args) {
        SpringApplication.run(BaseApp.class, args);
    }
}
