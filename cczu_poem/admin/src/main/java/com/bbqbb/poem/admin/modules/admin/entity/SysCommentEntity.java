package com.bbqbb.poem.admin.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论表，提供无用户评论，用户信息为空
 * 
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-07 08:55:10
 */
@TableName("sys_comment")
public class SysCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 对应的word的ID
	 */
	private Long titleid;
	/**
	 * 评论的内容
	 */
	private String comment;
	/**
	 * 评论人的ID
	 */
	private Long commentid;
	/**
	 * 评论人微信昵称
	 */
	private String nickname;
	/**
	 * 评论人的微信头像
	 */
	private String avatarurl;
	/**
	 * 评论人微信的信息
	 */
	private String wxotherinfo;

	private Integer greatcount;
	/**
	 * 评论时间
	 */
	private Date createdate;

	public Integer getGreatcount() {
		return greatcount;
	}

	public void setGreatcount(Integer greatcount) {
		this.greatcount = greatcount;
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
	 * 设置：对应的word的ID
	 */
	public void setTitleid(Long titleid) {
		this.titleid = titleid;
	}
	/**
	 * 获取：对应的word的ID
	 */
	public Long getTitleid() {
		return titleid;
	}
	/**
	 * 设置：评论的内容
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：评论的内容
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：评论人的ID
	 */
	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}
	/**
	 * 获取：评论人的ID
	 */
	public Long getCommentid() {
		return commentid;
	}
	/**
	 * 设置：评论人微信昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：评论人微信昵称
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * 设置：评论人的微信头像
	 */
	public void setAvatarurl(String avatarurl) {
		this.avatarurl = avatarurl;
	}
	/**
	 * 获取：评论人的微信头像
	 */
	public String getAvatarurl() {
		return avatarurl;
	}
	/**
	 * 设置：评论人微信的信息
	 */
	public void setWxotherinfo(String wxotherinfo) {
		this.wxotherinfo = wxotherinfo;
	}
	/**
	 * 获取：评论人微信的信息
	 */
	public String getWxotherinfo() {
		return wxotherinfo;
	}
	/**
	 * 设置：评论时间
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：评论时间
	 */
	public Date getCreatedate() {
		return createdate;
	}
}
