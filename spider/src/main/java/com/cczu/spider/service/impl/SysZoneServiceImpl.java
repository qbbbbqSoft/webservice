package com.cczu.spider.service.impl;

import com.cczu.spider.entity.SysZoneEntity;
import com.cczu.spider.entity.result.R;
import com.cczu.spider.repository.SysZoneRepo;
import com.cczu.spider.service.SysZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SysZoneServiceImpl implements SysZoneService {

    @Autowired
    private SysZoneRepo sysZoneRepo;


    @Override
    public R addNewZone(SysZoneEntity entity) {
        SysZoneEntity result = sysZoneRepo.save(entity);
        if (result != null) {
            return R.isFail();
        }
        return R.isOk();
    }

    @Override
    public R findZoneByZoneCode(String zoneCode) {
        SysZoneEntity entity = new SysZoneEntity();
        entity.setZonecode(zoneCode);
        Example example = Example.of(entity);
        Optional one = sysZoneRepo.findOne(example);
        Object o = one.get();

        return R.isOk().data((SysZoneEntity) o);
    }
}
