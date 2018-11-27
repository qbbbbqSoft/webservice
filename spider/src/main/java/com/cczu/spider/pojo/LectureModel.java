package com.cczu.spider.pojo;

public class LectureModel {
    private String order;
    private String term;
    private String lectureOrder;
    private String lectureName;
    private String className;
    private String stuNum;
    private String stuName;

    public LectureModel(String order, String term, String lectureOrder, String lectureName, String className, String stuNum, String stuName) {
        this.order = order;
        this.term = term;
        this.lectureOrder = lectureOrder;
        this.lectureName = lectureName;
        this.className = className;
        this.stuNum = stuNum;
        this.stuName = stuName;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLectureOrder() {
        return lectureOrder;
    }

    public void setLectureOrder(String lectureOrder) {
        this.lectureOrder = lectureOrder;
    }

    public String getLectureName() {
        return lectureName;
    }

    public void setLectureName(String lectureName) {
        this.lectureName = lectureName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStuNum() {
        return stuNum;
    }

    public void setStuNum(String stuNum) {
        this.stuNum = stuNum;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
