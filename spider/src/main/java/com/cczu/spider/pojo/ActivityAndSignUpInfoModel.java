package com.cczu.spider.pojo;

import java.util.Date;

public class ActivityAndSignUpInfoModel {
    private String activityname;
    private String activityplace;
    private Date activitydate;
    private String activityorganizingpeople;
    private String activityconfiguration;
    private String activitylabel;
    private String keep1;
    private String keep2;
    private Integer count;
    private String name;
    private String phone;
    private String signaddress;
    private String classname;
    private String stunum;
    private String signupkeep1;
    private String signupkeep2;
    private Date signDate;
    private Date leaveDate;

    public String getActivityname() {
        return activityname;
    }

    public void setActivityname(String activityname) {
        this.activityname = activityname;
    }

    public String getActivityplace() {
        return activityplace;
    }

    public void setActivityplace(String activityplace) {
        this.activityplace = activityplace;
    }

    public Date getActivitydate() {
        return activitydate;
    }

    public void setActivitydate(Date activitydate) {
        this.activitydate = activitydate;
    }

    public String getActivityorganizingpeople() {
        return activityorganizingpeople;
    }

    public void setActivityorganizingpeople(String activityorganizingpeople) {
        this.activityorganizingpeople = activityorganizingpeople;
    }

    public String getActivityconfiguration() {
        return activityconfiguration;
    }

    public void setActivityconfiguration(String activityconfiguration) {
        this.activityconfiguration = activityconfiguration;
    }

    public String getActivitylabel() {
        return activitylabel;
    }

    public void setActivitylabel(String activitylabel) {
        this.activitylabel = activitylabel;
    }

    public String getKeep1() {
        return keep1;
    }

    public void setKeep1(String keep1) {
        this.keep1 = keep1;
    }

    public String getKeep2() {
        return keep2;
    }

    public void setKeep2(String keep2) {
        this.keep2 = keep2;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSignaddress() {
        return signaddress;
    }

    public void setSignaddress(String signaddress) {
        this.signaddress = signaddress;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getStunum() {
        return stunum;
    }

    public void setStunum(String stunum) {
        this.stunum = stunum;
    }

    public String getSignupkeep1() {
        return signupkeep1;
    }

    public void setSignupkeep1(String signupkeep1) {
        this.signupkeep1 = signupkeep1;
    }

    public String getSignupkeep2() {
        return signupkeep2;
    }

    public void setSignupkeep2(String signupkeep2) {
        this.signupkeep2 = signupkeep2;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public ActivityAndSignUpInfoModel(String activityname, String activityplace, Date activitydate, String activityorganizingpeople, String activityconfiguration, String activitylabel, String keep1, String keep2, Integer count, String name, String phone, String signaddress, String classname, String stunum, String signupkeep1, String signupkeep2, Date signDate, Date leaveDate) {
        this.activityname = activityname;
        this.activityplace = activityplace;
        this.activitydate = activitydate;
        this.activityorganizingpeople = activityorganizingpeople;
        this.activityconfiguration = activityconfiguration;
        this.activitylabel = activitylabel;
        this.keep1 = keep1;
        this.keep2 = keep2;
        this.count = count;
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
