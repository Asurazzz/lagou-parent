package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname ConfigController
 * @Description 该类用于模拟我们要使用的共享的那些配置信息
 * @Date 2021/9/16 15:33
 * @Created by yemingjie
 */
@RestController
@RequestMapping("/config")
public class ConfigController {

    // 和取本地配置信息一样
    @Value("${lagou.message}")
    private String lagouMessage;
    @Value("${mysql.url}")
    private String mysqlUrl;

    // 内存级别的配置信息

    @RequestMapping("/viewconfig")
    public String viewConfig() {
        return "lagouMessage===>" + lagouMessage + ",mysqlUrl====>" + mysqlUrl;
    }

}
