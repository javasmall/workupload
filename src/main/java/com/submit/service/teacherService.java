package com.submit.service;

import com.submit.dao.*;
import com.submit.pojo.*;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class teacherService {
    private static final Logger logger= LoggerFactory.getLogger(teacherService.class);
    @Autowired(required = false)
    teacherMapper teacherMapper;
    @Autowired(required = false)
    teachclassMapper teachclassMapper;
    @Autowired(required = false)
     studentMapper studentMapper;
    @Autowired(required = false)
     studentclassMapper studentclassMapper;
    @Autowired(required = false)
    private jobMapper jobMapper;
    @Autowired(required = false)
    scoreMapper scoreMapper;
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

    public List<Map> gestudentbyclassid(int classid) {
        return studentMapper.getstudentbyclassid(classid);
    }

    public teachclass getteacherclassbyid(String id) {
        return  teachclassMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Transactional
    public void updateteachclass(teachclass teachclass) {
        teachclassMapper.updateByPrimaryKeySelective(teachclass);
    }

    @Transactional
    public void deleteteachclassbyid(int parseInt) {
        teachclassMapper.deleteByPrimaryKey(parseInt);
    }

    public void updatestuclano(int stuclaid, int num) {
        studentclassMapper.updatestuno(stuclaid,num);
    }

    public void addeachclass(teachclass teachclass) {
        teachclassMapper.insertSelective(teachclass);

    }

    public List<job> getjobbyteachclaid(int teachclaid) {
        return jobMapper.getjobbyteachclassid(teachclaid);
    }

    public job getjobbyid(String id) {
        return  jobMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    public void updatejob(job job) {
        jobMapper.updateByPrimaryKeySelective(job);
    }

    public void addstudentuser(student student) {
        studentMapper.insertSelective(student);
    }

    public void lessonaddstudent(studentclass studentclass) {
        studentclassMapper.insertSelective(studentclass);
    }

    @Transactional
    public void deletestuclassbytwoid(int teachclassid, String studentno) {
        studentclassMapper.deletebytwoid(teachclassid,studentno);
    }

    public List<teachclass> getteacherclassthisterm(String teacherid) {
        List<teachclass>list=teachclassMapper.getteacherclassbyteachid(teacherid);
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        List<teachclass> list1 = new ArrayList<>();
        for(teachclass te:list)
        {
            String date=te.getCoursesemester();
            String dayear = date.substring(0, 4);
            String xueqi = date.substring(10, 11);
            if ((Integer.parseInt(xueqi) == 2 && month < 8 && Integer.parseInt(dayear) == year - 1) || (Integer.parseInt(xueqi) == 2 && month > 8 && Integer.parseInt(dayear) == year)) {
                list1.add(te);
                logger.info(dayear + "  " + xueqi);
            }
        }
        return list1;

    }

    public List<Map> getscorebyjobid(int jobid) {
        return scoreMapper.getscorebyjobid(jobid);
    }

    @Transactional
    public void addjob(job job) {
        jobMapper.insertSelective(job);
    }

    public void updatescorebyscoreid(String scoreid, String score, String note) {
        logger.info(scoreid+" "+score+" "+note);
        score score1=scoreMapper.selectByPrimaryKey(Long.parseLong(scoreid));
        if(score!=null&&!"".equals(score)){score1.setScore(Integer.parseInt(score));}
        if(note!=null&&!"".equals(note)){score1.setNote(note);}
        scoreMapper.updateByPrimaryKeySelective(score1);
    }
}
