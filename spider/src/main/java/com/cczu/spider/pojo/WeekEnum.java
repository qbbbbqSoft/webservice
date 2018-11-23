package com.cczu.spider.pojo;

public enum  WeekEnum {
    WEEK_1(1,2),
    WEEK_2(2,3),
    WEEK_3(3,4),
    WEEK_4(4,5),
    WEEK_5(5,6),
    WEEK_6(6,7),
    WEEK_7(7,1);

    private Integer code;
    private Integer week;

    WeekEnum(int i, int i1) {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
