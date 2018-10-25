package com.cczu.spider.service;

import com.cczu.spider.entity.SysZoneEntity;
import com.cczu.spider.entity.result.R;

public interface SysZoneService {
    R addNewZone(SysZoneEntity entity);
    R findZoneByZoneCode(String zoneCode);
}
