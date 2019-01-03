package com.cczu.spider.service;

import com.cczu.spider.entity.SysIndexEntity;

import java.util.List;

public interface SysIndexService {

    List<SysIndexEntity> getAll();

    List<SysIndexEntity> getIndexByPosition(String position);
}
