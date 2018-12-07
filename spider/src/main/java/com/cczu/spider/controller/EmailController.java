package com.cczu.spider.controller;


import com.cczu.spider.entity.SysActivityEntity;
import com.cczu.spider.entity.SysSignUpEntity;
import com.cczu.spider.service.MailService;
import com.cczu.spider.service.SysActivityService;
import com.cczu.spider.service.SysSignUpService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cczu")
@Api("邮件发送类控制器")
public class EmailController {
    @Autowired
    private MailService mailService;
    @Autowired
    private SysSignUpService sysSignUpService;
    @Autowired
    private SysActivityService sysActivityService;

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
}
