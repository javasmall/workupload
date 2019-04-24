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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    @PostMapping(value = "/download")//teachclassid jobid
    public String download(int lesson, int job,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pat="fileget/"+lesson+"/"+job;
        String zipname="";
        teachclass teachclass=teachclassMapper.selectByPrimaryKey(lesson);
        job job1=jobMapper.selectByPrimaryKey(job);
        zipname+=teachclass.getCoursename();
        zipname+="实验"+job1.getNo()+job1.getTitle();
        zipname+=".zip";
        String filename=request.getSession().getServletContext().getRealPath(pat);//专门创建一个fileget文件夹存取内容
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session=request.getSession();
        if(session.getAttribute("teacherid")==null)return null;
        response.setContentType("text/html");

        //设置文件MIME类型
        response.setContentType(session.getServletContext().getMimeType(zipname));
        //设置Content-Disposition
        response.setHeader("Content-Disposition", "attachment;filename="+new String(zipname.getBytes("utf-8"),"ISO8859_1"));

        File file=new File(filename);
        if(!file.exists()){file.mkdirs();}

        OutputStream out = response.getOutputStream();
        ZipOutputStream zipout=new ZipOutputStream(out);

        dozip(zipout,file,"");
        zipout.close();
        out.close();
        return null;
    }

    private static void dozip(ZipOutputStream zipout, File file, String addpath) throws IOException {
        if(file.isDirectory())
        {
            File f[]=file.listFiles();
            for(int i=0;i<f.length;i++)
            {
                if(f[i].isDirectory()) {
                    dozip(zipout, f[i], addpath+f[i].getName()+"/");
                }
                else {
                    dozip(zipout, f[i], addpath+f[i].getName());
                }
            }
        }
        else
        {
            InputStream input;
            BufferedInputStream buff;
            zipout.putNextEntry(new ZipEntry(addpath+file.getName()));
            input=new FileInputStream(file);
            buff=new BufferedInputStream(input);
            byte b[]=new byte[1024*5];
            int a=0;
            while((a=buff.read(b))!=-1)
            {
                zipout.write(b);
            }
            buff.close();
            input.close();
            System.out.println(file.getName());
        }

    }


}
