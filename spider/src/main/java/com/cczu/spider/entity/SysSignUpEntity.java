package com.cczu.spider.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_signup")
public class SysSignUpEntity {

    private Long ID;
    private String openid;
    private Long activityID;
    private String name;
    private String phone;
    private String wxheadimageurl;
    private String wxusername;
    private String wxotherinfo;
    private String signaddress;
    private Boolean status;
    private String classname;
    private String stunum;
    private String keep1;
    private String keep2;
    private Date signdate;
    private Date leavedate;
    private Date createdate;
    private Date updatedate;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getActivityID() {
        return activityID;
    }

    public void setActivityID(Long activityID) {
        this.activityID = activityID;
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

    public String getWxheadimageurl() {
        return wxheadimageurl;
    }

    public void setWxheadimageurl(String wxheadimageurl) {
        this.wxheadimageurl = wxheadimageurl;
    }

    public String getWxusername() {
        return wxusername;
    }

    public void setWxusername(String wxsername) {
        this.wxusername = wxsername;
    }

    public String getWxotherinfo() {
        return wxotherinfo;
    }

    public void setWxotherinfo(String wxotherinfo) {
        this.wxotherinfo = wxotherinfo;
    }

    public String getSignaddress() {
        return signaddress;
    }

    public void setSignaddress(String signaddress) {
        this.signaddress = signaddress;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Date getSigndate() {
        return signdate;
    }

    public void setSigndate(Date signdate) {
        this.signdate = signdate;
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}
