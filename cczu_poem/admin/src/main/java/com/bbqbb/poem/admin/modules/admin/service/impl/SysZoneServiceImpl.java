package com.bbqbb.poem.admin.modules.admin.service.impl;

import com.bbqbb.poem.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bbqbb.poem.admin.common.utils.Query;

import com.bbqbb.poem.admin.modules.admin.dao.SysZoneDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysZoneService;


@Service("sysZoneService")
public class SysZoneServiceImpl extends ServiceImpl<SysZoneDao, SysZoneEntity> implements SysZoneService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysZoneEntity> page = this.selectPage(
                new Query<SysZoneEntity>(params).getPage(),
                new EntityWrapper<SysZoneEntity>()
        );

        return new PageUtils(page);
    }

}
