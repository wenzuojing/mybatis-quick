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
@TableName(value = "lecture")
public class Lecture implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<Lecture> createExample(){
		return new Example<Lecture>(Lecture.class);
	}

	/**  */
	@TableId(value = "lecture_id")
	private Long lectureId;

	/** 课程id */
	@TableField(value = "course_id")
	private Long courseId;

	/** 讲次 */
	@TableField(value = "lecture_no")
	private Integer lectureNo;

	/**  */
	private String title;

	/**  */
	@TableField(value = "sub_title")
	private String subTitle;

	/** 1:视频直播 2:视频点播 */
	@TableField(value = "teaching_method")
	private Integer teachingMethod;

	/** 点播视频地址 */
	@TableField(value = "video_url")
	private String videoUrl;

	/** 直播房间 */
	@TableField(value = "room_id")
	private String roomId;

	/**  */
	@TableField(value = "start_time")
	private Date startTime;

	/**  */
	@TableField(value = "end_time")
	private Date endTime;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	/**  */
	@TableField(value = "course_title")
	private String courseTitle;

	/**  */
	@TableField(value = "create_room_log")
	private String createRoomLog;

	/**  */
	@TableField(value = "create_room_hash")
	private String createRoomHash;

	/**  */
	@TableField(value = "create_room_success")
	private Integer createRoomSuccess;

	public Long getLectureId() {
		return this.lectureId;
	}

	public void setLectureId(Long lectureId) {
		this.lectureId = lectureId;
	}

	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getLectureNo() {
		return this.lectureNo;
	}

	public void setLectureNo(Integer lectureNo) {
		this.lectureNo = lectureNo;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return this.subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public Integer getTeachingMethod() {
		return this.teachingMethod;
	}

	public void setTeachingMethod(Integer teachingMethod) {
		this.teachingMethod = teachingMethod;
	}

	public String getVideoUrl() {
		return this.videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getCourseTitle() {
		return this.courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getCreateRoomLog() {
		return this.createRoomLog;
	}

	public void setCreateRoomLog(String createRoomLog) {
		this.createRoomLog = createRoomLog;
	}

	public String getCreateRoomHash() {
		return this.createRoomHash;
	}

	public void setCreateRoomHash(String createRoomHash) {
		this.createRoomHash = createRoomHash;
	}

	public Integer getCreateRoomSuccess() {
		return this.createRoomSuccess;
	}

	public void setCreateRoomSuccess(Integer createRoomSuccess) {
		this.createRoomSuccess = createRoomSuccess;
	}

}
