package com.bbqbb.poem.admin.modules.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.bbqbb.poem.common.utils.PageUtils;
import com.bbqbb.poem.common.utils.R;
import com.bbqbb.poem.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysTitleService;



/**
 * 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-06 16:13:12
 */
@RestController
@RequestMapping("admin/systitle")
public class SysTitleController {
    @Autowired
    private SysTitleService sysTitleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("admin:systitle:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysTitleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("admin:systitle:info")
    public R info(@PathVariable("id") Long id){
        SysTitleEntity sysTitle = sysTitleService.selectById(id);

        return R.ok().put("sysTitle", sysTitle);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("admin:systitle:save")
    public R save(@RequestBody SysTitleEntity sysTitle){
        sysTitleService.insert(sysTitle);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("admin:systitle:update")
    public R update(@RequestBody SysTitleEntity sysTitle){
        ValidatorUtils.validateEntity(sysTitle);
        sysTitleService.updateAllColumnById(sysTitle);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("admin:systitle:delete")
    public R delete(@RequestBody Long[] ids){
        sysTitleService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
