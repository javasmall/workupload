package com.submit.dao;

import com.submit.pojo.teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface teacherMapper {
    int deleteByPrimaryKey(String teacherno);

    int insert(teacher record);

    int insertSelective(teacher record);

    teacher selectByPrimaryKey(String teacherno);

    boolean updateByPrimaryKeySelective(teacher record);

    int updateByPrimaryKey(teacher record);

    @Select("select * from teacher")
    List<teacher> getAllTeacher();
}