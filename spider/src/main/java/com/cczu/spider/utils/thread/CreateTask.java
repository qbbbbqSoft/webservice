package com.cczu.spider.utils.thread;

import org.springframework.stereotype.Component;

@Component
public class CreateTask {


    public static String createTask(String openID){

        SetRemindWeekThread thread = new SetRemindWeekThread();
        thread.run();

        return "start";
    }
}
