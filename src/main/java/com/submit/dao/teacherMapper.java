package com.submit.dao;

import com.submit.pojo.teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface teacherMapper {
    int deleteByPrimaryKey(String teacherno);

    int insert(teacher record);

    int insertSelective(teacher record);

    teacher selectByPrimaryKey(String teacherno);

    boolean updateByPrimaryKeySelective(teacher record);

    int updateByPrimaryKey(teacher record);
}