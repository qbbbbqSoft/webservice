package com.cczu.spider.pojo;

import java.util.Date;

public class ActivityAndSignUpInfoModel {
    private Long id;
    private String activityid;
    private String activityname;
    private String activityplace;
    private Date activitydate;
    private String activityqrcodeurl;
    private String activityorganizingpeople;
    private String organizingpeopleopenid;
    private Integer activitystatus;
    private String activityconfiguration;
    private String activitylabel;
    private String activitybackgroundpic;
    private String keep1;
    private String keep2;
    private Integer count;
    private Date createdate;
    private Date updatedate;
    private String name;
    private String phone;
    private String signaddress;
    private String classname;
    private String stunum;
    private String signupkeep1;
    private String signupkeep2;
    private Date signDate;
    private Date leaveDate;

    public ActivityAndSignUpInfoModel(Long id, String activityid, String activityname, String activityplace, Date activitydate, String activityqrcodeurl, String activityorganizingpeople, String organizingpeopleopenid, Integer activitystatus, String activityconfiguration, String activitylabel, String activitybackgroundpic, String keep1, String keep2, Integer count, Date createdate, Date updatedate, String name, String phone, String signaddress, String classname, String stunum, String signupkeep1, String signupkeep2, Date signDate, Date leaveDate) {
        this.id = id;
        this.activityid = activityid;
        this.activityname = activityname;
        this.activityplace = activityplace;
        this.activitydate = activitydate;
        this.activityqrcodeurl = activityqrcodeurl;
        this.activityorganizingpeople = activityorganizingpeople;
        this.organizingpeopleopenid = organizingpeopleopenid;
        this.activitystatus = activitystatus;
        this.activityconfiguration = activityconfiguration;
        this.activitylabel = activitylabel;
        this.activitybackgroundpic = activitybackgroundpic;
        this.keep1 = keep1;
        this.keep2 = keep2;
        this.count = count;
        this.createdate = createdate;
        this.updatedate = updatedate;
        this.name = name;
        this.phone = phone;
        this.signaddress = signaddress;
        this.classname = classname;
        this.stunum = stunum;
        this.signupkeep1 = signupkeep1;
        this.signupkeep2 = signupkeep2;
        this.signDate = signDate;
        this.leaveDate = leaveDate;
    }
}
