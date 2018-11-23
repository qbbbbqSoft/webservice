package com.cczu.spider.service;

import com.cczu.spider.entity.SysBindingWxEntity;

import java.util.List;

public interface SysBindingWxService {

    SysBindingWxEntity saveSysBindingWxEntity(SysBindingWxEntity entity);

    boolean getOne(SysBindingWxEntity entity);

    int updateEntity(SysBindingWxEntity entity);

    void deleteByOpenid(String openid);
}
