package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysCourseEntity;
import com.cczu.spider.repository.SysCourseRepo;
import com.cczu.spider.service.SysCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysCourseService")
public class SysCourseServiceImpl implements SysCourseService {
    @Autowired
    private SysCourseRepo sysCourseRepo;

    @Override
    public List<SysCourseEntity> getAll() {
        return sysCourseRepo.findAll();
    }

    @Override
    public SysCourseEntity updateData(SysCourseEntity entity) {
        return sysCourseRepo.save(entity);
    }

    @Override
    public List<SysCourseEntity> getEntitiesByOpenID(String openID) {
        SysCourseEntity entity = new SysCourseEntity();
        entity.setOpenid(openID);
        Example<SysCourseEntity> example = Example.of(entity);
        List<SysCourseEntity> all = sysCourseRepo.findAll(example);
        return all;
    }


    @Override
    public void deleteByOpenid(String openid) {
        sysCourseRepo.deleteByOpenid(openid);
    }

    @Override
    public List<SysCourseEntity> getEntitiesByOpenIDAndWeek(String openID, Integer week) {
        SysCourseEntity entity = new SysCourseEntity();
        entity.setOpenid(openID);
        entity.setWeek(week);
        Example<SysCourseEntity> example = Example.of(entity);
        List<SysCourseEntity> all = sysCourseRepo.findAll(example);
        return all;
    }
}
