package com.cczu.spider.entity;

import javax.persistence.*;

@Entity
@Table(name = "tool_lecture")
public class LectureEntity {

    private Long id;


    /**
     * 学期
     */
    private String term;

    /**
     * 讲座序号
     */
    private Integer ordernum;

    /**
     * 课程名称
     */
    private String classname;

    /**
     * 教师
     */
    private String teacher;

    /**
     * 课程类别
     */
    private String classtype;

    /**
     * 周次
     */
    private Integer week;

    /**
     * 星期
     */
    private Integer day;

    /**
     * 节次1
     */
    private Integer class1;

    /**
     * 节次2
     */
    private Integer class2;

    /**
     * 教室
     */
    private String classroom;

    /**
     * 选择人数
     */
    private Integer choosenum;

    /**
     * 校区
     */
    private String schoolcampus;

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
     * 获取学期
     *
     * @return Term - 学期
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置学期
     *
     * @param term 学期
     */
    public void setTerm(String term) {
        this.term = term;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    /**
     * 获取课程名称
     *
     * @return ClassName - 课程名称
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 设置课程名称
     *
     * @param classname 课程名称
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * 获取教师
     *
     * @return Teacher - 教师
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * 设置教师
     *
     * @param teacher 教师
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    /**
     * 获取课程类别
     *
     * @return ClassType - 课程类别
     */
    public String getClasstype() {
        return classtype;
    }

    /**
     * 设置课程类别
     *
     * @param classtype 课程类别
     */
    public void setClasstype(String classtype) {
        this.classtype = classtype;
    }

    /**
     * 获取周次
     *
     * @return Week - 周次
     */
    public Integer getWeek() {
        return week;
    }

    /**
     * 设置周次
     *
     * @param week 周次
     */
    public void setWeek(Integer week) {
        this.week = week;
    }

    /**
     * 获取星期
     *
     * @return Day - 星期
     */
    public Integer getDay() {
        return day;
    }

    /**
     * 设置星期
     *
     * @param day 星期
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * 获取节次1
     *
     * @return Class1 - 节次1
     */
    public Integer getClass1() {
        return class1;
    }

    /**
     * 设置节次1
     *
     * @param class1 节次1
     */
    public void setClass1(Integer class1) {
        this.class1 = class1;
    }

    /**
     * 获取节次2
     *
     * @return Class2 - 节次2
     */
    public Integer getClass2() {
        return class2;
    }

    /**
     * 设置节次2
     *
     * @param class2 节次2
     */
    public void setClass2(Integer class2) {
        this.class2 = class2;
    }

    /**
     * 获取教室
     *
     * @return ClassRoom - 教室
     */
    public String getClassroom() {
        return classroom;
    }

    /**
     * 设置教室
     *
     * @param classroom 教室
     */
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    /**
     * 获取选择人数
     *
     * @return ChooseNum - 选择人数
     */
    public Integer getChoosenum() {
        return choosenum;
    }

    /**
     * 设置选择人数
     *
     * @param choosenum 选择人数
     */
    public void setChoosenum(Integer choosenum) {
        this.choosenum = choosenum;
    }

    /**
     * 获取校区
     *
     * @return SchoolCampus - 校区
     */
    public String getSchoolcampus() {
        return schoolcampus;
    }

    /**
     * 设置校区
     *
     * @param schoolcampus 校区
     */
    public void setSchoolcampus(String schoolcampus) {
        this.schoolcampus = schoolcampus;
    }
}
