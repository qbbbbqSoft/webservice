package com.cczu.spider.service;

import com.cczu.spider.entity.SysCourseEntity;

import java.util.List;

public interface SysCourseService {
    List<SysCourseEntity> getAll();

    SysCourseEntity updateData(SysCourseEntity entity);

    List<SysCourseEntity> getEntitiesByOpenID(String openID);

    void deleteByOpenid(String openid);

    List<SysCourseEntity> getEntitiesByOpenIDAndWeek(String openID,Integer week);
}
