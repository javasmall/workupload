package com.submit.dao;

import com.submit.pojo.studentclass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface studentclassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(studentclass record);

    int insertSelective(studentclass record);

    studentclass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(studentclass record);

    int updateByPrimaryKey(studentclass record);

    @Delete("delete from studentclass where classID=#{teachclaid} and studentno=#{studentid}")
    boolean deletebytwoid(@Param("teachclaid") int teachclaid,@Param("studentid") String studentid);

    @Update("update studentclass set no=#{num} where id=#{stuclaid}")
    boolean updatestuno(@Param("stuclaid") int stuclaid, @Param("num") int num);
}