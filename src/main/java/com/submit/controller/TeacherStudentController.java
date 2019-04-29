package com.submit.controller;

import com.submit.pojo.*;
import com.submit.service.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("teacher")
public class TeacherStudentController {

    @Autowired(required = false)
    teacherService teacherService;
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

    @PostMapping("updatestuclaid")//更新花名册的编号
    public String updatestuclaid(String stuclaid,String num)
    {
        try {
            teacherService.updatestuclano(Integer.parseInt(stuclaid), Integer.parseInt(num));
            return "更新成功";
        }
        catch (Exception e)
        {
            e.printStackTrace();return "更新失败";
        }
    }


    @GetMapping("getscorebyjobid")
    public Map getscorebyjobid(int jobid)
    {
       List<Map>list= teacherService.getscorebyjobid(jobid);
       Map<String,Object> map=new ConcurrentHashMap<>();
       map.put("code","0");
       map.put("count",list.size());
       map.put("data",list);
       return map;
    }

    @PostMapping("updatescorebyscoreid")
    public String updatescorebyscoreid(String scoreid,String score,String note)
    {
        try {
            teacherService.updatescorebyscoreid(scoreid,score,note);
            return "修改成功";
        }
        catch (Exception e)
        {e.printStackTrace();return "修改失败";}
    }


    @GetMapping("addstudentuser")
    public String addstudentuser(String studentno,String name,String pinyin,String password)
    {
        try {
            student student = new student();
            student.setStudentno(studentno);
            student.setName(name);
            student.setPinyin(pinyin);
            student.setPassword(password);
            teacherService.addstudentuser(student);
            return "插入成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "插入失败";
        }
    }
//    @GetMapping("lessonaddstudent")
//    public String lessonaddstudent(String teachclaid,String studentno,String no,String note)
//    {
//        try {
//            studentclass studentclass = new studentclass();
//            studentclass.setClassid(Integer.parseInt(teachclaid));
//            studentclass.setStudentno(studentno);
//            if(no!=null&&!"".equals(no))studentclass.setNo(Integer.parseInt(no));
//            if(note!=null&&!"".equals(note))studentclass.setNote(note);
//            teacherService.lessonaddstudent(studentclass);
//            return "插入成功";
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            return "添加失败";
//        }
//    }

    @PostMapping("deletestuclassbytwoid")
    public String deletestuclassbytwoid(int teachclassid, String studentno)
    {
        try {
             teacherService.deletestuclassbytwoid(teachclassid,studentno);
            return "删除成功";
        }catch (Exception e)
        {e.printStackTrace();return "删除失败";}
    }
    @PostMapping("deletestuclassbyid")
    public String deletestuclassbyid(int studentclassid)
    {
        try {
           if(teacherService.deletestuclassbyid(studentclassid))
            return "删除成功";
           else return "删除失败";
        }catch (Exception e)
        {e.printStackTrace();return "删除失败";}
    }

//    @ResponseBody
//    @GetMapping("showworkupload")
//    public String showworkupload()
//    {
//        return null;
//    }



    @ResponseBody
    @GetMapping("getstudentattendlesson")
    public Map<String,Object>getstudentattendlesson(String classid)
    {
      List<Map>list= teacherService.getstudentbyclaid(classid);
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("code","0");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }

    @ResponseBody
    @PostMapping("updatestudentclass")
    public String updatestudentclass(String studentclassid,String no,String note)
    {
        try {
            studentclass studentclass = teacherService.getstudentclassbyid(studentclassid);
            if (no != null && !"".equals(no)) {
                studentclass.setNo(Integer.parseInt(no));
            }
            if (note != null) {
                studentclass.setNote(note);
            }
            if(teacherService.updatestudentclass(studentclass))return "更新成功";
            else return "更新失败";

        }catch (Exception e)
        {
            e.printStackTrace();return "更新失败";
        }
    }

    @ResponseBody
    @PostMapping("lessonaddstudent")
    public String lessonaddstudent(String lesson,String startid,String endid,String startno,String studentid,String studentno,String type)
    {
        if(type.equals("one"))
        {
            try {
                return teacherService.lessonaddstudent(lesson, studentid, studentno);
            }catch (Exception e){
                e.printStackTrace();return "插入失败";
            }
        }
        else if(type.equals("more"))
        {
            try {
                return teacherService.lessonaddstudentmore(lesson, startid, endid, startno);
            }catch (Exception e){e.printStackTrace();return "异常错误";}
        }
        return "参数错误";
    }


}
