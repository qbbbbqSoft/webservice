package com.bbqbb.poem.admin.modules.api.dao;

import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ApiDao {

    List<SysTitleModel> getTitleList();

    List<SysCommentEntity> getCommentDetailByTitleID(Map<String,Object> param);

    SysTitleEntity getTitleDetailByID(Map<String,Object> param);

    int insertTitleDetail(SysTitleEntity entity);
}
