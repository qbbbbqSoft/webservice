package com.cczu.spider.repository;

import com.cczu.spider.entity.SysActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface SysActivityRepo extends JpaRepository<SysActivityEntity, Long> {
    @Query(value = "select sae from SysActivityEntity sae where sae.ID = ?1")
    SysActivityEntity getOneByID(Long ID);

    @Query(value = "select sae from SysActivityEntity sae where sae.activityID = ?1")
    SysActivityEntity getOneByActivityID(String activityID);

    @Modifying
    @Transactional
    @Query(value = "update SysActivityEntity sae set sae.activityStatus = ?2, sae.updateDate = ?3 where sae.ID = ?1")
    void setActivityStatusByID(Long ID, Integer status, Date updateDate);

    @Query(nativeQuery = true,value = "SELECT * from sys_activity WHERE ID IN (SELECT activityid FROM sys_signup WHERE openid = ?1)")
    List<SysActivityEntity> queryTakePartInActivityByOpenid(String openid);
}
