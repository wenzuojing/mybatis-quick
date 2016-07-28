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
@TableName(value = "course_info")
public class CourseInfo implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<CourseInfo> createExample(){
		return new Example<CourseInfo>(CourseInfo.class);
	}

	/**  */
	@TableId(value = "course_id")
	private Long courseId;

	/**  */
	private String title;

	/**  */
	@TableField(value = "sub_title")
	private String subTitle;

	/**  */
	private BigDecimal price;

	/**  */
	private String tags;

	/** 适合对象 */
	@TableField(value = "suit_for")
	private String suitFor;

	/** 课程介绍 */
	private String intro;

	/** 教师简介 */
	@TableField(value = "teacher_intro")
	private String teacherIntro;

	/**  */
	private String cover;

	/**  */
	@TableField(value = "creater_id")
	private Long createrId;

	/**  */
	@TableField(value = "creater_name")
	private String createrName;

	/**  */
	@TableField(value = "teacher_id")
	private Long teacherId;

	/**  */
	@TableField(value = "teacher_name")
	private String teacherName;

	/** 报名人数 */
	@TableField(value = "sign_amount")
	private Integer signAmount;

	/**  */
	@TableField(value = "start_time")
	private Date startTime;

	/**  */
	@TableField(value = "end_time")
	private Date endTime;

	/** 状态 1:创建/编辑 2:发布 3:下架 */
	private Integer status;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	/** 讲数 */
	@TableField(value = "lecture_count")
	private Integer lectureCount;

	/**  */
	@TableField(value = "grade_name")
	private String gradeName;

	/**  */
	@TableField(value = "subject_name")
	private String subjectName;

	public Long getCourseId() {
		return this.courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSuitFor() {
		return this.suitFor;
	}

	public void setSuitFor(String suitFor) {
		this.suitFor = suitFor;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTeacherIntro() {
		return this.teacherIntro;
	}

	public void setTeacherIntro(String teacherIntro) {
		this.teacherIntro = teacherIntro;
	}

	public String getCover() {
		return this.cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
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

	public Long getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return this.teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getSignAmount() {
		return this.signAmount;
	}

	public void setSignAmount(Integer signAmount) {
		this.signAmount = signAmount;
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

	public Integer getLectureCount() {
		return this.lectureCount;
	}

	public void setLectureCount(Integer lectureCount) {
		this.lectureCount = lectureCount;
	}

	public String getGradeName() {
		return this.gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

}
