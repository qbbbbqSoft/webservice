package com.cczu.spider.pojo;

import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.entity.SysSignUpEntity;

import java.util.List;

public class ActivityAndSignupEntitesModel {
    private SysActivityEntity sysActivityEntity;
    private List<SysSignUpEntity> sysSignUpEntities;
    private Integer count;

    public ActivityAndSignupEntitesModel(SysActivityEntity sysActivityEntity, List<SysSignUpEntity> sysSignUpEntities, Integer count) {
        this.sysActivityEntity = sysActivityEntity;
        this.sysSignUpEntities = sysSignUpEntities;
        this.count = count;
    }
}
