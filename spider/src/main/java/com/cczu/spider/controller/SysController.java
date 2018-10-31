package com.cczu.spider.controller;

import com.cczu.spider.entity.SysSuggestionEntity;
import com.cczu.spider.entity.SysZoneEntity;
import com.cczu.spider.repository.SysSuggestionRepo;
import com.cczu.spider.repository.SysZoneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/sys")
public class SysController {
    @Autowired
    private SysSuggestionRepo sysSuggestionRepo;
    @Autowired
    private SysZoneRepo sysZoneRepo;

    @RequestMapping(value = "/wxapi")
    public String checkWeixinValid(@RequestParam(name="signature")String signature,
                                   @RequestParam(name="timestamp")String timestamp,
                                   @RequestParam(name="nonce")String nonce,
                                   @RequestParam(name="echostr")String echostr){
        return "success";
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
        Optional one = sysZoneRepo.findOne(example);
        return false;
    }
}
