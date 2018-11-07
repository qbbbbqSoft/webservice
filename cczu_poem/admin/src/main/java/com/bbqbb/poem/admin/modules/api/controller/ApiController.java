package com.bbqbb.poem.admin.modules.api.controller;


import com.bbqbb.poem.admin.modules.admin.dao.SysCommentDao;
import com.bbqbb.poem.admin.modules.admin.entity.SysCommentEntity;
import com.bbqbb.poem.admin.modules.admin.entity.SysTitleEntity;
import com.bbqbb.poem.admin.modules.api.model.SysTitleModel;
import com.bbqbb.poem.admin.modules.api.service.ApiService;
import com.bbqbb.poem.common.exception.RRException;
import com.bbqbb.poem.common.utils.R;
import com.bbqbb.poem.common.utils.RedisUtils;
import com.bbqbb.poem.common.validator.ValidatorUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/poem/api")
@Api(value = "数据保存和获取的接口")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("getTitleList")
    public R getTitleList() {
        List<SysTitleModel> titleList = apiService.getTitleList();
        return R.ok().put("data",titleList);
    }

    @RequestMapping("/getTitleAndCommentDetailByTitleID/{ID}")
    public R getTitleDetailByID(@PathVariable("ID")Long ID) {
        Map<String, Object> param = new HashMap<>();
        param.put("ID",ID);
        param.put("titleID",ID);
        SysTitleEntity titleDetailByID = apiService.getTitleDetailByID(param);
        List<SysCommentEntity> commentDetailByTitleID = apiService.getCommentDetailByTitleID(param);
        redisUtils.set("commentDetailByTitleID:" + System.currentTimeMillis(),commentDetailByTitleID,30);
        String redisStr = redisUtils.get("commentDetailByTitleID:");
        System.out.println(redisStr);
        return R.ok().put("titleDetail",titleDetailByID).put("commentDetail",commentDetailByTitleID);
    }


    @ApiOperation(value = "信息添加")
    @RequestMapping(value = "/insertTitleDetail",method = RequestMethod.POST)
    public R insertTitleDatail(@RequestBody SysTitleEntity entity) {
        try
        {
            ValidatorUtils.validateEntity(entity);
        }
        catch (RRException e)
        {
            return R.error(e.getMessage());
        }
        entity.setTitle("controller");
        entity.setImageurl("url");
        entity.setImageheight(300);
        entity.setImagewidth(300);
        entity.setContent("asdasdasdas");
        entity.setPrivatestatus(0);
        entity.setAuthor("author");
        entity.setCreatedate(new Date());
        int result = apiService.insertSysTitleDetail(entity);
        return R.ok().put("result",result);
    }
    @RequestMapping("/checkPrivateZoneByZoneCode/zoneCode")
    public R checkPrivateZoneByZoneCode (@PathVariable("zoneCode") String zoneCode) {

        return R.ok();
    }

    @RequestMapping(value = "/postsmt",method = RequestMethod.POST)
    public R post(@RequestBody Map<String,Object> reqbody) throws InvocationTargetException, IllegalAccessException {

        Object obj = null;
        Class<?> sysTitleEntityClass = SysTitleEntity.class;
        try {
            obj = sysTitleEntityClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.populate(obj,reqbody);
        SysTitleEntity entity = (SysTitleEntity)obj;

        entity.setCreatedate(new Date());
        int result = apiService.insertSysTitleDetail(entity);
        return R.ok().put("result",result);
    }

}
