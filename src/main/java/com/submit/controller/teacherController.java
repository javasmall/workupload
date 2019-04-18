package com.submit.controller;

import com.submit.pojo.teacher;
import com.submit.service.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("teacher")
public class teacherController {

    @Autowired(required = false)
    teacherService teacherService;
    @ResponseBody
    @PostMapping("updatepasswordtecher")
    public String updatepasswordtecher(String oldpassword, String newpassword,  HttpServletRequest request)
    {
        teacher teacher= teacherService.getteacherbyid((String)request.getSession().getAttribute("teacherid"));

        if(oldpassword==null||newpassword==null){ return "请正确输入账号和密码"; }
        else if(!oldpassword.equals(teacher.getPassword()))
        {
            return "密码错误";
        }
        else
        {
            teacher.setPassword(newpassword);
          if( teacherService.updatepassword(teacher))
              return "更改成功";
          else return "更改失败";
        }
    }
}
