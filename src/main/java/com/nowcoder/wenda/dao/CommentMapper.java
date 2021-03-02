package com.nowcoder.wenda.dao;

import com.nowcoder.wenda.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    //查询评论by评论类型，分页及每页最大评论数
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    //查询评论总数by评论类型
    int selectCountByEntity(int entityType, int entityId);

    //添加回帖
    int insertComment(Comment comment);

    //查询评论id
    Comment selectCommentById(int id);
}
