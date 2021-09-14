package com.lagou.edu;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * service-autodeliver-8090
 * <p>
 * SpringCloudApplication 综合性的注解
 * = SpringBootApplication + EnableDiscoveryClient + EnableCircuitBreaker
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
//@EnableHystrix // 开启hystrix功能
@EnableCircuitBreaker // 开启熔断器功能
public class Lagou01ServiceAutodeliverApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lagou01ServiceAutodeliverApplication.class, args);
    }

    // 使用RestTempalte模板对象进行远程调用
//    @Bean
//    @LoadBalanced
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }


    /**
     * 在被监控的微服务中注册一个servlet，后期通过这个servlet来获取服务Hystrix的监控数据
     * 前提：被监控的微服务需要引入springboot的actuator功能
     */
    @Bean
    public ServletRegistrationBean getServlet() {
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);

        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
