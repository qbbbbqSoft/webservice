package com.cczu.spider.repository;

import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.pojo.ActivityAndSignUpInfoModel;
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

    @Query(nativeQuery = true,value = "SELECT * from sys_activity WHERE activityID IN (SELECT activityid FROM sys_signup WHERE openid = ?1)")
    List<SysActivityEntity> queryTakePartInActivityByOpenid(String openid);

    @Query(nativeQuery = true,value = "SELECT sac.activityname,sac.activityplace,sac.activitydate,sac.activityorganizingpeople,sac.activityconfiguration,sac.activitylabel,sac.keep1,sac.keep2,sac.count,ssi.name,ssi.phone,ssi.signAddress,ssi.className,ssi.stuNum,ssi.keep1 as signupkeep1,ssi.keep2 as signupkeep2, ssi.signDate,ssi.leaveDate from sys_activity sac LEFT JOIN sys_signup ssi ON sac.activityID = ssi.activityID WHERE sac.activityID IN (SELECT activityid FROM sys_signup WHERE openid = ?1)")
    List<ActivityAndSignUpInfoModel> queryActivityAndSignUpInfo(String openid);
}
