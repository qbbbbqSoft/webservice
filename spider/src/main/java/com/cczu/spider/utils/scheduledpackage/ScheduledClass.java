package com.cczu.spider.utils.scheduledpackage;

import com.cczu.spider.repository.LectureRepo;
import com.cczu.spider.utils.JZSpiderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class ScheduledClass {
    @Autowired
    private LectureRepo lectureRepo;

    @Autowired
    private JZSpiderUtil jzSpiderUtil;
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

    @Scheduled(cron = "0 50 8,9,10,11,12,13,14,15,16,17,18,19,20,21 * * ? ")
    public void update() {
        lectureRepo.deleteAll();
        try {
            jzSpiderUtil.getAndSaveLectureInfo();
        } catch (Exception e) {
            System.out.println("保存出错");
        }
    }
}
