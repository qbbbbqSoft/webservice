package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.repository.SysActivityRepo;
import com.cczu.spider.service.SysActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysActivityServiceImpl implements SysActivityService {

    @Autowired
    private SysActivityRepo sysActivityRepo;

    @Override
    public SysActivityEntity save(SysActivityEntity entity) {
        SysActivityEntity sysActivityEntity = sysActivityRepo.save(entity);
        return sysActivityEntity;
    }
}
