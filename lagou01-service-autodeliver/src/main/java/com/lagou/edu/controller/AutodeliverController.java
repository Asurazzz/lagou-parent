package com.lagou.edu.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
     * 服务注册到Eureka之后的改造
     * @param userId
     * @return
     */
//    @GetMapping("/checkState/{userId}")
//    public Integer findResumeOpenState(@PathVariable Long userId) {
//        // 从Eureka Server获取我们关注的那个服务实例信息以及接口信息
//        // 1.从Eureka Server获取lagou-service-resume服务的实例信息（使用客户端对象）
//        List<ServiceInstance> instances = discoveryClient.getInstances("lagou-service-resume");
//        // 2.如果有多个实例，就选择一个使用
//        ServiceInstance serviceInstance = instances.get(0);
//        // 3.从元数据信息获取host port
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//        String url = "http://" + host + ":" + port + "/resume/openstate/" + userId;
//        System.out.println("=====================" + url + "======================");
//        Integer forObject = restTemplate.getForObject(url, Integer.class);
//        return forObject;
//    }


    /**
     * 使用Ribbon负载均衡
     * @param userId
     * @return
     */
    @GetMapping("/checkState/{userId}")
    public Integer findResumeOpenState(@PathVariable Long userId) {
        // 使用ribbon不需要我们获取服务实例然后选择一个去访问了
        // 指定服务名
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        System.out.println("=====================" + url + "======================");
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }


    /**
     * 使用histrix模拟超时
     * @param userId
     * @return
     */
    @HystrixCommand(
            // 熔断的一些细节属性配置
            commandProperties = {
                    // 每一个属性都是HystrixProperty
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
    )
    @GetMapping("/checkStateTimeOut/{userId}")
    public Integer findResumeOpenStateTimeOut(@PathVariable Long userId) {
        // 使用ribbon不需要我们获取服务实例然后选择一个去访问了
        // 指定服务名
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        System.out.println("=====================" + url + "======================");
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }


    /**
     * 1) 服务提供者处理超时，熔断，返回错误信息
     * 2) 有可能服务提供者出现异常直接抛出异常信息
     *
     * 以上信息，都会返回到消费者这里，很多时候消费者服务不希望把收到异常/错误信息再抛到它上游去
     * 用户微服务 ---  注册微服务 --- 优惠券微服务
     *               1.登记注册
     *               2.分发优惠券（非核心）如果调用优惠券微服务返回异常信息或者熔断的错误信息，这些信息不应该抛给用户，不能丢失用户
     *                  此时，返回预设的默认值
     */
    @GetMapping("checkStateTimeOutFallBack/{userId}")
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }, fallbackMethod = "myFallBack" // 回退方法
    )
    public Integer findResumeOpenStateTimeOutFallBack(@PathVariable Long userId) {
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        System.out.println("=====================" + url + "======================");
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }


    /**
     * 定义回退方法，返回预设默认值
     * 注意：该方法形参和返回值与原始方法保持一致
     * @param userId
     * @return
     */
    public Integer myFallBack(Long userId) {
        // 兜底数据
        return -1;
    }



    @GetMapping("checkStateTimeOutFallBack2/{userId}")
    @HystrixCommand(
            // 线程池标识，保持唯一，不唯一就共用了
            threadPoolKey = "findResumeOpenStateTimeoutFallback",
            // 线程池细节属性配置
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),// 线程数
                    @HystrixProperty(name = "maxQueueSize", value = "20") // 等待队列长度
            },
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),

                    /**
                     * Hystrix高级配置，定制工作过程细节:
                     * 8秒钟内，请求次数达到2个，并且失败率在50%以上，就跳闸, 跳闸后活动窗⼝设置为3s
                     */
                    // 时间窗口定义
                    @HystrixProperty(name = "metrics.rollingStats.timeoutInMilliseconds", value = "8000"),
                    // 统计时间窗口内的最小请求数
                    @HystrixProperty(name = "circuitBreak.requestVolumeThreshold", value = "2"),
                    // 统计时间窗口内的错误数量百分比阈值
                    @HystrixProperty(name = "circuitBreak.errorThresholdPercentage", value = "50"),
                    // 自我修复时的活动窗口长度
                    @HystrixProperty(name = "circuitBreak.sleepWindowInMilliseconds", value = "3000")

            }, fallbackMethod = "myFallBack" // 回退方法
    )
    public Integer findResumeOpenStateTimeOutFallBack2(@PathVariable Long userId) {
        String url = "http://lagou-service-resume/resume/openstate/" + userId;
        System.out.println("=====================" + url + "======================");
        Integer forObject = restTemplate.getForObject(url, Integer.class);
        return forObject;
    }
}
