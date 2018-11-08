package com.bbqbb.poem.admin.modules.admin.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * Private zone 
 * 
 * @author bbqbb
 * @email ********@****.com
 * @date 2018-11-07 08:55:10
 */
@TableName("sys_zone")
public class SysZoneEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 唯一的code
	 */
	private String zonecode;
	/**
	 * 第一个创建的人可以设置
	 */
	private String zonename;

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
	/**
	 * 创建时间
	 */
	private Date createdate;

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
	 * 设置：唯一的code
	 */
	public void setZonecode(String zonecode) {
		this.zonecode = zonecode;
	}
	/**
	 * 获取：唯一的code
	 */
	public String getZonecode() {
		return zonecode;
	}
	/**
	 * 设置：第一个创建的人可以设置
	 */
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	/**
	 * 获取：第一个创建的人可以设置
	 */
	public String getZonename() {
		return zonename;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedate() {
		return createdate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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
