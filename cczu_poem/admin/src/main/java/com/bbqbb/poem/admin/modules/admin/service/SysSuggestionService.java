package com.bbqbb.poem.admin.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.bbqbb.poem.admin.modules.admin.entity.SysSuggestionEntity;
import com.bbqbb.poem.common.utils.PageUtils;

import java.util.Map;

/**
 * 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-06 09:38:34
 */
public interface SysSuggestionService extends IService<SysSuggestionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

