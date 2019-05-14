package com.submit.controller;

import com.submit.pojo.job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("teacher")
public class teacherJobController {
    @Autowired(required = false)
    com.submit.service.teacherService teacherService;

    @ResponseBody
    @GetMapping("getjobbyteachclaid")
    public List<job> getjobbyteachclaid(String teacherclaid, HttpServletRequest request)
    {
        try {
            return teacherService.getjobbyteachclaid(Integer.parseInt(teacherclaid));
        }catch (Exception e)
        {e.printStackTrace();return null;}
    }
    @ResponseBody
    @GetMapping("getjobbyteachclaid2")
    public Map<String,Object> getjobbyteachclaid2(String teacherclaid, HttpServletRequest request)//给表格用
    {
        try {
            List<job>list= teacherService.getjobbyteachclaid(Integer.parseInt(teacherclaid));
            Map<String,Object>map=new ConcurrentHashMap<>();
            map.put("code","0");
            map.put("count",list.size());
            map.put("data",list);
            return map;
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
    @PostMapping("updatejobbyteachclaid")
    public String updatejobbyteachclaid(String ID,String no,String title,String duedate,String type,String note)
    {
        try {
            job job=teacherService.getjobbyid(ID);
            if(no!=null&&!"".equals(no)){job.setNo(Integer.parseInt(no));}
            if(title!=null&&!"".equals(title)){job.setTitle(title);}
            if(duedate!=null&&!"".equals(duedate)){job.setDuedate(duedate);}
            if(type!=null&&!"".equals(type)){job.setType(Integer.parseInt(type));}
            if(note!=null&&!"".equals(note)){job.setNote(note);}
            teacherService.updatejob(job);
            return "更新成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "更新失败";
        }
    }

    @ResponseBody
    @PostMapping("deletejobbyid")
    public  String deletejobbyid(int id)
    {
        try {
            teacherService.deletejobbyid(id);
            return "删除成功";
        }catch (Exception e)
        {
            e.printStackTrace();
            return "更改失败";
        }
    }

}
