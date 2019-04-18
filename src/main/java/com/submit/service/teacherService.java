package com.submit.service;

import com.submit.dao.teacherMapper;
import com.submit.pojo.teachclass;
import com.submit.pojo.teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class teacherService {
    @Autowired(required = false)
    teacherMapper teacherMapper;
    public teacher getteacherbyid(String teacherid) {
        return teacherMapper.selectByPrimaryKey(teacherid);
    }


    public boolean updatepassword(teacher teacher) {
        return teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    public List<teachclass> getteachcassbyteacherid(String teacherid) {
        return  null;
    }
}
