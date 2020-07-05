package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.ArticleCategory;
import com.qingqing.demo.entity.ArticleCategoryExample;
import java.util.List;

public interface ArticleCategoryMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticleCategory record);

    int insertSelective(ArticleCategory record);

    List<ArticleCategory> selectByExample(ArticleCategoryExample example);

    ArticleCategory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleCategory record);

    int updateByPrimaryKey(ArticleCategory record);
}