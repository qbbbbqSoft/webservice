package com.cczu.spider.utils.thread;

import com.cczu.spider.entity.SysCourseEntity;
import com.cczu.spider.repository.SysCourseRepo;
import com.cczu.spider.service.SysCourseService;
import com.cczu.spider.utils.SpringContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


public class SetRemindWeekThread implements Runnable {

    private String openID;

    public SetRemindWeekThread(String openID) {
        this.openID = openID;
    }

    @Override
    public void run() {
        SysCourseService sysCourseService = (SysCourseService) SpringContextUtils.getBean("sysCourseService");
        try {
             System.out.println("这里开始新线程操作" + new Date());
            List<SysCourseEntity> alls = sysCourseService.getEntitiesByOpenID(this.openID);
            List<Integer> remind;
            for (SysCourseEntity all:alls) {
                remind = new ArrayList<>();
                List<Integer> weeks;
                List<String> lists = new ArrayList<>();
                lists.add(all.getCourse1());
                lists.add(all.getCourse2());
                lists.add(all.getCourse3());
                lists.add(all.getCourse4());
                lists.add(all.getCourse5());
                lists.add(all.getCourse6());
                lists.add(all.getCourse7());
                lists.add(all.getCourse8());
                lists.add(all.getCourse9());
                lists.add(all.getCourse10());
                lists.add(all.getCourse11());
                lists.add(all.getCourse12());
                for (String list:lists) {
                    weeks = new ArrayList<>();
                    if (!list.equals("没有课yoo~~!")) {
                         weeks = getRemindWeekStr(list);
                    }
                    for (Integer week:weeks) {
                        if (remind.indexOf(week) == -1) {
                            remind.add(week);
                        }
                    }
                }
                Collections.sort(remind);
                all.setRemind(remind.toString());
                sysCourseService.updateData(all);
            }
            System.out.println(new Date());
            CreateTask.packageResultMap.put(openID,"Success");
        } catch (Exception e) {
            e.printStackTrace();
            CreateTask.packageResultMap.put(openID,"Failure");
        } finally {
            CreateTask.packageListMap.remove(openID);
        }
    }
    public static List<Integer> getRemindWeekStr(String course) {
//        String test = "工业分析技术 W1210 单 1-16/毛泽东思想和中国特色 W4阶 双 1-14/";
        List<String> list = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        List<String> t;
        list.addAll(Arrays.asList(course.split("/")));
        for (String l:list) {
            List<String> tmp = new ArrayList<>();
            tmp.addAll(Arrays.asList(l.split(" ")));
            //样式为--财政学 X1阶 1-8
            if (tmp.size() == 3) {
                for (String x:tmp) {
                    if (x.contains("-")) {
                        if (x.contains(",")) {
                            t = new ArrayList<>();
                            t.addAll(Arrays.asList(x.split(",")));
                            for (String a:t) {
                                List<String> a1 = new ArrayList<>();
                                a1.addAll(Arrays.asList(a.split("-")));
                                for (int i = Integer.valueOf(a1.get(0)); i < Integer.valueOf(a1.get(1)) + 1; i++) {
                                    if (result.indexOf(i) == -1)
                                        result.add(i);
                                }
                            }
                        } else {
                            t = new ArrayList<>();
                            t.addAll(Arrays.asList(x.split("-")));
                            for (int i = Integer.valueOf(t.get(0)); i < Integer.valueOf(t.get(1)) + 1; i++) {
                                if (result.indexOf(i) == -1)
                                    result.add(i);
                            }
                        }
                    }
                }
            } else if (tmp.size() == 4) {
                String dORs =  tmp.get(2);
                if (dORs.equals("单")) {
                    for (String x:tmp) {
                        if (x.contains("-")) {
                            if (x.contains(",")) {
                                t = new ArrayList<>();
                                t.addAll(Arrays.asList(x.split(",")));
                                for (String a:t) {
                                    List<String> a1 = new ArrayList<>();
                                    a1.addAll(Arrays.asList(a.split("-")));
                                    for (int i = Integer.valueOf(a1.get(0)); i < Integer.valueOf(a1.get(1)) + 1; i++) {
                                        if (result.indexOf(i) == -1 && i % 2 == 1)
                                            result.add(i);
                                    }
                                }
                            } else {
                                t = new ArrayList<>();
                                t.addAll(Arrays.asList(x.split("-")));
                                for (int i = Integer.valueOf(t.get(0)); i < Integer.valueOf(t.get(1)) + 1; i++) {
                                    if (result.indexOf(i) == -1 && i % 2 == 1)
                                        result.add(i);
                                }
                            }
                        }
                    }
                } else if (dORs.equals("双")){
                    for (String x:tmp) {
                        if (x.contains("-")) {
                            if (x.contains(",")) {
                                t = new ArrayList<>();
                                t.addAll(Arrays.asList(x.split(",")));
                                for (String a:t) {
                                    List<String> a1 = new ArrayList<>();
                                    a1.addAll(Arrays.asList(a.split("-")));
                                    for (int i = Integer.valueOf(a1.get(0)); i < Integer.valueOf(a1.get(1)) + 1; i++) {
                                        if (result.indexOf(i) == -1 && i % 2 == 0)
                                            result.add(i);
                                    }
                                }
                            } else {
                                t = new ArrayList<>();
                                t.addAll(Arrays.asList(x.split("-")));
                                for (int i = Integer.valueOf(t.get(0)); i < Integer.valueOf(t.get(1)) + 1; i++) {
                                    if (result.indexOf(i) == -1 && i % 2 == 0)
                                        result.add(i);
                                }
                            }
                        }
                    }
                } else {

                }
            } else {

            }

        }
        Collections.sort(result);
        Iterator it = result.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(result.toString());
        return result;
    }

}
