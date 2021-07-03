package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
// 声明本项⽬是⼀个Eureka服务
@EnableEurekaServer
@SpringBootApplication
public class Lagou02CloudEurekaServer8761Application {

    public static void main(String[] args) {
        SpringApplication.run(Lagou02CloudEurekaServer8761Application.class, args);
    }

}
