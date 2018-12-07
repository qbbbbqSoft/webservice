package com.cczu.spider.service;

import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.entity.SysSignUpEntity;

import java.util.List;

public interface MailService {
    void sendMail(String type,String text);

    void sendHtmlMail(SysActivityEntity sysActivityEntity, List<SysSignUpEntity> sysSignUpEntities, int type, String recieveEmail) throws Exception;
}
