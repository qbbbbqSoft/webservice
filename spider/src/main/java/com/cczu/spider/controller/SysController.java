package com.cczu.spider.controller;

import com.cczu.spider.entity.SysSuggestionEntity;
import com.cczu.spider.repository.SysSuggestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/sys")
public class SysController {
    @Autowired
    private SysSuggestionRepo sysSuggestionRepo;

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
}
