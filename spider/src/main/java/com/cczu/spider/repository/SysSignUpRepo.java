package com.cczu.spider.repository;

import com.cczu.spider.entity.SysSignUpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysSignUpRepo extends JpaRepository<SysSignUpEntity,Long> {

    @Query(value = "select sse from SysSignUpEntity sse where sse.openid = ?1 and sse.activityID = ?2")
    SysSignUpEntity getOneByID(String openid, String activityID);


    @Query(value = "select sse.activityID from SysSignUpEntity sse where sse.openid = ?1")
    List<Long> getAllTakePartInActivityIDByOpenid(String openid);

    @Query(value = "select sse from SysSignUpEntity sse where sse.activityID = ?1")
    List<SysSignUpEntity> getSysSignUpEntitiesByActivityID(String activityID);

    @Query(value = "select sse from SysSignUpEntity sse where sse.activityID = ?1 and sse.openid = ?2")
    SysSignUpEntity getSysSignUpEntityByActivityIDAndOpenid(String activityID,String openid);

}
