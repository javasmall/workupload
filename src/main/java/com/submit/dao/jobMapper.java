package com.submit.dao;

import com.submit.pojo.job;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface jobMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(job record);

    int insertSelective(job record);

    job selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(job record);

    int updateByPrimaryKey(job record);
    //插入job，具体的那次作业
    @Insert("insert into job (teachclassid,no,title,duedate,type,note,creteTime)" +
            "values(#{teachclassid},#{no},#{title},#{duedate},#{type},#{note},#{creteTime})")
    boolean insertjob(job job);

    @Select("select * from job where teachclassid=#{teachclassid} order by createTime desc")
    List<job> getjobbyteachclassid(int teacherclassid);
}