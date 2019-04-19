package com.submit.dao;

import com.submit.pojo.teachclass;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface teachclassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(teachclass record);

    int insertSelective(teachclass record);

    teachclass selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(teachclass record);

    int updateByPrimaryKey(teachclass record);
    //插入teachclass 比如安卓、jsp、c++等课程信息
    @Insert("insert into teachclass (teachclassno,coursename,coursesemester,credit,evalmethod,teacherno)" +
            "values(#{teachclassno},#{coursename},#{coursesemester},#{credit},#{evalmethod},#{teacherno})")
    boolean insertteachclass(teachclass teachclass);

    @Select("SELECT * from teachclass WHERE ID in " +
            "(SELECT classID FROM studentclass  " +
            "WHERE studentno=#{studentid}) " +
            "GROUP BY coursename,ID " +
            "ORDER BY coursesemester DESC")
    List<teachclass> getteacherclassbystuid(String studentid);

    @Select("select a.*,b.name from teachclass a,teacher b where ID in " +
            "(SELECT classID FROM studentclass  " +
            "WHERE studentno=#{studentid}) " +
            "and a.teacherno=b.teacherno " +
            "GROUP BY a.coursename,a.ID " +
            "ORDER BY a.coursesemester DESC")
    List<Map> getteacherclassbystuid2(String studentid);//唯一区别是多了教师名称

    @Select("select a.*,b.name from teachclass a,teacher b where  " +
            " a.teacherno=b.teacherno " +
            "GROUP BY a.coursename,a.ID " +
            "ORDER BY a.coursesemester DESC")
    List<Map> getallclasslesson();

    @Select("select * from teachclass where teacherno=#{teacherid} ORDER BY coursesemester DESC")
    List<teachclass> getteacherclassbyteachid(String teacherid);
}