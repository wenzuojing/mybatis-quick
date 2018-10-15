package com.github.wens.entity;

import java.io.Serializable;

import com.github.wens.mybatis.annotations.TableField;
import com.github.wens.mybatis.annotations.TableId;
import com.github.wens.mybatis.annotations.TableName;
import com.github.wens.mybatis.examples.Example;

/**
 *
 * 
 *
 */
@TableName(value = "city")
public class City implements Serializable {

	/**  */
	@TableId
	private Integer id;

	/**  */
	private String name;

	/**  */
	private String state;

	/**  */
	private String country;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
