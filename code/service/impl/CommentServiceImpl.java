package com.zy.live.service.impl;

import org.springframework.stereotype.Service;

import com.zy.live.mapper.CommentMapper;
import com.zy.live.entity.Comment;
import com.zy.live.service.ICommentService;
import com.github.wens.framework.service.impl.SuperServiceImpl;

/**
 *
 * Comment 表数据服务层接口实现类
 *
 */
@Service
public class CommentServiceImpl extends SuperServiceImpl<CommentMapper, Comment,Long> implements ICommentService {


}