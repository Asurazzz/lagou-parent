package com.ymj.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin2.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApplication9411 {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication9411.class, args);
    }
}
