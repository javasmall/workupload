package com.submit.controller;

import com.submit.pojo.*;
import com.submit.service.teacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    @ResponseBody
    @PostMapping("updatetechclass")
    public String updateteachclass(String teachclassno,String coursename,String coursesemester
    ,String credit,String id,String evalmethod)
    {
        try {
            teachclass teachclass = teacherService.getteacherclassbyid(id);
            teachclass.setCoursename(coursename);
            teachclass.setTeachclassno(teachclassno);
            teachclass.setCoursesemester(coursesemester);
            teachclass.setCredit(Byte.parseByte(credit));
            teachclass.setEvalmethod(evalmethod);
            teacherService.updateteachclass(teachclass);
            return "更新成功";
        }
        catch (Exception e)
        {e.printStackTrace();return "更新失败";}
    }
    @ResponseBody
    @PostMapping("addtechclass")
    public String addteachclass(String teachclassno,String coursename,String coursesemester
            ,String credit,String evalmethod,HttpServletRequest request)
    {
        try {
            teachclass teachclass = new teachclass();
            teachclass.setCoursename(coursename);
            teachclass.setTeachclassno(teachclassno);
            teachclass.setCoursesemester(coursesemester);
            teachclass.setCredit(Byte.parseByte(credit));
            teachclass.setEvalmethod(evalmethod);
            teachclass.setTeacherno((String)request.getSession().getAttribute("teacherid"));
            teacherService.addeachclass(teachclass);
            return "添加成功";
        }
        catch (Exception e)
        {e.printStackTrace();return "添加失败";}
    }

    @ResponseBody
    @PostMapping("deleteteachclassbyid")
    public String deleteteachclassbyid(String id)
    {
        try {
            teacherService.deleteteachclassbyid(Integer.parseInt(id));
            return "删除成功";
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "删除失败";
        }
    }
    @ResponseBody
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


    @ResponseBody
    @GetMapping("getteacherclassthisterm")
    public List<teachclass> getteacherclassthisterm(HttpServletRequest request)
    {
        try {
            String teacherid = (String) request.getSession().getAttribute("teacherid");
            List<teachclass> list = teacherService.getteacherclassthisterm(teacherid);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @ResponseBody
    @GetMapping("getteacherclass")
    public List<teachclass> getteacherclass(HttpServletRequest request)
    {
        try {
            String teacherid = (String) request.getSession().getAttribute("teacherid");
            List<teachclass> list = teacherService.getteachcassbyteacheridall(teacherid);
            return list;
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    @ResponseBody
    @GetMapping("getjobbyteachclaid")
    public List<job>getjobbyteachclaid(String teacherclaid,HttpServletRequest request)
    {
        try {
            return teacherService.getjobbyteachclaid(Integer.parseInt(teacherclaid));
        }catch (Exception e)
        {e.printStackTrace();return null;}
    }
    @ResponseBody
    @PostMapping("addjob")
    public String addjob(String lesson,String no,String title,String duedate,int type,String note)
    {
        try {
            job job=new job();
            job.setTeachclassid(Integer.parseInt(lesson));
            job.setNo(Integer.parseInt(no));
            job.setTitle(title);
            job.setDuedate(duedate);
            job.setType(type);
            job.setNote(note);
            job.setCreatetime(new Date());
            teacherService.addjob(job);
            return "插入成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败";
        }
    }

    @ResponseBody
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

    @ResponseBody
    @GetMapping("updatejobbyteachclaid")
    public String updatejobbyteachclaid(String ID,String no,String title,String duedate,String type)
    {
        try {
            job job=teacherService.getjobbyid(ID);
            if(no!=null&&!"".equals(no)){job.setNo(Integer.parseInt(no));}
            if(title!=null&&!"".equals(title)){job.setTitle(title);}
            if(duedate!=null&&!"".equals(duedate)){job.setDuedate(duedate);}
            if(type!=null&&!"".equals(type)){job.setType(Integer.parseInt(type));}
            teacherService.updatejob(job);
            return "更新成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "更新失败";
        }
    }
    @ResponseBody
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
    @ResponseBody
    @GetMapping("lessonaddstudent")
    public String lessonaddstudent(String teachclaid,String studentno,String no,String note)
    {
        try {
            studentclass studentclass = new studentclass();
            studentclass.setClassid(Integer.parseInt(teachclaid));
            studentclass.setStudentno(studentno);
            if(no!=null&&!"".equals(no))studentclass.setNo(Integer.parseInt(no));
            if(note!=null&&!"".equals(note))studentclass.setNote(note);
            teacherService.lessonaddstudent(studentclass);
            return "插入成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败";
        }
    }

    @ResponseBody
    @PostMapping("deletestuclassbytwoid")
    public String deletestuclassbytwoid(int teachclassid, String studentno)
    {
        try {
             teacherService.deletestuclassbytwoid(teachclassid,studentno);
            return "删除成功";
        }catch (Exception e)
        {e.printStackTrace();return "删除失败";}
    }
    @ResponseBody
    @GetMapping("showworkupload")
    public String showworkupload()
    {
        return null;
    }



}
