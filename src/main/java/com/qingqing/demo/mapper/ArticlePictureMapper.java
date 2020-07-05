package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.ArticlePicture;
import com.qingqing.demo.entity.ArticlePictureExample;
import java.util.List;

public interface ArticlePictureMapper {
    int deleteByPrimaryKey(String id);

    int insert(ArticlePicture record);

    int insertSelective(ArticlePicture record);

    List<ArticlePicture> selectByExample(ArticlePictureExample example);

    ArticlePicture selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ArticlePicture record);

    int updateByPrimaryKey(ArticlePicture record);
}