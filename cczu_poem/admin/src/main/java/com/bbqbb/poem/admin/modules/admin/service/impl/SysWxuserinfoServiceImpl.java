package com.bbqbb.poem.admin.modules.admin.service.impl;

import com.bbqbb.poem.common.utils.PageUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bbqbb.poem.admin.common.utils.Query;

import com.bbqbb.poem.admin.modules.admin.dao.SysWxuserinfoDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysWxuserinfoEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysWxuserinfoService;


@Service("sysWxuserinfoService")
public class SysWxuserinfoServiceImpl extends ServiceImpl<SysWxuserinfoDao, SysWxuserinfoEntity> implements SysWxuserinfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<SysWxuserinfoEntity> page = this.selectPage(
                new Query<SysWxuserinfoEntity>(params).getPage(),
                new EntityWrapper<SysWxuserinfoEntity>()
        );

        return new PageUtils(page);
    }

}
