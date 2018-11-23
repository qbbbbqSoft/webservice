package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysIndexEntity;
import com.cczu.spider.repository.SysIndexRepo;
import com.cczu.spider.service.SysIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("SysIndexService")
public class SysIndexServiceImpl implements SysIndexService {

    @Autowired
    private SysIndexRepo sysIndexRepo;

    @Override
    public List<SysIndexEntity> getAll() {
        return sysIndexRepo.findAll();
    }
}
