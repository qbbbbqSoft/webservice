package com.cczu.spider.utils.thread;

import com.cczu.spider.entity.SysCourseEntity;
import com.cczu.spider.repository.SysCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SetRemindWeekThread implements Runnable {

    @Autowired
    private SysCourseRepo repo;
    @Override
    public void run() {
        try {
             System.out.println("这里开始新线程操作" + new Date());
            List<SysCourseEntity> all = repo.findAll();
            System.out.println(all.size());
            System.out.println(new Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
