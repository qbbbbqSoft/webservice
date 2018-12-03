package com.cczu.spider.service;

import com.cczu.spider.entity.SysWxUserInfoEntity;

public interface SysWxUserInfoService {

    SysWxUserInfoEntity getOneWxUserInfoByOpenid(String openid);
}
