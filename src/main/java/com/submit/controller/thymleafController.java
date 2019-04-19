package com.submit.controller;

import com.submit.dao.studentMapper;
import com.submit.pojo.student;
import com.submit.pojo.teachclass;
import com.submit.pojo.teacher;
import com.submit.service.studentService;
import com.submit.service.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class thymleafController {
    @Autowired(required = false)
    studentMapper studentMapper;
    @Autowired(required = false)
    studentService studentService;
    @Autowired(required = false)
    teacherService teacherService;

    @GetMapping("student")
    public String index(Model model, HttpServletRequest request)
    {
     model.addAttribute("studentid",request.getSession().getAttribute("studentid"));
     return "student/student";
    }
    @GetMapping("changepass")
    public String changepassword(Model model,HttpServletRequest request){
        student student=studentMapper.selectByPrimaryKey((String) request.getSession().getAttribute("studentid"));
        model.addAttribute("msg","");
        model.addAttribute(student);
        return "student/changepass";
    }

    @GetMapping("upload")
    public String upload(Model model,HttpServletRequest request){
        List<teachclass>list=studentService.getList((String) request.getSession().getAttribute("studentid"));
        model.addAttribute("list",list);
        return "student/upload";
    }

    @GetMapping("uploadjilu")
    public  String uploadjilu(){return "student/addteach";}

    @GetMapping("addteach")
    public String addteach(Model model,HttpServletRequest request)
    {

        List<Map>list=studentService.getstudentattendlesson((String)request.getSession().getAttribute("studentid"));
        List<Map>list1=studentService.getallclasslesson();
        model.addAttribute("list1",list1);
        model.addAttribute("list",list);
        return "student/addteach";
    }
    @GetMapping("mylesson")
    public String mylesson(Model model,HttpServletRequest request)
    {
        List<teachclass>list=teacherService.getteachcassbyteacherid((String)request.getSession().getAttribute("teacherid"));

        return null;
    }
    @GetMapping("teacher")
    public String teacher(Model model,HttpServletRequest request)
    {
        teacher teacher=teacherService.getteacherbyid((String)request.getSession().getAttribute("teacherid"));
        model.addAttribute("techerid",teacher.getTeacherno());
        model.addAttribute("techername",teacher.getName());
        return "teacher/teacher";
    }
    @GetMapping("changpassteacher")
    public String changepassteacher(Model model,HttpServletRequest request)
    {
        teacher teacher=teacherService.getteacherbyid((String)request.getSession().getAttribute("teacherid"));
        model.addAttribute("techerid",teacher.getTeacherno());
        model.addAttribute("techername",teacher.getName());
        model.addAttribute("msg","");
        return "teacher/changepassteacher";
    }

    @GetMapping("teacherlesson")
    public String teacherlesson(Model model,HttpServletRequest request)
    {
       List<teachclass>list= teacherService.getteachcassbyteacheridall((String) request.getSession().getAttribute("teacherid"));
       model.addAttribute("list",list);
        return "teacher/teacherlesson";
    }
    @GetMapping("editeachclass")
    public String editeachclass(String classid,HttpServletRequest request,Model model)
    {
        teachclass teachclass=teacherService.getteachclassbyclassid(classid);
        model.addAttribute("lesson",teachclass);
        List<student>list=teacherService.gestudentbyclassid(Integer.parseInt(classid));
        model.addAttribute("list",list);
        return "teacher/editeachclass";
    }
}
