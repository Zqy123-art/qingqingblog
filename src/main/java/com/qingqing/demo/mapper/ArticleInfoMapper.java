package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.ArticleInfo;
import com.qingqing.demo.entity.ArticleInfoExample;
import java.util.List;

public interface ArticleInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticleInfo record);

    int insertSelective(ArticleInfo record);

    List<ArticleInfo> selectByExample(ArticleInfoExample example);

    ArticleInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticleInfo record);

    int updateByPrimaryKey(ArticleInfo record);
}