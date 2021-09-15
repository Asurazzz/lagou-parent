package com.ymj.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Classname AutodeliverApplicaton8096
 * @Description TODO
 * @Date 2021/9/15 15:59
 * @Created by yemingjie
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients // 开启Feign客户端功能
public class AutodeliverApplicaton8096 {
    public static void main(String[] args) {
        SpringApplication.run(AutodeliverApplicaton8096.class, args);
    }
}
