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
@TableName(value = "discount_coupon")
public class DiscountCoupon implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<DiscountCoupon> createExample(){
		return new Example<DiscountCoupon>(DiscountCoupon.class);
	}

	/**  */
	@TableId
	private Long did;

	/** 关联id */
	private Long name;

	/** 状态 1:现金券 2:折扣券 */
	private Integer type;

	/** 数量 */
	private Integer amount;

	/** 剩余数量 */
	private Integer remaining;

	/** 金额 */
	private BigDecimal money;

	/** 折扣 */
	private BigDecimal discount;

	/**  */
	@TableField(value = "creater_id")
	private Long createrId;

	/**  */
	@TableField(value = "creater_name")
	private String createrName;

	/** 状态 1:正常 2:发放完毕 */
	private Integer status;

	/** 删除状态 0:正常 1:已删除 */
	private Integer deleted;

	/**  */
	@TableField(value = "expire_time")
	private Date expireTime;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	public Long getDid() {
		return this.did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Long getName() {
		return this.name;
	}

	public void setName(Long name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getRemaining() {
		return this.remaining;
	}

	public void setRemaining(Integer remaining) {
		this.remaining = remaining;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getDiscount() {
		return this.discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
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

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
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
