package com.bbqbb.poem.admin.modules.admin.service.impl;

import com.bbqbb.poem.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bbqbb.poem.admin.common.utils.Query;

import com.bbqbb.poem.admin.modules.admin.dao.SysCommentDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysCommentService;


@Service("sysCommentService")
public class SysCommentServiceImpl extends ServiceImpl<SysCommentDao, SysCommentEntity> implements SysCommentService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysCommentEntity> page = this.selectPage(
                new Query<SysCommentEntity>(params).getPage(),
                new EntityWrapper<SysCommentEntity>()
        );

        return new PageUtils(page);
    }

}
