package com.bbqbb.poem.admin.modules.api.model;

import com.bbqbb.poem.admin.modules.admin.entity.SysWxuserinfoEntity;

public class WxUserInfoModel {
    private String code;
    private SysWxuserinfoEntity sysWxuserinfoEntity;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SysWxuserinfoEntity getSysWxuserinfoEntity() {
        return sysWxuserinfoEntity;
    }

    public void setSysWxuserinfoEntity(SysWxuserinfoEntity sysWxuserinfoEntity) {
        this.sysWxuserinfoEntity = sysWxuserinfoEntity;
    }
}
