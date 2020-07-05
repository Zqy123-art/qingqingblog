package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.ArticleContent;
import com.qingqing.demo.entity.ArticleContentExample;
import java.util.List;

public interface ArticleContentMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticleContent record);

    int insertSelective(ArticleContent record);

    List<ArticleContent> selectByExampleWithBLOBs(ArticleContentExample example);

    List<ArticleContent> selectByExample(ArticleContentExample example);

    ArticleContent selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleContent record);

    int updateByPrimaryKeyWithBLOBs(ArticleContent record);

    int updateByPrimaryKey(ArticleContent record);
}