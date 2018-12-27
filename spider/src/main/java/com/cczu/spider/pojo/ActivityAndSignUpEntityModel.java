package com.cczu.spider.pojo;

import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.entity.SysSignUpEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

public class ActivityAndSignUpEntityModel  implements Serializable {
    private SysActivityEntity sysActivityEntity;
    private SysSignUpEntity sysSignUpEntity;

    public ActivityAndSignUpEntityModel(SysActivityEntity sysActivityEntity, SysSignUpEntity sysSignUpEntity) {
        this.sysActivityEntity = sysActivityEntity;
        this.sysSignUpEntity = sysSignUpEntity;
    }
}
