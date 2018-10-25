package com.cczu.spider.pojo;

public enum TermEnum {
    T1819_1("18-19-1"),
    T1718_2("17-18-2"),
    T1718_1("17-18-1"),
    T1617_2("16-17-2"),
    T1617_1("16-17-1"),
    T1516_2("15-16-2"),
    T1516_1("15-16-1"),
    T1415_2("14-15-2"),
    T1415_1("14-15-1"),
    T1314_2("13-14-2"),
    T1314_1("13-14-1"),
    T1213_2("12-13-2"),
    T1213_1("12-13-1"),
    T1112_2("11-12-2"),
    T1112_1("11-12-1");
    private final String name;
    private TermEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
