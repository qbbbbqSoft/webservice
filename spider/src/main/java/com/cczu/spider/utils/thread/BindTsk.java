package com.cczu.spider.utils.thread;

import com.cczu.spider.utils.CCZU_spiderByHtmlUnit;
import com.cczu.spider.utils.SpringContextUtils;

import java.util.concurrent.ConcurrentSkipListMap;

public class BindTsk implements Runnable {


    private String username;
    private String password;
    private String openid;

    public BindTsk(String username, String password, String openid) {
        this.username = username;
        this.password = password;
        this.openid = openid;
    }

    @Override
    public void run() {
        CCZU_spiderByHtmlUnit cczu_spiderByHtmlUnit = (CCZU_spiderByHtmlUnit) SpringContextUtils.getBean("CCZU_spiderByHtmlUnit");
        try {
            cczu_spiderByHtmlUnit.cczuSpider(this.username,this.password,0,this.openid);
        } catch (Exception e) {
            CreateTask.packageListMap.remove(this.openid);
            CreateTask.packageResultMap.put(this.openid,e.getMessage());
            e.printStackTrace();
        }

    }
}
