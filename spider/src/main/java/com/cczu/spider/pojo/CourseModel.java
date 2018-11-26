package com.cczu.spider.pojo;

import java.util.Arrays;
import java.util.List;

public class CourseModel {
    private Integer order;
    private String name;
    private String location;
    private String week;

    public CourseModel(Integer order,String course) {
        this.order = order;
        List<String> cs = Arrays.asList(course.split("/"));
        if (cs.size() == 1) {
            if (cs.get(0).equals("暂时没有课，休息一下吧!")) {
                this.name = "暂时没有课，休息一下吧!";
                this.location = "anyWhere you want";
                this.week = "......";
            } else {
                List<String> css = Arrays.asList(cs.get(0).split(" "));
                this.name = css.get(0);
                this.location = css.get(1);
                this.week = css.get(2);
            }
        } else {
            List<String> css2 = Arrays.asList(cs.get(0).split(" "));
            List<String> css3 = Arrays.asList(cs.get(1).split(" "));
            this.name = css2.get(0) + "/" + css3.get(0);
            this.location = css2.get(1) + "/" + css3.get(1);
            this.week = css2.get(2) + "/" + css3.get(2);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
