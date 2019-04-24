package com.submit.service;

import com.submit.dao.jobMapper;
import com.submit.dao.studentclassMapper;
import com.submit.dao.teachclassMapper;
import com.submit.dao.teacherMapper;
import com.submit.pojo.job;
import com.submit.pojo.teachclass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class studentService {
    private static final Logger logger = LoggerFactory.getLogger(studentService.class);
    @Autowired(required = false)
    teachclassMapper teachclassMapper;
    @Autowired(required = false)
    jobMapper jobMapper;
    @Autowired(required = false)
    studentclassMapper studentclassMapper;

    public List<teachclass> getList(String studentid) {
        return teachclassMapper.getteacherclassbystuid(studentid);
//        for(teachclass teachclass:list)
//        {
//            String time=teachclass.getCoursesemester();
//            Calendar calendar=Calendar.getInstance();
//            int month=(calendar.get(Calendar.MONTH));
//        }
    }

    public List<job> getjobbyteacherclassid(int teacherclassid) {
        return jobMapper.getjobbyteachclassid(teacherclassid);
    }

    public List<Map> getstudentattendlesson(String studentid) {
        List<Map> list = teachclassMapper.getteacherclassbystuid2(studentid);
        return list;
    }


    public List<Map> getallclasslesson() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        // logger.info(year+" "+month);
        List<Map> list = teachclassMapper.getallclasslesson();
        List<Map> list1 = new ArrayList<>();
        for (Map map : list) {
            String date = (String) map.get("coursesemester");
            String dayear = date.substring(0, 4);
            String xueqi = date.substring(10, 11);
            if ((Integer.parseInt(xueqi) == 2 && month < 8 && Integer.parseInt(dayear) == year - 1) || (Integer.parseInt(xueqi) == 2 && month > 8 && Integer.parseInt(dayear) == year)) {
                list1.add(map);
                logger.info(dayear + " " + xueqi);
            }
        }
        return list1;
    }
}


