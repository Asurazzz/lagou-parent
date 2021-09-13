package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * service-autodeliver-8090
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class Lagou01ServiceAutodeliverApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lagou01ServiceAutodeliverApplication.class, args);
    }

}
