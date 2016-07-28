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
@TableName(value = "courseware")
public class Courseware implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<Courseware> createExample(){
		return new Example<Courseware>(Courseware.class);
	}

	/**  */
	@TableId(value = "courseware_id")
	private Long coursewareId;

	/** 课程id */
	@TableField(value = "cource_id")
	private Long courceId;

	/** 课程讲id */
	@TableField(value = "lecture_id")
	private Long lectureId;

	/**  */
	@TableField(value = "doc_id")
	private String docId;

	/**  */
	@TableField(value = "file_name")
	private String fileName;

	/**  */
	@TableField(value = "creater_id")
	private Long createrId;

	/**  */
	@TableField(value = "creater_name")
	private String createrName;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	public Long getCoursewareId() {
		return this.coursewareId;
	}

	public void setCoursewareId(Long coursewareId) {
		this.coursewareId = coursewareId;
	}

	public Long getCourceId() {
		return this.courceId;
	}

	public void setCourceId(Long courceId) {
		this.courceId = courceId;
	}

	public Long getLectureId() {
		return this.lectureId;
	}

	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}

	public String getDocId() {
		return this.docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
