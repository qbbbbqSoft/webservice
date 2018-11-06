package com.bbqbb.poem.admin.modules.admin.service.impl;

import com.bbqbb.poem.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bbqbb.poem.admin.common.utils.Query;

import com.bbqbb.poem.admin.modules.admin.dao.SysSuggestionDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysSuggestionEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysSuggestionService;


@Service("sysSuggestionService")
public class SysSuggestionServiceImpl extends ServiceImpl<SysSuggestionDao, SysSuggestionEntity> implements SysSuggestionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysSuggestionEntity> page = this.selectPage(
                new Query<SysSuggestionEntity>(params).getPage(),
                new EntityWrapper<SysSuggestionEntity>()
        );

        return new PageUtils(page);
    }

}
