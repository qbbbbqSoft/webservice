package com.cczu.spider.repository;

import com.cczu.spider.entity.SysCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface SysCourseRepo extends JpaRepository<SysCourseEntity,Long> {
//    int saveSysCourse(SysCourseEntity entity);
}
