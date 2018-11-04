package com.cczu.spider.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "sys_title")
public class SysTitleEntity {

    @Column(name = "ID")
    private Long id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章图片
     */
    @Column(name = "imageUrl")
    private String imageurl;
    @Column(name = "imageWidth")
    private String imagewidth;
    @Column(name = "imageHeight")
    private String imageheight;

    /**
     * 是否原创 默认1原创
     */
    private Integer original;

    private Integer type;

    private String content;

    /**
     * 是否私密，默认0不私密
     */
    @Column(name = "privateStatus")
    private Integer privatestatus;

    /**
     * 默认0，不删除
     */
    @Column(name = "delStatus")
    private Integer delstatus;

    /**
     * 默认0 需审核
     */
    @Column(name = "adminStatus")
    private Integer adminstatus;

    /**
     * 对应sys_zone的ID
     */
    @Column(name = "zoneID")
    private Long zoneid;

    private String author;

    /**
     * 标签
     */
    private String label;

    @Column(name = "createDate")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
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
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文章图片
     *
     * @return imageUrl - 文章图片
     */
    public String getImageurl() {
        return imageurl;
    }

    /**
     * 设置文章图片
     *
     * @param imageurl 文章图片
     */
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    /**
     * 获取是否原创 默认1原创
     *
     * @return original - 是否原创 默认1原创
     */
    public Integer getOriginal() {
        return original;
    }

    /**
     * 设置是否原创 默认1原创
     *
     * @param original 是否原创 默认1原创
     */
    public void setOriginal(Integer original) {
        this.original = original;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取是否私密，默认0不私密
     *
     * @return privateStatus - 是否私密，默认0不私密
     */
    public Integer getPrivatestatus() {
        return privatestatus;
    }

    /**
     * 设置是否私密，默认0不私密
     *
     * @param privatestatus 是否私密，默认0不私密
     */
    public void setPrivatestatus(Integer privatestatus) {
        this.privatestatus = privatestatus;
    }

    /**
     * 获取默认0，不删除
     *
     * @return delStatus - 默认0，不删除
     */
    public Integer getDelstatus() {
        return delstatus;
    }

    /**
     * 设置默认0，不删除
     *
     * @param delstatus 默认0，不删除
     */
    public void setDelstatus(Integer delstatus) {
        this.delstatus = delstatus;
    }

    /**
     * 获取默认0 需审核
     *
     * @return adminStatus - 默认0 需审核
     */
    public Integer getAdminstatus() {
        return adminstatus;
    }

    /**
     * 设置默认0 需审核
     *
     * @param adminstatus 默认0 需审核
     */
    public void setAdminstatus(Integer adminstatus) {
        this.adminstatus = adminstatus;
    }

    /**
     * 获取对应sys_zone的ID
     *
     * @return zoneID - 对应sys_zone的ID
     */
    public Long getZoneid() {
        return zoneid;
    }

    /**
     * 设置对应sys_zone的ID
     *
     * @param zoneid 对应sys_zone的ID
     */
    public void setZoneid(Long zoneid) {
        this.zoneid = zoneid;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取标签
     *
     * @return label - 标签
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置标签
     *
     * @param label 标签
     */
    public void setLabel(String label) {
        this.label = label;
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

    public String getImagewidth() {
        return imagewidth;
    }

    public void setImagewidth(String imagewidth) {
        this.imagewidth = imagewidth;
    }

    public String getImageheight() {
        return imageheight;
    }

    public void setImageheight(String imageheight) {
        this.imageheight = imageheight;
    }
}
