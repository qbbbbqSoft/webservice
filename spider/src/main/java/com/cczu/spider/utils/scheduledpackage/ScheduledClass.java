package com.cczu.spider.utils.scheduledpackage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cczu.spider.controller.IndexContoller;
import com.cczu.spider.entity.SysCourseEntity;
import com.cczu.spider.entity.SysFormidEntity;
import com.cczu.spider.pojo.TemplateModel;
import com.cczu.spider.pojo.TokenModel;
import com.cczu.spider.repository.LectureRepo;
import com.cczu.spider.repository.SysCourseRepo;
import com.cczu.spider.service.MailService;
import com.cczu.spider.service.SysFormidService;
import com.cczu.spider.utils.JZSpiderUtil;
import com.cczu.spider.utils.httpclientutils.HttpClientUtil;
import com.cczu.spider.utils.jsonutils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Component
public class ScheduledClass {
    @Value("${term.startYear}")
    private Integer YEAR;
    @Value("${term.startMonth}")
    private Integer MONTH;
    @Value("${term.startDay}")
    private Integer DAY;
    @Value("${template_id}")
    private String template_id;
    @Value("${APPID}")
    private String APPID;
    @Value("${SECRET}")
    private String SECRET;

    @Autowired
    private LectureRepo lectureRepo;

    @Autowired
    private JZSpiderUtil jzSpiderUtil;

    @Autowired
    private SysCourseRepo sysCourseRepo;

    @Autowired
    private SysFormidService sysFormidService;

    @Autowired
    private MailService mailService;
//    @Scheduled(fixedRate = 5000)
//    public void say() throws InterruptedException {
//        Thread.sleep(3000L);
////        System.out.println(new Date() + "===========================rate");
//        log.info(new Date() + "===========================rate");
//    }
//
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayJob(){
//        try {
//            Thread.sleep(3000l);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
////        System.out.println(new Date() + "===========================delay");
//        log.info(new Date() + "===========================delay");
//    }

    //    @Scheduled(cron = "0 10 8,9,10,11,12,13,14,15,16,17,18,19,20,21 * * ? ")
    public void update() {
        lectureRepo.deleteAll();
        try {
            jzSpiderUtil.getAndSaveLectureInfo();
        } catch (Exception e) {
            mailService.sendMail("保存出错","讲座信息保存失败" + e.getMessage());
            System.out.println("保存出错");
        }
    }

    @Scheduled(cron = "0 29 07 * * ? ")
    public void sendInfo() {
        Integer week = IndexContoller.getWeek();
        Integer weekOfTerm = getWeekOfTerm();
        System.out.println(weekOfTerm);
        List<SysCourseEntity> lists = sysCourseRepo.getNeedRemindOpenid(week, weekOfTerm.toString());
        for (SysCourseEntity list:lists) {
            if (list.getOpenid() != null) {
                SysFormidEntity sysFormidEntity = sysFormidService.getNeedRemindUserFormid(list.getOpenid(), System.currentTimeMillis());
                if (sysFormidEntity != null) {
                    TemplateModel templateModel = sendTemplateMessage(list.getOpenid(), sysFormidEntity.getFormid());
                    if (templateModel != null ) {
                        if (templateModel.getErrcode() == 0) {
                            //Todo 记录日志
                            sysFormidEntity.setIsused(true);
                            sysFormidService.saveFormid(sysFormidEntity);
                        }
                    }
                }
            }
        }
        System.out.println(lists.size());
    }

    public Integer getWeekOfTerm() {
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        int week_now = cal.get(Calendar.WEEK_OF_YEAR);
        cal2.set(YEAR, MONTH, DAY);
        int week_start = cal2.get(Calendar.WEEK_OF_YEAR);
        return week_now - week_start + 1;

    }

    public TemplateModel sendTemplateMessage(String openid,String formid) {
        String urlForToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + APPID + "&secret=" + SECRET;
        String tokenModelStr = HttpClientUtil.doGet(urlForToken);
        TokenModel tokenModel = JsonUtils.jsonToPojo(tokenModelStr, TokenModel.class);
        Map<String, String> params = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("touser",openid);
        jsonObject.put("template_id",template_id);
        jsonObject.put("page","pages/index/index");
        jsonObject.put("form_id",formid);
        String data = "{\"keyword1\":{\"value\":\"今日课程提醒\"},\"keyword2\":{\"value\":\"系统显示您今日有课程，点击查看详情\"}}";
        jsonObject.put("data",data);
        jsonObject.put("emphasis_keyword", "keyword1.DATA");
        String argsJSONStr = JSON.toJSONString(jsonObject);
        String templateUrl = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=" + tokenModel.getAccess_token();
        params.put("data",argsJSONStr);
        System.out.println(argsJSONStr.replaceAll("=",":"));
        System.out.println(argsJSONStr.replaceAll("\"\\{","{").replaceAll("\\\\","").replaceAll("}\"","}"));
        String sendTemplateResult = HttpClientUtil.doPostJson(templateUrl,argsJSONStr.replaceAll("\"\\{","{").replaceAll("\\\\","").replaceAll("}\"","}"));
        System.out.println(sendTemplateResult);
        TemplateModel templateModel = JsonUtils.jsonToPojo(sendTemplateResult, TemplateModel.class);
        return templateModel;
    }
}
