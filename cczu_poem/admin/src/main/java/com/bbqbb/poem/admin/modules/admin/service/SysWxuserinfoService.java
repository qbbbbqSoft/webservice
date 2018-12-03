package com.bbqbb.poem.admin.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.bbqbb.poem.admin.modules.admin.entity.SysWxuserinfoEntity;
import com.bbqbb.poem.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-12-03 15:06:04
 */
public interface SysWxuserinfoService extends IService<SysWxuserinfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

