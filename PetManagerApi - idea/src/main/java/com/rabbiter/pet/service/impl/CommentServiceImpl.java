package com.rabbiter.pet.service.impl;

import com.rabbiter.pet.entity.Comment;
import com.rabbiter.pet.mapper.CommentMapper;
import com.rabbiter.pet.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2023-04-02
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
