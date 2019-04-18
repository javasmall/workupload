package com.submit.dao;

import com.submit.pojo.timetable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface timetableMapper {
    int deleteByPrimaryKey(Long no);

    int insert(timetable record);

    int insertSelective(timetable record);

    timetable selectByPrimaryKey(Long no);

    int updateByPrimaryKeySelective(timetable record);

    int updateByPrimaryKey(timetable record);
}