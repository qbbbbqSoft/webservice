package com.cczu.spider.controller;


import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.entity.SysFormidEntity;
import com.cczu.spider.entity.SysSignUpEntity;
import com.cczu.spider.entity.result.R;
import com.cczu.spider.service.MailService;
import com.cczu.spider.service.SysActivityService;
import com.cczu.spider.service.SysFormidService;
import com.cczu.spider.service.SysSignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/cczu")
@Api("邮件发送类控制器 + formid获取和保存")
public class EmailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private SysSignUpService sysSignUpService;
    @Autowired
    private SysActivityService sysActivityService;
    @Autowired
    private SysFormidService sysFormidService;

    @GetMapping("/sendMail")
    @ApiOperation(value = "发送邮件",notes = "ID为活动的主键，type为1发送活动详情，2发送签到人员详情，receiveEmail接收邮件人的邮箱")
    public void sendMail(@ApiParam(value = "活动的主键",required = true,defaultValue = "1")@RequestParam(value = "ID",required = true,defaultValue = "1")Long ID,
                         @ApiParam(value = "发送类型",required = true,defaultValue = "1")@RequestParam(value = "type",defaultValue = "1")Integer type,
                         @ApiParam(value = "邮件接收人邮箱地址",required = true,defaultValue = "")@RequestParam(value = "receiveEmail",defaultValue = "")String receiveEmail) {
        SysActivityEntity sysActivityEntity = sysActivityService.getOneByID(ID);
        List<SysSignUpEntity> sysSignUpEntities = null;
        if (type == 2) {
            sysSignUpEntities = sysSignUpService.getSysSignUpEntitiesByActivityID(sysActivityEntity.getActivityID());
        }

        try {
            mailService.sendHtmlMail(sysActivityEntity,sysSignUpEntities,type,receiveEmail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/saveFormid")
    @ApiOperation(value = "保存formid",notes = "搜集formid用于推送消息")
    public R saveFormid(@ApiParam(value = "微信openid",required = true, defaultValue = "")@RequestParam("openid") String openid,
                        @ApiParam(value = "表单id",required = true,defaultValue = "")@RequestParam("formid") String formid) {
        SysFormidEntity entity = new SysFormidEntity();
        entity.setOpenid(openid);
        entity.setFormid(formid);
        entity.setTimestamp(System.currentTimeMillis() + 86400 * 7);
        entity.setIsused(false);
        entity.setCreatedate(new Date());
        SysFormidEntity sysFornidEntity = sysFormidService.saveFormid(entity);
        return R.ok().put("data",sysFornidEntity);
    }
}
