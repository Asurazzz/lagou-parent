package com.lagou.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
@EntityScan("com.lagou.edu.pojo")
public class Lagou01ServiceResumeApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lagou01ServiceResumeApplication.class, args);
    }

}
