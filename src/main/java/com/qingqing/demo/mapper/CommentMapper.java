package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.Comment;
import com.qingqing.demo.entity.CommentExample;
import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}