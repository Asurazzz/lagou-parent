package com.ymj.edu.service;

import org.springframework.stereotype.Component;

/**
 * @Classname ResumeFallback
 * @Description 降级回退逻辑需要定义一个类，实现FeignClient接口，实现接口中的方法
 * @Date 2021/9/15 17:42
 * @Created by yemingjie
 */
@Component
public class ResumeFallback implements ResumeServiceFeignClient{
    @Override
    public Integer findDefaultResumeState(Long userId) {
        return -6;
    }
}
