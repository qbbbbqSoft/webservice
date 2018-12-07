package com.cczu.spider.service;

import com.cczu.spider.entity.SysSignUpEntity;

import java.util.List;

public interface SysSignUpService {
    SysSignUpEntity getOneByOpenidAndActivityID(String openid, String activityID);

    void save(SysSignUpEntity entity);

    List<Long> getAllTakePartInActivityIDByOpenid(String openid);

    List<SysSignUpEntity> getSysSignUpEntitiesByActivityID(String activityID);

    SysSignUpEntity getSysSignUpEntityByActivityIDAndOpenid(String activityID,String openid);

}
