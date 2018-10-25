package com.cczu.spider.pojo;

public class CoursePojo<T> {
    private int week;
    private T data;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
