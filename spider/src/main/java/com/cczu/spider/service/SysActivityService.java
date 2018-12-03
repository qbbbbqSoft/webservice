package com.cczu.spider.service;

import com.cczu.spider.entity.SysActivityEntity;

import java.util.List;

public interface SysActivityService {
    SysActivityEntity save(SysActivityEntity entity);

    List<SysActivityEntity> getSysActivityListByOpenid(String openid);

    SysActivityEntity getOneSysActivityByActivityID(Long ID);

    SysActivityEntity getOneByID(Long ID);

    void setActivityStatusByID(Long ID,Integer status);
}
