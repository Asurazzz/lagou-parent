package com.lagou.edu.controller;

import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yemingjie
 * @Classname ResumeController
 * @Description TODO
 * @Date 2021/7/2 11:33
 * @Created by yemingjie
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Value("${server.port}")
    private Integer port;

    //"/resume/openstate/1545132"
//    @GetMapping("/openstate/{userId}")
//    public Integer findDefaultResumeState(@PathVariable Long userId) {
//        return resumeService.findDefaultResumeByUserId(userId).getIsOpenResume();
//        //return port;
//    }

    /**
     * 使用ribbon的时候返回port区分实例
     * @param userId
     * @return
     */
    @GetMapping("/openstate/{userId}")
    public Integer findDefaultResumeState(@PathVariable Long userId) {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return port;
    }
}
