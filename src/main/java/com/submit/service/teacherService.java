package com.submit.service;

import com.submit.dao.studentMapper;
import com.submit.dao.teachclassMapper;
import com.submit.dao.teacherMapper;
import com.submit.pojo.student;
import com.submit.pojo.teachclass;
import com.submit.pojo.teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class teacherService {
    private static final Logger logger= LoggerFactory.getLogger(teacherService.class);
    @Autowired(required = false)
    teacherMapper teacherMapper;
    @Autowired(required = false)
    teachclassMapper teachclassMapper;
    @Autowired(required = false)
    private studentMapper studentMapper;
    public teacher getteacherbyid(String teacherid) {
        return teacherMapper.selectByPrimaryKey(teacherid);
    }


    public boolean updatepassword(teacher teacher) {
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    public List<teachclass> getteachcassbyteacherid(String teacherid) {
        //判断时间，以前学期的pass掉
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        // logger.info(year+" "+month);
        List<teachclass> list=teachclassMapper.getteacherclassbyteachid(teacherid);
        List<teachclass>list1=new ArrayList<>();
        for(teachclass teachclass:list)
        {
            String date=(String) teachclass.getCoursesemester();
            String dayear=date.substring(0,4);
            String xueqi=date.substring(10,11);
            if((Integer.parseInt(xueqi)==2&&month<8&&Integer.parseInt(dayear)==year-1)||(Integer.parseInt(xueqi)==2&&month>8&&Integer.parseInt(dayear)==year)){
                list1.add(teachclass);
                logger.info(dayear+" "+xueqi);
            }
        }
        return list1;

    }

    public List<teachclass> getteachcassbyteacheridall(String teacherid) {
        return teachclassMapper.getteacherclassbyteachid(teacherid);
    }

    public teachclass getteachclassbyclassid(String calssid) {
        return teachclassMapper.selectByPrimaryKey(Integer.parseInt(calssid));
    }

    public List<student> gestudentbyclassid(int classid) {
        return studentMapper.getstudentbyclassid(classid);
    }
}
