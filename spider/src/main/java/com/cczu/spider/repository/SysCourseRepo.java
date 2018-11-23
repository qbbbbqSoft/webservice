package com.cczu.spider.repository;

import com.cczu.spider.entity.SysCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface SysCourseRepo extends JpaRepository<SysCourseEntity,Long> {


    @Modifying
    @Transactional
    @Query(value = "delete from SysCourseEntity sce where sce.openid = ?1")
    void deleteByOpenid(String openid);
}
