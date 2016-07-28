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
@TableName(value = "comment")
public class Comment implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	public static Example<Comment> createExample(){
		return new Example<Comment>(Comment.class);
	}

	/**  */
	@TableId(value = "comment_id")
	private Long commentId;

	/** 评论内容 */
	private String content;

	/**  */
	@TableField(value = "author_id")
	private Long authorId;

	/**  */
	@TableField(value = "author_name")
	private String authorName;

	/** 删除状态 0:正常 1:已删除 */
	private Integer deleted;

	/**  */
	@TableField(value = "create_time")
	private Date createTime;

	/**  */
	@TableField(value = "update_time")
	private Date updateTime;

	/**  */
	@TableField(value = "comment_for")
	private String commentFor;

	/**  */
	private String avatar;

	/**  */
	private String hash;

	public Long getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return this.authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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

	public String getCommentFor() {
		return this.commentFor;
	}

	public void setCommentFor(String commentFor) {
		this.commentFor = commentFor;
	}

	public String getAvatar() {
		return this.avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
