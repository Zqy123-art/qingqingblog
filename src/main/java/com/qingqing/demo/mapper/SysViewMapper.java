package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.SysView;
import com.qingqing.demo.entity.SysViewExample;
import java.util.List;

public interface SysViewMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysView record);

    int insertSelective(SysView record);

    List<SysView> selectByExample(SysViewExample example);

    SysView selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysView record);

    int updateByPrimaryKey(SysView record);
}