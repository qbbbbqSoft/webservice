package com.cczu.spider.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_course")
public class SysCourseEntity {

    @Column(name = "ID")
    private Long id;

    private Integer week;

    private String course1;

    private String course2;

    private String course3;

    private String course4;

    private String course5;

    private String course6;

    private String course7;

    private String course8;

    private String course9;

    private String course10;

    private String course11;

    private String course12;

    private String remind;

    @Column(name = "openID")
    private String openid;

    @Column(name = "createDate")
    private Date createdate;

    /**
     * @return ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return week
     */
    public Integer getWeek() {
        return week;
    }

    /**
     * @param week
     */
    public void setWeek(Integer week) {
        this.week = week;
    }

    /**
     * @return course1
     */
    public String getCourse1() {
        return course1;
    }

    /**
     * @param course1
     */
    public void setCourse1(String course1) {
        this.course1 = course1;
    }

    /**
     * @return course2
     */
    public String getCourse2() {
        return course2;
    }

    /**
     * @param course2
     */
    public void setCourse2(String course2) {
        this.course2 = course2;
    }

    /**
     * @return course3
     */
    public String getCourse3() {
        return course3;
    }

    /**
     * @param course3
     */
    public void setCourse3(String course3) {
        this.course3 = course3;
    }

    /**
     * @return course4
     */
    public String getCourse4() {
        return course4;
    }

    /**
     * @param course4
     */
    public void setCourse4(String course4) {
        this.course4 = course4;
    }

    /**
     * @return course5
     */
    public String getCourse5() {
        return course5;
    }

    /**
     * @param course5
     */
    public void setCourse5(String course5) {
        this.course5 = course5;
    }

    /**
     * @return course6
     */
    public String getCourse6() {
        return course6;
    }

    /**
     * @param course6
     */
    public void setCourse6(String course6) {
        this.course6 = course6;
    }

    /**
     * @return course7
     */
    public String getCourse7() {
        return course7;
    }

    /**
     * @param course7
     */
    public void setCourse7(String course7) {
        this.course7 = course7;
    }

    /**
     * @return course8
     */
    public String getCourse8() {
        return course8;
    }

    /**
     * @param course8
     */
    public void setCourse8(String course8) {
        this.course8 = course8;
    }

    /**
     * @return course9
     */
    public String getCourse9() {
        return course9;
    }

    /**
     * @param course9
     */
    public void setCourse9(String course9) {
        this.course9 = course9;
    }

    /**
     * @return course10
     */
    public String getCourse10() {
        return course10;
    }

    /**
     * @param course10
     */
    public void setCourse10(String course10) {
        this.course10 = course10;
    }

    /**
     * @return course11
     */
    public String getCourse11() {
        return course11;
    }

    /**
     * @param course11
     */
    public void setCourse11(String course11) {
        this.course11 = course11;
    }

    /**
     * @return course12
     */
    public String getCourse12() {
        return course12;
    }

    /**
     * @param course12
     */
    public void setCourse12(String course12) {
        this.course12 = course12;
    }

    /**
     * @return remind
     */
    public String getRemind() {
        return remind;
    }

    /**
     * @param remind
     */
    public void setRemind(String remind) {
        this.remind = remind;
    }

    /**
     * @return openID
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * @param openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * @return createDate
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * @param createdate
     */
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
}
