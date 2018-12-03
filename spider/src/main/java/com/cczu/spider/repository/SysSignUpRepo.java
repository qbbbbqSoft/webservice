package com.cczu.spider.repository;

import com.cczu.spider.entity.SysSignUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SysSignUpRepo extends JpaRepository<SysSignUpEntity,Long> {

    @Query(value = "select sse from SysSignUpEntity sse where sse.openid = ?1 and sse.activityID = ?2")
    SysSignUpEntity getOneByID(String openid, Long activityID);



}
