package com.cczu.spider.service;

import com.cczu.spider.entity.SysActivityEntity;

import java.util.Date;
import java.util.List;

public interface SysActivityService {
    SysActivityEntity save(SysActivityEntity entity);

    List<SysActivityEntity> getSysActivityListByOpenid(String openid);

    SysActivityEntity getOneSysActivityByActivityID(Long ID);

    SysActivityEntity getOneByID(Long ID);

    SysActivityEntity getOneByActivityID(String activityID);

    void setActivityStatusByID(Long ID, Integer status, Date updateDate);

    List<SysActivityEntity> queryTakePartInActivityByOpenid(String openid);
}
