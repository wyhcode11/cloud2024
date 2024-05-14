package com.atguigu.cloud;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.atguigu.cloud.mapper")
public class Main2003 {
    public static void main(String[] args) {
        SpringApplication.run(Main2003.class,args);
    }
}