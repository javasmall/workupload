package com.submit.controller;

import com.submit.pojo.student;
import com.submit.pojo.studentclass;
import com.submit.pojo.teacher;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("teacher")
public class teacherUserController {

    private final static Logger logger= LoggerFactory.getLogger(teacherUserController.class);
    @Autowired(required = false)
    com.submit.service.teacherService teacherService;
    @Autowired(required = false)
    com.submit.service.studentService studentService;
    @Autowired(required = false)
    com.submit.dao.studentMapper studentMapper;

    @GetMapping("getallstudent")
    public Map getallstudent(String studentid,String name)
    {
        if(studentid==null)studentid="";
        if(name==null)name="";
        List<student>list=studentService.getallstudent(studentid,name);
        Map<String,Object>map=new HashMap<>();
        map.put("code","0");
        map.put("count",list.size());
        map.put("data",list);
        return map;
    }
    @PostMapping("updatestudent")
    public String updatestudent(String studentid,String name,String pinyin,String password)
    {
        try {
            student student = studentMapper.selectByPrimaryKey(studentid);
            if (student == null) return "不存在学生";
            student.setName(name);
            student.setPinyin(pinyin);
            student.setPassword(password);
            studentMapper.updateByPrimaryKeySelective(student);
           return "更新成功";
        }
        catch (Exception e)
        {
            logger.error(e.toString());return "更新失败";
        }
    }

    @PostMapping("deletestudent")
    public String deletestudent(String studentid)
    {
        try {
            studentMapper.deleteByPrimaryKey(studentid);
            return "删除成功";
        }catch (Exception e)
        {
            return "删除失败";
        }
    }
    @GetMapping("getallteacher")
    public Map<String,Object>getallteacher()
    {
        Subject subject= SecurityUtils.getSubject();
       List<teacher>list= teacherService.getAllTeacher();
       List<teacher>list1=new ArrayList<>();
       for(teacher teacher2:list)
       {
          if(!((String)subject.getSession().getAttribute("teacherid")).equals(teacher2.getTeacherno()))
          {logger.info(teacher2.getTeacherno());
          list1.add(teacher2);}
       }
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("code","0");
        map.put("count",list1.size());
        map.put("data",list1);
       return map;

    }
    @PostMapping("addteacher")
    public String addteacher(String teacherno,String name,String password,HttpServletRequest request)
    {
        teacher teacher1=teacherService.getteacherbyid((String)request.getSession().getAttribute("teacherid") );
        if(teacher1.getLevel()==1){return "权限不足";}
        try {
            teacher teacher = new teacher();
            teacher.setTeacherno(teacherno);
            teacher.setName(name);
            teacher.setPassword(password);
            teacher.setLevel((byte) 1);
            teacherService.addteacher(teacher);
            return "插入成功";
        }catch (Exception e)
        {e.printStackTrace();return "插入失败";}

    }
    @PostMapping("deleteteacher")
    public String deleteteacher(String teacherno,HttpServletRequest request)
    {
        teacher teacher=teacherService.getteacherbyid((String)request.getSession().getAttribute("teacherid") );
        if(teacher.getLevel()==1){return "权限不足";}
        else{
            try {
                teacherService.deleteteacherbyid(teacherno);
                return "删除成功";
            }
            catch (Exception e)
            {
                logger.error(e.toString());
                return "删除失败";
            }
        }
    }
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
