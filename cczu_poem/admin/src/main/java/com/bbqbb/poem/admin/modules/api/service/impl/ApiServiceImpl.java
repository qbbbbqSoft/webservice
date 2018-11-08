package com.bbqbb.poem.admin.modules.api.service.impl;

import com.bbqbb.poem.admin.modules.admin.dao.SysCommentDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.admin.modules.api.dao.ApiDao;
import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import com.bbqbb.poem.admin.modules.api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    @Override
    public List<SysTitleModel> getTitleList(Map<String, Object> params) {
        return apiDao.getTitleList(params);
    }


    @Override
    public List<SysCommentEntity> getCommentDetailByTitleID(Map<String, Object> param) {
        return apiDao.getCommentDetailByTitleID(param);
    }

    @Override
    public SysTitleEntity getTitleDetailByID(Map<String, Object> param) {
        return apiDao.getTitleDetailByID(param);
    }

    @Override
    public int insertSysTitleDetail(SysTitleEntity entity) {
        return apiDao.insertTitleDetail(entity);
    }

    @Override
    public SysZoneEntity checkZoneExistByZoneCode(String zoneCode) {
        return apiDao.checkZoneExistByZoneCode(zoneCode);
    }

    @Override
    public int insertZoneDetail(SysZoneEntity entity) {
        return apiDao.insertZoneDetail(entity);
    }
}
