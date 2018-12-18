package com.cczu.spider.utils.thread;


import com.cczu.spider.service.UpImgService;
import com.cczu.spider.utils.SpringContextUtils;
import org.springframework.stereotype.Component;


public class SaveImageThread implements Runnable {

    private String fileName;

    public SaveImageThread(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        UpImgService upImgService = (UpImgService) SpringContextUtils.getBean("upImgService");
        try {
            upImgService.updateErWeiMa(this.fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
