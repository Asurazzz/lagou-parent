package com.lagou.edu.service;

import com.lagou.edu.pojo.Resume;

/**
 * @Classname ResumeService
 * @Description TODO
 * @Date 2021/7/2 11:31
 * @Created by yemingjie
 */
public interface ResumeService {
    Resume findDefaultResumeByUserId(Long userId);
}
