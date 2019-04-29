package com.submit.dao;

import com.submit.pojo.studentclass;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface studentclassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(studentclass record);

    boolean insertSelective(studentclass record);

    studentclass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(studentclass record);

    int updateByPrimaryKey(studentclass record);

    @Delete("delete from studentclass where classID=#{teachclaid} and studentno=#{studentid}")
    boolean deletebytwoid(@Param("teachclaid") int teachclaid,@Param("studentid") String studentid);

    @Update("update studentclass set no=#{num} where id=#{stuclaid}")
    boolean updatestuno(@Param("stuclaid") int stuclaid, @Param("num") int num);

    @Select("select a.id, a.no,s.name,s.pinyin,a.classid, a.studentno,a.note from studentclass a " +
            "left join student s on a.studentno = s.studentno " +
            "where a.classID=#{classid} " +
            "order by a.no asc ")
    List<Map> getstudentbyclaid(String classid);
}