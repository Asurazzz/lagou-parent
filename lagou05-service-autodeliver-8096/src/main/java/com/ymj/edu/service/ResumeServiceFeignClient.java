package com.ymj.edu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname ResumeServiceFeignClient
 * @Description @FeignClient表明当前类是一个Feign客户端，value指定客户端要请求的服务名称（登记到注册中心上的的服务提供者的服务名称）
 * @Date 2021/9/15 16:17
 * @Created by yemingjie
 */
@FeignClient(value = "lagou-service-resume", fallback = ResumeFallback.class, path = "/resume")
//@RequestMapping("/resume")
public interface ResumeServiceFeignClient {

    // Feign要做的事就是拼装url发起请求
    @GetMapping("/openstate/{userId}")
    Integer findDefaultResumeState(@PathVariable("userId") Long userId);
}
