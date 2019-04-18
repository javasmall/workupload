package com.submit.controller;

import com.submit.dao.jobMapper;
import com.submit.dao.scoreMapper;
import com.submit.dao.teachclassMapper;
import com.submit.pojo.job;
import com.submit.pojo.score;
import com.submit.pojo.teachclass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;

@Controller
public class fileController {

    private static  final Logger  logger= LoggerFactory.getLogger(fileController.class);
    @Autowired(required = false)
    jobMapper jobMapper;
    @Autowired(required = false)
    teachclassMapper teachclassMapper;
    @Autowired(required = false)
    scoreMapper scoreMapper;
    @ResponseBody
    @PostMapping("onfile")
    public String onfile(MultipartFile file,int lessonid, int jobid, HttpServletRequest request) throws IOException {
        //lessonid :teachclass jsp,安卓等信息的 id
        //jobid: jdbc,登录实验名称的 id
        // String pat="fileget/"+lessonid+"/"+jobid+"/";
        HttpSession session=request.getSession();
        if(file==null||!file.getOriginalFilename().contains("doc")){return "请选择正确文件";}

        job job=jobMapper.selectByPrimaryKey(jobid);
        teachclass teachclass=teachclassMapper.selectByPrimaryKey(lessonid);
        score score=scoreMapper.uniqueindex(job.getId(),(String)session.getAttribute("studentid"));


        logger.info(job.getTitle()+" "+job.getNo()+" "+job.getDuedate());
        logger.info(teachclass.getCoursename()+" "+teachclass.getTeachclassno());

        String pat="fileget/"+lessonid+"/"+jobid+"/";
        String path=request.getSession().getServletContext().getRealPath(pat);//专门创建一个fileget文件夹存取内容
        File file2=new File(path);
        if(!file2.exists())//不存在就新建文件夹
        {
            file2.mkdirs();
        }
        logger.info(path);
        String filename=session.getAttribute("studentid")+(String)session.getAttribute("name")+"实验"+job.getNo()+"."+file.getOriginalFilename().split("\\.")[1];
        File file3=new File(file2,filename);//创建文件
        OutputStream out=new FileOutputStream(file3);
        BufferedOutputStream buf=new BufferedOutputStream(out);
        byte by[]=file.getBytes();
        buf.write(by);
        buf.close();
        out.close();

        if(score==null)
        {
            score score1=new score();
            score1.setJobid(job.getId());score1.setStudentno((String)session.getAttribute("studentid"));
            score1.setTime(new Date());
            scoreMapper.insert(score1);
        }
        else {
            score.setTime(new Date());
            scoreMapper.updateByPrimaryKeySelective(score);
        }

        return "上传成功";
    }
}
