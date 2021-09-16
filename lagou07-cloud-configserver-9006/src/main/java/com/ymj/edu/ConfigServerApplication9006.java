package com.ymj.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Classname ConfigServerApplication9006
 * @Description TODO
 * @Date 2021/9/16 14:50
 * @Created by yemingjie
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer // 开启配置中心服务
public class ConfigServerApplication9006 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication9006.class, args);
    }
}
