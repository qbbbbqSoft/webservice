package com.bbqbb.poem.admin.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.common.utils.PageUtils;

import java.util.Map;

/**
 * Private zone 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-07 08:55:10
 */
public interface SysZoneService extends IService<SysZoneEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

