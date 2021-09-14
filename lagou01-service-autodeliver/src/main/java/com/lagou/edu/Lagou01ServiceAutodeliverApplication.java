package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * service-autodeliver-8090
 *
 *   SpringCloudApplication 综合性的注解
 * = SpringBootApplication + EnableDiscoveryClient + EnableCircuitBreaker
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
//@EnableHystrix // 开启hystrix功能
@EnableCircuitBreaker // 开启熔断器功能
public class Lagou01ServiceAutodeliverApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lagou01ServiceAutodeliverApplication.class, args);
    }

}
