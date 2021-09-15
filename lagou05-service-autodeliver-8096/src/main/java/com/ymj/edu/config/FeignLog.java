package com.ymj.edu.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname FeignLog
 * @Description TODO
 * @Date 2021/9/15 17:32
 * @Created by yemingjie
 */
@Configuration
public class FeignLog {
    @Bean
    Logger.Level feignLevel() {
        return Logger.Level.FULL;
    }
}
