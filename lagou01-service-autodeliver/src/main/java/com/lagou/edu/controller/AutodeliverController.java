package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Classname AutodeliverController
 * @Description TODO
 * @Date 2021/7/2 11:41
 * @Created by yemingjie
 */
@RestController
@RequestMapping("/autodeliver")
public class AutodeliverController {
    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping("/checkState/{userId}")
//    public Integer findResumeOpenState(@PathVariable Long userId) {
//        Integer forObject =
//                restTemplate.getForObject("http://localhost:8080/resume/openstate/"
//                        + userId, Integer.class);
//        System.out.println("======>>>调⽤简历微服务，获取到⽤户" +
//                userId + "的默认简历当前状态为：" + forObject);
//        return forObject;
//    }

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 改造
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 从Eureka Server获取我们关注的那个服务实例信息以及接口信息
        // 1.从Eureka Server获取lagou-service-resume服务的实例信息（使用客户端对象）
        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");
        // 2.如果有多个实例，就选择一个使用
        ServiceInstance serviceInstance = instances.get(0);
        // 3.从元数据信息获取host port
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;
        System.out.println("=====================" + url + "======================");
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
}
