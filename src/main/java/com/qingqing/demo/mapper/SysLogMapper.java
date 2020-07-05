package com.qingqing.demo.mapper;

import com.qingqing.demo.entity.SysLog;
import com.qingqing.demo.entity.SysLogExample;
import java.util.List;

public interface SysLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    List<SysLog> selectByExample(SysLogExample example);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);
}