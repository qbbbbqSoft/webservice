package com.cczu.spider.repository;

import com.cczu.spider.entity.SysZoneEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface SysZoneMapper {

    SysZoneEntity querySysZoneByZoneCode(String zoneCode);
}
