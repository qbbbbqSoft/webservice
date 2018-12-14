package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysFormidEntity;
import com.cczu.spider.repository.SysFormidRepo;
import com.cczu.spider.service.SysFormidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SysFormidService")
public class SysFormidServiceImpl implements SysFormidService {

    @Autowired
    private SysFormidRepo sysFormidRepo;

    @Override
    public SysFormidEntity saveFormid(SysFormidEntity entity) {
        return sysFormidRepo.save(entity);
    }

    @Override
    public SysFormidEntity getNeedRemindUserFormid(String openid, long timeStamp) {
        return sysFormidRepo.getNeedRemindUserFormid(openid, timeStamp);
    }
}
