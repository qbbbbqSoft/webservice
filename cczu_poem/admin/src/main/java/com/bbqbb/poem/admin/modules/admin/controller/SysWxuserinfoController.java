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

import com.bbqbb.poem.admin.modules.admin.entity.SysWxuserinfoEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysWxuserinfoService;



/**
 * 
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-12-03 15:06:04
 */
@RestController
@RequestMapping("admin/syswxuserinfo")
public class SysWxuserinfoController {
    @Autowired
    private SysWxuserinfoService sysWxuserinfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("admin:syswxuserinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysWxuserinfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("admin:syswxuserinfo:info")
    public R info(@PathVariable("id") Long id){
        SysWxuserinfoEntity sysWxuserinfo = sysWxuserinfoService.selectById(id);

        return R.ok().put("sysWxuserinfo", sysWxuserinfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("admin:syswxuserinfo:save")
    public R save(@RequestBody SysWxuserinfoEntity sysWxuserinfo){
        sysWxuserinfoService.insert(sysWxuserinfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("admin:syswxuserinfo:update")
    public R update(@RequestBody SysWxuserinfoEntity sysWxuserinfo){
        ValidatorUtils.validateEntity(sysWxuserinfo);
        sysWxuserinfoService.updateAllColumnById(sysWxuserinfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("admin:syswxuserinfo:delete")
    public R delete(@RequestBody Long[] ids){
        sysWxuserinfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
