package com.zy.app.entity;

import com.github.wens.mybatisplus.annotations.TableField;
import com.github.wens.mybatisplus.annotations.TableId;
import com.github.wens.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 *
 *
 *
 */
@TableName(value = "tb_user")
public class TbUser implements Serializable {

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 数据ID，与卓越同步
     */
    @TableId(value = "USER_ID")
    private String userId;

    /**  */
    @TableField(value = "PERSON_NAME")
    private String personName;

    /**
     * 用户名（登录名）
     */
    @TableField(value = "USER_NAME")
    private String userName;

    /**
     * 昵称，即客户端和后台显示的名字
     */
    private String nickname;

    /**
     * 用户头像图片的路径
     */
    private String icon;

    /**  */
    private String location;

    /**  */
    private Long integral;

    /**  */
    @TableField(value = "USER_TYPE")
    private String userType;

    /**  */
    private String identity;

    /**  */
    @TableField(value = "RESERVE_NO")
    private String reserveNo;

    /**  */
    @TableField(value = "PHONE_NO")
    private String phoneNo;

    /**  */
    @TableField(value = "CHILD_GRADE")
    private String childGrade;

    /**  */
    @TableField(value = "CAMPUS_ID")
    private String campusId;

    /**  */
    @TableField(value = "CAMPUS_NAME")
    private String campusName;

    /**  */
    @TableField(value = "BUS_TYPE")
    private String busType;

    /**  */
    private String memo;

    /**  */
    private String status;

    /**  */
    @TableField(value = "CRE_TIME")
    private Date creTime;

    /**  */
    @TableField(value = "EDIT_TIME")
    private Date editTime;

    /**  */
    private String weixin;

    /**  */
    private String course;

    /**  */
    private String password;

    /**  */
    @TableField(value = "is_master")
    private String isMaster;

    /**  */
    @TableField(value = "IS_VIP")
    private String isVip;

    /**  */
    @TableField(value = "IS_STMS")
    private String isStms;

    /**  */
    @TableField(value = "TEACH_NATURE")
    private String teachNature;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getIntegral() {
        return this.integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getIdentity() {
        return this.identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getReserveNo() {
        return this.reserveNo;
    }

    public void setReserveNo(String reserveNo) {
        this.reserveNo = reserveNo;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getChildGrade() {
        return this.childGrade;
    }

    public void setChildGrade(String childGrade) {
        this.childGrade = childGrade;
    }

    public String getCampusId() {
        return this.campusId;
    }

    public void setCampusId(String campusId) {
        this.campusId = campusId;
    }

    public String getCampusName() {
        return this.campusName;
    }

    public void setCampusName(String campusName) {
        this.campusName = campusName;
    }

    public String getBusType() {
        return this.busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreTime() {
        return this.creTime;
    }

    public void setCreTime(Date creTime) {
        this.creTime = creTime;
    }

    public Date getEditTime() {
        return this.editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getWeixin() {
        return this.weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIsMaster() {
        return this.isMaster;
    }

    public void setIsMaster(String isMaster) {
        this.isMaster = isMaster;
    }

    public String getIsVip() {
        return this.isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getIsStms() {
        return this.isStms;
    }

    public void setIsStms(String isStms) {
        this.isStms = isStms;
    }

    public String getTeachNature() {
        return this.teachNature;
    }

    public void setTeachNature(String teachNature) {
        this.teachNature = teachNature;
    }

    @Override
    public String toString() {
        return "TbUser{" +
                "userId='" + userId + '\'' +
                ", personName='" + personName + '\'' +
                ", userName='" + userName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", icon='" + icon + '\'' +
                ", location='" + location + '\'' +
                ", integral=" + integral +
                ", userType='" + userType + '\'' +
                ", identity='" + identity + '\'' +
                ", reserveNo='" + reserveNo + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", childGrade='" + childGrade + '\'' +
                ", campusId='" + campusId + '\'' +
                ", campusName='" + campusName + '\'' +
                ", busType='" + busType + '\'' +
                ", memo='" + memo + '\'' +
                ", status='" + status + '\'' +
                ", creTime=" + creTime +
                ", editTime=" + editTime +
                ", weixin='" + weixin + '\'' +
                ", course='" + course + '\'' +
                ", password='" + password + '\'' +
                ", isMaster='" + isMaster + '\'' +
                ", isVip='" + isVip + '\'' +
                ", isStms='" + isStms + '\'' +
                ", teachNature='" + teachNature + '\'' +
                '}';
    }
}
