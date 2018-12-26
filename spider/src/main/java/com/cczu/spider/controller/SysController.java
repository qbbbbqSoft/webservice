package com.cczu.spider.controller;

import com.cczu.spider.entity.SysSuggestionEntity;
import com.cczu.spider.entity.SysTitleEntity;
import com.cczu.spider.entity.SysZoneEntity;
import com.cczu.spider.repository.SysSuggestionRepo;
import com.cczu.spider.repository.SysTitleRepo;
import com.cczu.spider.repository.SysZoneMapper;
import com.cczu.spider.repository.SysZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sys")
public class SysController {
    @Autowired
    private SysSuggestionRepo sysSuggestionRepo;
    @Autowired
    private SysZoneRepo sysZoneRepo;
    @Autowired
    private SysTitleRepo sysTitleRepo;
    @Autowired
    private SysZoneMapper sysZoneMapper;

    @GetMapping(value = "/wxapi")
    public String checkWeixinValid(@RequestParam(name="signature")String signature,
                                   @RequestParam(name="timestamp")String timestamp,
                                   @RequestParam(name="nonce")String nonce,
                                   @RequestParam(name="echostr")String echostr){
        System.out.println("GetMapping wxapi success");
        return echostr;
    }

    @PostMapping(value = "/wxapi")
    public void checkWeixinValid_post(@RequestParam(name="signature")String signature,
                                        @RequestParam(name="timestamp")String timestamp,
                                        @RequestParam(name="nonce")String nonce,
                                        @RequestParam(name="echostr")String echostr, HttpServletResponse response){
        response.reset();
        System.out.println("PostMapping wxapi success");
        try {
            response.getOutputStream().print("success");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/writesuggestion")
    public Boolean writeSuggestion(@RequestBody SysSuggestionEntity sysSuggestionEntity) {
        sysSuggestionEntity.setCreatedate(new Date());
        SysSuggestionEntity save = sysSuggestionRepo.save(sysSuggestionEntity);
        if (save != null) {
            return true;
        }
        return false;
    }

    @RequestMapping("/queryZoneCode/{zoneCode}")
    public boolean queryZoneCode(@PathVariable("zoneCode")String zoneCode) {
        SysZoneEntity entity = new SysZoneEntity();
        entity.setZonecode(zoneCode);
        Example example = Example.of(entity);
        boolean exists = sysZoneRepo.exists(example);
        return exists;
    }

    @RequestMapping("/querySysTitleByZoneCode/{zoneCode}")
    public List<SysTitleEntity> querySysTitleByZoneID(@PathVariable("zoneCode")String zoneCode) {
        SysZoneEntity zoneEntity = sysZoneMapper.querySysZoneByZoneCode(zoneCode);
        SysTitleEntity entity = new SysTitleEntity();
        entity.setZoneid(zoneEntity.getId());
        List<SysTitleEntity> all = sysTitleRepo.findAll();
        return all;
    }

    @RequestMapping("/queryAllSysTitle")
    public List<SysTitleEntity> queryAllSysTitle() {
        List<SysTitleEntity> all = sysTitleRepo.findAll();
        return all;
    }
}
