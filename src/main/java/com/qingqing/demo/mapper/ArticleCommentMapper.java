package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.ArticleComment;
import com.qingqing.demo.entity.ArticleCommentExample;
import java.util.List;

public interface ArticleCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticleComment record);

    int insertSelective(ArticleComment record);

    List<ArticleComment> selectByExample(ArticleCommentExample example);

    ArticleComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleComment record);

    int updateByPrimaryKey(ArticleComment record);
}