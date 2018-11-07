package com.bbqbb.poem.admin.modules.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.common.utils.PageUtils;

import java.util.Map;

/**
 * 评论表，提供无用户评论，用户信息为空
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-07 08:55:10
 */
public interface SysCommentService extends IService<SysCommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

