package com.bbqbb.poem.admin.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-06 16:13:12
 */
@TableName("sys_title")
public class SysTitleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
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
	private Integer privatestatus;
	/**
	 * 默认0，不删除
	 */
	private Integer delstatus;
	/**
	 * 默认0 需审核
	 */
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


	private String avatarurl;

	private String wxotherinfo;

	private Integer delCode;

	public Integer getDelCode() {
		return delCode;
	}

	public void setDelCode(Integer delCode) {
		this.delCode = delCode;
	}

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：文章标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：文章标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：文章图片
	 */
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	/**
	 * 获取：文章图片
	 */
	public String getImageurl() {
		return imageurl;
	}
	/**
	 * 设置：图片的宽
	 */
	public void setImagewidth(Integer imagewidth) {
		this.imagewidth = imagewidth;
	}
	/**
	 * 获取：图片的宽
	 */
	public Integer getImagewidth() {
		return imagewidth;
	}
	/**
	 * 设置：图片的高
	 */
	public void setImageheight(Integer imageheight) {
		this.imageheight = imageheight;
	}
	/**
	 * 获取：图片的高
	 */
	public Integer getImageheight() {
		return imageheight;
	}
	/**
	 * 设置：是否原创 默认1原创
	 */
	public void setOriginal(Integer original) {
		this.original = original;
	}
	/**
	 * 获取：是否原创 默认1原创
	 */
	public Integer getOriginal() {
		return original;
	}
	/**
	 * 设置：
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：是否私密，默认0不私密
	 */
	public void setPrivatestatus(Integer privatestatus) {
		this.privatestatus = privatestatus;
	}
	/**
	 * 获取：是否私密，默认0不私密
	 */
	public Integer getPrivatestatus() {
		return privatestatus;
	}
	/**
	 * 设置：默认0，不删除
	 */
	public void setDelstatus(Integer delstatus) {
		this.delstatus = delstatus;
	}
	/**
	 * 获取：默认0，不删除
	 */
	public Integer getDelstatus() {
		return delstatus;
	}
	/**
	 * 设置：默认0 需审核
	 */
	public void setAdminstatus(Integer adminstatus) {
		this.adminstatus = adminstatus;
	}
	/**
	 * 获取：默认0 需审核
	 */
	public Integer getAdminstatus() {
		return adminstatus;
	}
	/**
	 * 设置：对应sys_zone的ID
	 */
	public void setZoneid(Long zoneid) {
		this.zoneid = zoneid;
	}
	/**
	 * 获取：对应sys_zone的ID
	 */
	public Long getZoneid() {
		return zoneid;
	}
	/**
	 * 设置：作者
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 获取：作者
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置：标签
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * 获取：标签
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * 设置：喜欢
	 */
	public void setLikecount(Integer likecount) {
		this.likecount = likecount;
	}
	/**
	 * 获取：喜欢
	 */
	public Integer getLikecount() {
		return likecount;
	}
	/**
	 * 设置：不喜欢
	 */
	public void setNotlikecount(Integer notlikecount) {
		this.notlikecount = notlikecount;
	}
	/**
	 * 获取：不喜欢
	 */
	public Integer getNotlikecount() {
		return notlikecount;
	}
	/**
	 * 设置：日期
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：日期
	 */
	public Date getCreatedate() {
		return createdate;
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
