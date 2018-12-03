package com.cczu.spider.service;

import com.cczu.spider.entity.SysSignUpEntity;

public interface SysSignUpService {
    SysSignUpEntity getOneByOpenidAndActivityID(String openid, Long activityID);

    void save(SysSignUpEntity entity);
}
