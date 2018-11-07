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

import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.service.SysCommentService;



/**
 * 评论表，提供无用户评论，用户信息为空
 *
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-07 08:55:10
 */
@RestController
@RequestMapping("admin/syscomment")
public class SysCommentController {
    @Autowired
    private SysCommentService sysCommentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("admin:syscomment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysCommentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("admin:syscomment:info")
    public R info(@PathVariable("id") Long id){
        SysCommentEntity sysComment = sysCommentService.selectById(id);

        return R.ok().put("sysComment", sysComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("admin:syscomment:save")
    public R save(@RequestBody SysCommentEntity sysComment){
        sysCommentService.insert(sysComment);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("admin:syscomment:update")
    public R update(@RequestBody SysCommentEntity sysComment){
        ValidatorUtils.validateEntity(sysComment);
        sysCommentService.updateAllColumnById(sysComment);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("admin:syscomment:delete")
    public R delete(@RequestBody Long[] ids){
        sysCommentService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
