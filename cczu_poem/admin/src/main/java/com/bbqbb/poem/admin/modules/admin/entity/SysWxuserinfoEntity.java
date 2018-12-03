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
 * @date 2018-12-03 15:06:04
 */
@TableName("sys_wxuserinfo")
public class SysWxuserinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private String openid;
	/**
	 * 
	 */
	private String wxheadimageurl;
	/**
	 * 
	 */
	private String wxusername;
	/**
	 * 
	 */
	private String wxotheruserinfo;
	/**
	 * 
	 */
	private Date createdate;
	/**
	 * 
	 */
	private Date updatedate;

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
	 * 设置：
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：
	 */
	public String getOpenid() {
		return openid;
	}
	/**
	 * 设置：
	 */
	public void setWxheadimageurl(String wxheadimageurl) {
		this.wxheadimageurl = wxheadimageurl;
	}
	/**
	 * 获取：
	 */
	public String getWxheadimageurl() {
		return wxheadimageurl;
	}
	/**
	 * 设置：
	 */
	public void setWxusername(String wxusername) {
		this.wxusername = wxusername;
	}
	/**
	 * 获取：
	 */
	public String getWxusername() {
		return wxusername;
	}
	/**
	 * 设置：
	 */
	public void setWxotheruserinfo(String wxotheruserinfo) {
		this.wxotheruserinfo = wxotheruserinfo;
	}
	/**
	 * 获取：
	 */
	public String getWxotheruserinfo() {
		return wxotheruserinfo;
	}
	/**
	 * 设置：
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedate() {
		return createdate;
	}
	/**
	 * 设置：
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
}
