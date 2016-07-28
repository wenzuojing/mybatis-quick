package com.zy.live.entity;

import java.io.Serializable;
import java.util.Date;

import com.github.wens.mybatisplus.annotations.TableField;
import com.github.wens.mybatisplus.annotations.TableId;
import com.github.wens.mybatisplus.annotations.TableName;
import com.github.wens.mybatisplus.examples.Example;

/**
 *
 * 
 *
 */
@TableName(value = "sign_info")
public class SignInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<SignInfo> createExample(){
		return new Example<SignInfo>(SignInfo.class);
	}

	/**  */
	@TableId(value = "sign_id")
	private Long signId;

	/** 课程id */
	@TableField(value = "course_id")
	private Long courseId;

	/**  */
	@TableField(value = "user_id")
	private Long userId;

	/**  */
	private String username;

	/** 状态 1:未支付 2:已支付 3:作废 */
	private Integer status;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	public Long getSignId() {
		return this.signId;
	}

	public void setSignId(Long signId) {
		this.signId = signId;
	}

	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
