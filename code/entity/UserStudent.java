package com.zy.live.entity;

import java.io.Serializable;

import com.github.wens.mybatisplus.annotations.TableField;
import com.github.wens.mybatisplus.annotations.TableId;
import com.github.wens.mybatisplus.annotations.TableName;
import com.github.wens.mybatisplus.examples.Example;

/**
 *
 * 
 *
 */
@TableName(value = "user_student")
public class UserStudent implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<UserStudent> createExample(){
		return new Example<UserStudent>(UserStudent.class);
	}

	/**  */
	@TableId
	private Long uid;

	/**  */
	@TableField(value = "grade_name")
	private String gradeName;

	/**  */
	@TableField(value = "parent_name")
	private String parentName;

	/**  */
	@TableField(value = "parent_phone_no")
	private String parentPhoneNo;

	/**  */
	@TableField(value = "parent_name2")
	private String parentName2;

	/**  */
	@TableField(value = "parent_phone_no2")
	private String parentPhoneNo2;

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getParentName() {
		return this.parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentPhoneNo() {
		return this.parentPhoneNo;
	}

	public void setParentPhoneNo(String parentPhoneNo) {
		this.parentPhoneNo = parentPhoneNo;
	}

	public String getParentName2() {
		return this.parentName2;
	}

	public void setParentName2(String parentName2) {
		this.parentName2 = parentName2;
	}

	public String getParentPhoneNo2() {
		return this.parentPhoneNo2;
	}

	public void setParentPhoneNo2(String parentPhoneNo2) {
		this.parentPhoneNo2 = parentPhoneNo2;
	}

}
