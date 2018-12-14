package com.cczu.spider.service;

import com.cczu.spider.entity.SysFormidEntity;

public interface SysFormidService {

    SysFormidEntity saveFormid(SysFormidEntity entity);

    SysFormidEntity getNeedRemindUserFormid(String openid, long timeStamp);
}
