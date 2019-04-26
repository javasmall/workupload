package com.submit.dao;

import com.submit.pojo.score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    @Select("select e.name,d.* from(SELECT a.`no`,a.classID,a.studentno,b.ID scoreid,b.score," +
            "DATE_FORMAT(b.time,'%Y-%m-%d %h:%m:%s') as time,b.note " +
            "from studentclass a " +
            "LEFT   JOIN  score b " +
            "on a.studentno=b.studentno " +
            "and b.jobID=#{jobid} " +
            "where a.classID =(SELECT teachclassid FROM job  WHERE ID=#{jobid}) " +
            "ORDER BY a.`no` asc)d,student e " +
            "WHERE d.studentno=e.studentno")
    List<Map> getscorebyjobid(int jobid);
}