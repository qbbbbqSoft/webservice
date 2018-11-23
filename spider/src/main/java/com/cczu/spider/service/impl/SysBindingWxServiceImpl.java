package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysBindingWxEntity;
import com.cczu.spider.repository.SysBindingWxRepo;
import com.cczu.spider.service.SysBindingWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("SysBindingWxService")
public class SysBindingWxServiceImpl implements SysBindingWxService {

    @Autowired
    private SysBindingWxRepo sysBindingWxRepo;

    @Override
    public SysBindingWxEntity saveSysBindingWxEntity(SysBindingWxEntity entity) {
        return sysBindingWxRepo.save(entity);
    }

    @Override
    public boolean getOne(SysBindingWxEntity entity) {
        Example<SysBindingWxEntity> example = Example.of(entity);
        return sysBindingWxRepo.exists(example);
    }

    @Override
    public int updateEntity(SysBindingWxEntity entity) {
        String status = entity.getStatus();
        String openid = entity.getOpenid();
        Date updatedate = entity.getUpdatedate();
        int update = sysBindingWxRepo.update(status, openid,updatedate);
        return update;
    }

    @Override
    public void deleteByOpenid(String openid) {
        sysBindingWxRepo.deleteByOpenid(openid);
    }
}
