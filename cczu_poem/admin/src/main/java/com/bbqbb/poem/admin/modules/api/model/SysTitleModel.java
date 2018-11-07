package com.bbqbb.poem.admin.modules.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class SysTitleModel {

    private Long id;
    /**
     * 文章标题
     */
    private String title;
    /**
     * 文章图片
     */
    private String imageurl;
    /**
     * 图片的宽
     */
    private Integer imagewidth;
    /**
     * 图片的高
     */
    private Integer imageheight;
    /**
     * 是否原创 默认1原创
     */
    private Integer original;
    /**
     *
     */
    private Integer type;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否私密，默认0不私密
     */
    @JsonIgnore
    private Integer privatestatus;
    /**
     * 默认0，不删除
     */
    @JsonIgnore
    private Integer delstatus;
    /**
     * 默认0 需审核
     */
    @JsonIgnore
    private Integer adminstatus;
    /**
     * 对应sys_zone的ID
     */
    private Long zoneid;
    /**
     * 作者
     */
    private String author;
    /**
     * 标签
     */
    private String label;
    /**
     * 喜欢
     */
    private Integer likecount;
    /**
     * 不喜欢
     */
    private Integer notlikecount;
    /**
     * 日期
     */
    private Date createdate;

    private Integer commentCount;


    private String avatarurl;

    private String wxotherinfo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Integer getImagewidth() {
        return imagewidth;
    }

    public void setImagewidth(Integer imagewidth) {
        this.imagewidth = imagewidth;
    }

    public Integer getImageheight() {
        return imageheight;
    }

    public void setImageheight(Integer imageheight) {
        this.imageheight = imageheight;
    }

    public Integer getOriginal() {
        return original;
    }

    public void setOriginal(Integer original) {
        this.original = original;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPrivatestatus() {
        return privatestatus;
    }

    public void setPrivatestatus(Integer privatestatus) {
        this.privatestatus = privatestatus;
    }

    public Integer getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(Integer delstatus) {
        this.delstatus = delstatus;
    }

    public Integer getAdminstatus() {
        return adminstatus;
    }

    public void setAdminstatus(Integer adminstatus) {
        this.adminstatus = adminstatus;
    }

    public Long getZoneid() {
        return zoneid;
    }

    public void setZoneid(Long zoneid) {
        this.zoneid = zoneid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getLikecount() {
        return likecount;
    }

    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Integer getNotlikecount() {
        return notlikecount;
    }

    public void setNotlikecount(Integer notlikecount) {
        this.notlikecount = notlikecount;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getAvatarurl() {
        return avatarurl;
    }

    public void setAvatarurl(String avatarurl) {
        this.avatarurl = avatarurl;
    }

    public String getWxotherinfo() {
        return wxotherinfo;
    }

    public void setWxotherinfo(String wxotherinfo) {
        this.wxotherinfo = wxotherinfo;
    }
}
