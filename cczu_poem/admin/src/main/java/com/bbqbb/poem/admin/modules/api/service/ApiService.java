package com.bbqbb.poem.admin.modules.api.service;

import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;

import java.util.List;
import java.util.Map;

public interface ApiService {


    List<SysTitleModel> getTitleList(Map<String, Object> params);

    List<SysCommentEntity> getCommentDetailByTitleID(Map<String,Object> param);

    SysTitleEntity getTitleDetailByID(Map<String,Object> param);

    int insertSysTitleDetail(SysTitleEntity entity);

    SysZoneEntity checkZoneExistByZoneCode(String zoneCode);

    int insertZoneDetail(SysZoneEntity entity);

    SysTitleEntity getTitleDetailByDelCodeAndID(Map<String, Object> params);

    int deleteTitleByID(Map<String, Object> params);
}
