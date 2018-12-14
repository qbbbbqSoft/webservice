package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysSignUpEntity;
import com.cczu.spider.repository.SysSignUpRepo;
import com.cczu.spider.service.SysSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysSignUpServiceImpl implements SysSignUpService {
    @Autowired
    private SysSignUpRepo sysSignUpRepo;
    @Override
    public SysSignUpEntity getOneByOpenidAndActivityID(String openid, String activityID) {
        return sysSignUpRepo.getOneByID(openid,activityID);
    }

    @Override
    public void save(SysSignUpEntity entity) {
        sysSignUpRepo.save(entity);
    }

    @Override
    public List<Long> getAllTakePartInActivityIDByOpenid(String openid) {
        return sysSignUpRepo.getAllTakePartInActivityIDByOpenid(openid);
    }

    @Override
    public List<SysSignUpEntity> getSysSignUpEntitiesByActivityID(String activityID) {
        return sysSignUpRepo.getSysSignUpEntitiesByActivityID(activityID);
    }

    @Override
    public SysSignUpEntity getSysSignUpEntityByActivityIDAndOpenid(String activityID, String openid) {
        return sysSignUpRepo.getSysSignUpEntityByActivityIDAndOpenid(activityID,openid);
    }

    @Override
    public int getTakePartInUserCount(String activityID) {
        SysSignUpEntity entity = new SysSignUpEntity();
        entity.setActivityID(activityID);
        Example example = Example.of(entity);
        List all = sysSignUpRepo.findAll(example);
        if (all == null)
            return 0;
        return all.size();
    }
}
