package com.submit;

import com.submit.dao.studentMapper;
import com.submit.dao.teachclassMapper;
import com.submit.pojo.teachclass;
import com.submit.service.teacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentApplicationTests {

    Logger logger= LoggerFactory.getLogger(StudentApplicationTests.class);
    @Autowired(required = false)
    studentMapper studentMapper;
    @Autowired(required = false)
    private teachclassMapper teachclassMapepr;
    @Autowired(required = false)
    private teacherService teacherService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void  test3()
    {
        Map<String,Object>map=new HashMap<>();
        List<teachclass> list=teacherService.getteachcassbyteacherid("199800001483");
        for(teachclass l:list)
        {
            logger.info(l.getCoursename()+" ");
        }
    }
    @Test
    public void test2()

    {
//        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
//        int year=c.get(Calendar.YEAR);
//        int month=c.get(Calendar.MONTH);
//       // logger.info(year+" "+month);
//        List<Map> list=teachclassMapepr.getallclasslesson();
//        List<Map>list1=new ArrayList<>();
//        for(Map map:list)
//        {
//            String date=(String) map.get("coursesemester");
//            String dayear=date.substring(0,4);
//            String xueqi=date.substring(10,11);
//            if((Integer.parseInt(xueqi)==2&&month<8&&Integer.parseInt(dayear)==year-1)||(Integer.parseInt(xueqi)==2&&month>8&&Integer.parseInt(dayear)==year)){
//                list1.add(map);
//                logger.info(year+" "+dayear+" "+month);
//            }
//           // logger.info(dayear+" "+xueqi);
//        }
//        for(Map map:list1)
//        {
//            String date=(String) map.get("coursesemester");
//            String dayear=date.substring(0,4);
//            String xueqi=date.substring(10,11);
//            logger.info(dayear+" "+xueqi);
//        }

    }

}
