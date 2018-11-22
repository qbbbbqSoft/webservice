package com.cczu.spider.utils.thread;

import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentSkipListMap;

@Component
public class CreateTask {

    /**
     * 执行中打包状态
     */
    public static ConcurrentSkipListMap<String, String> packageListMap = new ConcurrentSkipListMap<>();

    /**
     * 打包结果状态
     */
    public static ConcurrentSkipListMap<String, String> packageResultMap = new ConcurrentSkipListMap<>();




    public static String createTask(String openID){
        Thread thread = new Thread(new SetRemindWeekThread(openID));
        thread.start();
        return "start";
    }

    public static  String createBindTask(String username,String password,String openid) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (packageListMap.get(openid) != null)
                    packageListMap.remove(openid);
            }
        }, 60000);

        // 如果存在说明仍在打包中
        if (packageListMap.get(openid) != null && "Binding".equals(packageListMap.get(openid)))
        {
            return "Binding";
        }
        else if (packageListMap.get(openid) != null && "Success".equals(packageListMap.get(openid)))
        {
            packageListMap.remove(openid);
            return "Success";
        }
        else
        {
            // 如果有错误提示错误
            if (packageResultMap.get(openid) != null)
            {
                String error = packageResultMap.get(openid);
                packageResultMap.remove(openid);
                return error;
            }

            // 如果缓存中没有，则开始打包
            packageListMap.put(openid, "Binding");
        }

        Thread thread = new Thread(new BindTsk(username,password,openid));
        thread.start();
        return "Binding";
    }

    //ToDo 天气接口https://www.sojson.com/blog/305.html
}
