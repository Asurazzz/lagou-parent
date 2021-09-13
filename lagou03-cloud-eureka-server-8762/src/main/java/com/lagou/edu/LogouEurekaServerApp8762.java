package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Classname LogouEurekaServerApp8761
 * @Description eureka-8761
 * @Date 2021/9/13 13:57
 * @Created by yemingjie
 */
@SpringBootApplication
// 声明当前项目为Eureka服务
@EnableEurekaServer
public class LogouEurekaServerApp8762 {
    public static void main(String[] args) {
        SpringApplication.run(LogouEurekaServerApp8762.class, args);
    }
}
