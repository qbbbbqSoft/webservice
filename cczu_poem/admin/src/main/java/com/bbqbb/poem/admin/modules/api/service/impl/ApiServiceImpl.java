package com.bbqbb.poem.admin.modules.api.service.impl;

import com.bbqbb.poem.admin.modules.api.dao.ApiDao;
import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import com.bbqbb.poem.admin.modules.api.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiDao apiDao;

    @Override
    public List<SysTitleModel> getTitleList() {
        return apiDao.getTitleList();
    }
}
