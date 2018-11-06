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
 * @date 2018-11-06 09:38:34
 */
@TableName("sys_suggestion")
public class SysSuggestionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 建议的内容
	 */
	private String content;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 日期
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
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：建议的内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：建议的内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
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
}
