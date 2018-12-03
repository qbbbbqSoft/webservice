package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysSignUpEntity;
import com.cczu.spider.repository.SysSignUpRepo;
import com.cczu.spider.service.SysSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysSignUpServiceImpl implements SysSignUpService {
    @Autowired
    private SysSignUpRepo sysSignUpRepo;
    @Override
    public SysSignUpEntity getOneByOpenidAndActivityID(String openid, Long activityID) {
        return sysSignUpRepo.getOneByID(openid,activityID);
    }

    @Override
    public void save(SysSignUpEntity entity) {
        sysSignUpRepo.save(entity);
    }
}
