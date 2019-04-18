package com.submit.dao;

import com.submit.pojo.score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface scoreMapper {
    int deleteByPrimaryKey(Long id);

    int insert(score record);

    int insertSelective(score record);

    score selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(score record);

    int updateByPrimaryKey(score record);
    @Insert("insert into score")
    boolean insertscore(score score);

    @Select("select * from score where jobID=#{jobID} and studentno=#{studentid}")
    score uniqueindex(@Param("jobID") Integer id,@Param("studentid") String studentid);
}