package com.bbqbb.poem.admin.modules.admin.service.impl;

import com.bbqbb.poem.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bbqbb.poem.admin.common.utils.Query;

import com.bbqbb.poem.admin.modules.admin.dao.SysTitleDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysTitleService;


@Service("sysTitleService")
public class SysTitleServiceImpl extends ServiceImpl<SysTitleDao, SysTitleEntity> implements SysTitleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysTitleEntity> page = this.selectPage(
                new Query<SysTitleEntity>(params).getPage(),
                new EntityWrapper<SysTitleEntity>()
        );

        return new PageUtils(page);
    }

}
