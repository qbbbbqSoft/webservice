package com.bbqbb.poem.admin.modules.api.service.impl;

import com.bbqbb.poem.admin.modules.admin.dao.SysCommentDao;
import com.bbqbb.poem.admin.modules.admin.dao.SysTitleDao;
import com.bbqbb.poem.admin.modules.admin.dao.SysZoneDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysWxuserinfoEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.admin.modules.api.dao.ApiDao;
import com.bbqbb.poem.admin.modules.api.model.ActivityAndSignUpInfoModel;
import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import com.bbqbb.poem.admin.modules.api.service.ApiService;
import com.bbqbb.poem.admin.modules.api.utils.DateTransUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<SysTitleModel> titleList = apiDao.getTitleList(params);
        for (SysTitleModel model: titleList) {
            if (model.getCreatedate() != null) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    model.setCreateDateStamp(DateTransUtil.dateToStamp(simpleDateFormat.format(model.getCreatedate())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return titleList;
    }


    @Override
    public List<SysCommentEntity> getCommentDetailByTitleID(Map<String, Object> param) {
        List<SysCommentEntity> entities = apiDao.getCommentDetailByTitleID(param);
        for (SysCommentEntity entity:entities) {
            if (entity.getCreatedate() != null) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entity.setWxotherinfo(DateTransUtil.dateToStamp(simpleDateFormat.format(entity.getCreatedate())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return entities;
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

    @Override
    public List<ActivityAndSignUpInfoModel> queryActivityAndSignUpInfo(String openid) {
        return apiDao.queryActivityAndSignUpInfo(openid);
    }

    @Override
    public List<SysWxuserinfoEntity> checkUserIfExist(Map<String, Object> map) {
        return apiDao.checkUserIfExist(map);
    }
}
