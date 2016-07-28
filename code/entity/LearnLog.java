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
@TableName(value = "learn_log")
public class LearnLog implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<LearnLog> createExample(){
		return new Example<LearnLog>(LearnLog.class);
	}

	/**  */
	@TableId(value = "log_id")
	private Long logId;

	/** 课程id */
	@TableField(value = "cource_id")
	private Long courceId;

	/** 课程讲id */
	@TableField(value = "lecture_id")
	private Long lectureId;

	/**  */
	private Long uid;

	/**  */
	private Date time;

	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
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

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
