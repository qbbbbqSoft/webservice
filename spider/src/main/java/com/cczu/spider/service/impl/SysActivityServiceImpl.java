package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.pojo.ActivityAndSignUpInfoModel;
import com.cczu.spider.repository.SysActivityRepo;
import com.cczu.spider.service.SysActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SysActivityServiceImpl implements SysActivityService {

    @Autowired
    private SysActivityRepo sysActivityRepo;

    @Override
    public SysActivityEntity save(SysActivityEntity entity) {
        SysActivityEntity sysActivityEntity = sysActivityRepo.save(entity);
        return sysActivityEntity;
    }

    @Override
    public List<SysActivityEntity> getSysActivityListByOpenid(String openid) {
        SysActivityEntity entity = new SysActivityEntity();
        entity.setOrganizingPeopleOpenID(openid);
        Example<SysActivityEntity> example = Example.of(entity);
        List<SysActivityEntity> all = sysActivityRepo.findAll(example);
        return all;
    }

    @Override
    public SysActivityEntity getOneSysActivityByActivityID(Long ID) {
        SysActivityEntity one = sysActivityRepo.getOne(ID);
        return one;
    }

    @Override
    public SysActivityEntity getOneByID(Long ID) {
        return sysActivityRepo.getOneByID(ID);
    }

    @Override
    public SysActivityEntity getOneByActivityID(String activityID) {
        return sysActivityRepo.getOneByActivityID(activityID);
    }

    @Override
    public void setActivityStatusByID(Long ID, Integer status, Date updateDate) {
        sysActivityRepo.setActivityStatusByID(ID,status,updateDate);
    }

    @Override
    public List<SysActivityEntity> queryTakePartInActivityByOpenid(String openid) {
        return sysActivityRepo.queryTakePartInActivityByOpenid(openid);
    }

    @Override
    public List<ActivityAndSignUpInfoModel> queryActivityAndSignUpInfo(String openid) {
        return sysActivityRepo.queryActivityAndSignUpInfo(openid);
    }
}
