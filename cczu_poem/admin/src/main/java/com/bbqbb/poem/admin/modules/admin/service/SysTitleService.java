package com.bbqbb.poem.admin.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-06 16:13:12
 */
public interface SysTitleService extends IService<SysTitleEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

