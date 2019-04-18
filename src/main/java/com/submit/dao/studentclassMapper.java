package com.submit.dao;

import com.submit.pojo.studentclass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface studentclassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(studentclass record);

    int insertSelective(studentclass record);

    studentclass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(studentclass record);

    int updateByPrimaryKey(studentclass record);

    @Delete("delete from studentclass where classID=#{stuclaid} and studentno=#{studentid}")
    boolean deletebystuclaid(@Param("stuclaid") int stuclaid,@Param("studentid") String studentid);
}