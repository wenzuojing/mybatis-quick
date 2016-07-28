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
@TableName(value = "user_teacher")
public class UserTeacher implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<UserTeacher> createExample(){
		return new Example<UserTeacher>(UserTeacher.class);
	}

	/**  */
	@TableId
	private Long uid;

	/** 多个时道号分隔 */
	@TableField(value = "grade_names")
	private String gradeNames;

	/** 多个时道号分隔 */
	@TableField(value = "subject_names")
	private String subjectNames;

	/**  */
	private String intro;

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getGradeNames() {
		return this.gradeNames;
	}

	public void setGradeNames(String gradeNames) {
		this.gradeNames = gradeNames;
	}

	public String getSubjectNames() {
		return this.subjectNames;
	}

	public void setSubjectNames(String subjectNames) {
		this.subjectNames = subjectNames;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
