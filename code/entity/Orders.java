package com.zy.live.entity;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.github.wens.mybatisplus.annotations.TableField;
import com.github.wens.mybatisplus.annotations.TableId;
import com.github.wens.mybatisplus.annotations.TableName;
import com.github.wens.mybatisplus.examples.Example;

/**
 *
 * 
 *
 */
@TableName(value = "orders")
public class Orders implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<Orders> createExample(){
		return new Example<Orders>(Orders.class);
	}

	/**  */
	@TableId
	private Long oid;

	/** 关联id */
	@TableField(value = "rel_id")
	private Long relId;

	/** 摘要 */
	private String summary;

	/** 金额 */
	private BigDecimal money;

	/** 支付方式 1:微信 2:支付报 3:信用卡 4:其他 */
	private String payment;

	/**  */
	@TableField(value = "creater_id")
	private Long createrId;

	/**  */
	@TableField(value = "creater_name")
	private String createrName;

	/** 状态 1:未支付 2:已支付 */
	private Integer status;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	public Long getOid() {
		return this.oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Long getRelId() {
		return this.relId;
	}

	public void setRelId(Long relId) {
		this.relId = relId;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getPayment() {
		return this.payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Long getCreaterId() {
		return this.createrId;
	}

	public void setCreaterId(Long createrId) {
		this.createrId = createrId;
	}

	public String getCreaterName() {
		return this.createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
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
