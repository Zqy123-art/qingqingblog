package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.CategoryInfo;
import com.qingqing.demo.entity.CategoryInfoExample;
import java.util.List;

public interface CategoryInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(CategoryInfo record);

    int insertSelective(CategoryInfo record);

    List<CategoryInfo> selectByExample(CategoryInfoExample example);

    CategoryInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CategoryInfo record);

    int updateByPrimaryKey(CategoryInfo record);
}