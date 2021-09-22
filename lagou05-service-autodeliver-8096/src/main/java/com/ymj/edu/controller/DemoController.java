package com.ymj.edu.controller;

import com.ymj.edu.service.ResumeServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname com.ymj.edu.controller.AutodeliverController
 * @Description TODO
 * @Date 2021/9/15 16:09
 * @Created by yemingjie
 */
@RestController
@RequestMapping("/demo")
public class DemoController {


    @GetMapping("/test")
    public String findResumeOpenState() {
        return "demo/test!";
    }
}
