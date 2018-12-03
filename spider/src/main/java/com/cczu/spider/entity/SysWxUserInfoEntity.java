package com.cczu.spider.entity;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "sys_wxuserinfo")
public class SysWxUserInfoEntity {
    private Long ID;
    private String openid;
    private String wxheadimageurl;
    private String wxusername;
    private String wxotheruserinfo;

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

    public String getWxheadimageurl() {
        return wxheadimageurl;
    }

    public void setWxheadimageurl(String wxheadimageurl) {
        this.wxheadimageurl = wxheadimageurl;
    }

    public String getWxusername() {
        return wxusername;
    }

    public void setWxusername(String wxusername) {
        this.wxusername = wxusername;
    }

    public String getWxotheruserinfo() {
        return wxotheruserinfo;
    }

    public void setWxotheruserinfo(String wxotheruserinfo) {
        this.wxotheruserinfo = wxotheruserinfo;
    }
}
