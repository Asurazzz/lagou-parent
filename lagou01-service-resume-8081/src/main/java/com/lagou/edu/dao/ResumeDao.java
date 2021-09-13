package com.lagou.edu.dao;

import com.lagou.edu.pojo.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Classname ResumeDao
 * @Description TODO
 * @Date 2021/7/2 11:26
 * @Created by yemingjie
 */
public interface ResumeDao extends JpaRepository<Resume,Long> {
}
