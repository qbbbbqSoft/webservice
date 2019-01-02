package com.cczu.spider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sys_activity")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class SysActivityEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "ID")
    private Long ID;
    @Column(name = "activityID")
    private String activityID;
    @Column(name = "activityName")
    private String activityName;
    @Column(name = "activityPlace")
    private String activityPlace;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "activityDate")
    private Date activityDate;
    @Column(name = "activityQrCodeUrl")
    private String activityQrCodeUrl;
    @Column(name = "activityOrganizingPeople")
    private String activityOrganizingPeople;
    @Column(name = "organizingPeopleOpenID")
    private String organizingPeopleOpenID;
    @Column(name = "activityStatus")
    private Integer activityStatus;
    @Column(name = "activityConfiguration")
    private String activityConfiguration;
    @Column(name = "activityLabel")
    private String activityLabel;
    @Column(name = "activityBackgroundPic")
    private String activityBackgroundPic;
    @Column(name = "keep1")
    private String keep1;
    @Column(name = "keep2")
    private String keep2;
    @Column(name = "count")
    private Integer count;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "createDate")
    private Date createDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name = "updateDate")
    private Date updateDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getActivityID() {
        return activityID;
    }

    public void setActivityID(String activityID) {
        this.activityID = activityID;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityQrCodeUrl() {
        return activityQrCodeUrl;
    }

    public void setActivityQrCodeUrl(String activityQrCodeUrl) {
        this.activityQrCodeUrl = activityQrCodeUrl;
    }

    public String getActivityOrganizingPeople() {
        return activityOrganizingPeople;
    }

    public void setActivityOrganizingPeople(String activityOrganizingPeople) {
        this.activityOrganizingPeople = activityOrganizingPeople;
    }

    public String getOrganizingPeopleOpenID() {
        return organizingPeopleOpenID;
    }

    public void setOrganizingPeopleOpenID(String organizingPeopleOpenID) {
        this.organizingPeopleOpenID = organizingPeopleOpenID;
    }

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getActivityConfiguration() {
        return activityConfiguration;
    }

    public void setActivityConfiguration(String activityConfiguration) {
        this.activityConfiguration = activityConfiguration;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    public String getActivityBackgroundPic() {
        return activityBackgroundPic;
    }

    public void setActivityBackgroundPic(String activityBackgroundPic) {
        this.activityBackgroundPic = activityBackgroundPic;
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
}
