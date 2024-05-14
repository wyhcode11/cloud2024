package com.atguigu.cloud.config;

import feign.Logger;
import feign.RetryableException;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.util.retry.Retry;

@Configuration
public class FeignConfig {
    @Bean
    public Retryer myRetryer() {
        return Retryer.NEVER_RETRY;
        //return new Retryer.Default(100,1,3);
    }
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}