package com.edu.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author yemingjie
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication9002.class, args);
    }
}
