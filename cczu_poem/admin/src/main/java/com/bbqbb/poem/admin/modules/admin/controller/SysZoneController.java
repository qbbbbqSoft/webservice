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

import com.bbqbb.poem.admin.modules.admin.entity.SysZoneEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysZoneService;



/**
 * Private zone 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-07 08:55:10
 */
@RestController
@RequestMapping("admin/syszone")
public class SysZoneController {
    @Autowired
    private SysZoneService sysZoneService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("admin:syszone:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysZoneService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("admin:syszone:info")
    public R info(@PathVariable("id") Long id){
        SysZoneEntity sysZone = sysZoneService.selectById(id);

        return R.ok().put("sysZone", sysZone);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("admin:syszone:save")
    public R save(@RequestBody SysZoneEntity sysZone){
        sysZoneService.insert(sysZone);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("admin:syszone:update")
    public R update(@RequestBody SysZoneEntity sysZone){
        ValidatorUtils.validateEntity(sysZone);
        sysZoneService.updateAllColumnById(sysZone);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("admin:syszone:delete")
    public R delete(@RequestBody Long[] ids){
        sysZoneService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
