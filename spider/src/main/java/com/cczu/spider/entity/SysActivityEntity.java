package com.cczu.spider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sys_activity")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class SysActivityEntity {
    @Column(name = "ID")
    private Long ID;
    @Column(name = "activityID")
    private String activityID;
    @Column(name = "activityName")
    private String activityName;
    @Column(name = "activityPlcae")
    private String activityPlcae;
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
    @Column(name = "createDate")
    private Date createDate;
    @Column(name = "updateDate")
    private Date updateDate;

    @Id
    @JsonIgnore
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

    public String getActivityPlcae() {
        return activityPlcae;
    }

    public void setActivityPlcae(String activityPlcae) {
        this.activityPlcae = activityPlcae;
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
}
