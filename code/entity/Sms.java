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
@TableName(value = "sms")
public class Sms implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<Sms> createExample(){
		return new Example<Sms>(Sms.class);
	}

	/**  */
	@TableId
	private Long sid;

	/** 1:验证码 2:其他 */
	@TableField(value = "sms_type")
	private Integer smsType;

	/**  */
	private String content;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	/**  */
	@TableField(value = "phone_no")
	private String phoneNo;

	public Long getSid() {
		return this.sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public Integer getSmsType() {
		return this.smsType;
	}

	public void setSmsType(Integer smsType) {
		this.smsType = smsType;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

}
