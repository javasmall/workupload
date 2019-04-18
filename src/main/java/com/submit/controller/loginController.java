package com.submit.controller;

import com.submit.dao.studentMapper;
import com.submit.pojo.student;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class loginController {

    @Autowired(required = false)
    studentMapper studentMapepr;
    @ResponseBody
    @GetMapping("test")
    public List<student> test()
    {
        return studentMapepr.getall();
    }
    @PostMapping("studentlogin")
    public String studentlogin(String username, String password, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("role","student");
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转到test.html
            request.getSession().setAttribute("studentid",username);
            return "redirect:student";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            return "redirect:login.html";
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            return "redirect:login.html";
        } catch (Exception e)
        {
            e.printStackTrace();
            return "redirect:login.html";
        }
    }

    @PostMapping("teacherlogin")
    public String teacherlogin(String username, String password, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("role","teacher");
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转到test.html
            request.getSession().setAttribute("teacherid",username);
            return "redirect:teacher";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登录失败:用户名不存在
            return "redirect:loginteacher.html";
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登录失败:密码错误
            return "redirect:loginteacher.html";
        } catch (Exception e)
        {
            e.printStackTrace();
            return "redirect:loginteacher.html";
        }
    }
}
