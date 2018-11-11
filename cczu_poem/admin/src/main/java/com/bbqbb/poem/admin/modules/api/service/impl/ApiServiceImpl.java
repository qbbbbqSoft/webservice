package com.bbqbb.poem.admin.modules.api.service.impl;

import com.bbqbb.poem.admin.modules.admin.dao.SysCommentDao;
import com.bbqbb.poem.admin.modules.admin.dao.SysTitleDao;
import com.bbqbb.poem.admin.modules.admin.dao.SysZoneDao;
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
    @Autowired
    private SysTitleDao sysTitleDao;
    @Autowired
    private SysCommentDao sysCommentDao;
    @Autowired
    private SysZoneDao sysZoneDao;

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

    @Override
    public SysTitleEntity getTitleDetailByDelCodeAndID(Map<String, Object> params) {
        return apiDao.getTitleDetailByDelCodeAndID(params);
    }

    @Override
    public int deleteTitleByID(Map<String, Object> params) {
        return apiDao.deleteTitleByID(params);
    }

    @Override
    public int updateGreatCountByCommentID(Map<String, Object> params) {
        return apiDao.updateGreatCountByCommentID(params);
    }

    @Override
    public int updateLikeCountByTitleID(Map<String, Object> params) {
        return apiDao.updateLikeCountByTitleID(params);
    }

    @Override
    public int updatenotLikeCountByTitleID(Map<String, Object> params) {
        return apiDao.updateNotLikeCountByTitleID(params);
    }

    @Override
    public SysTitleEntity getSysTitleEntity(SysTitleEntity entity) {
        return sysTitleDao.selectOne(entity);
    }

    @Override
    public int updateSysTitleEntity(SysTitleEntity entity) {
        return sysTitleDao.updateAllColumnById(entity);
    }

    @Override
    public int insertSysCommentEntity(SysCommentEntity entity) {
        return sysCommentDao.insertAllColumn(entity);
    }

    @Override
    public SysZoneEntity getSysZoneEntity(SysZoneEntity entity) {
        return sysZoneDao.selectOne(entity);
    }
}
