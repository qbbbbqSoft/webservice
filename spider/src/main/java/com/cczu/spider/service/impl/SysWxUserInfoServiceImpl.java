package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysWxUserInfoEntity;
import com.cczu.spider.repository.SysWxUserInfoRepo;
import com.cczu.spider.service.SysWxUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysWxUserInfoServiceImpl implements SysWxUserInfoService {

    @Autowired
    private SysWxUserInfoRepo sysWxUserInfoRepo;

    @Override
    public SysWxUserInfoEntity getOneWxUserInfoByOpenid(String openid) {
        return sysWxUserInfoRepo.getOneWxUserInfoByOpenid(openid);
    }
}
