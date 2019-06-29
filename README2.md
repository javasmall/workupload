[项目github地址](https://github.com/javasmall/workupload)

[项目码云地址，欢迎star！💗](https://gitee.com/bigsai/workupload)

微信公众号（持续分享）：bigsai

<img src="https://img-blog.csdnimg.cn/20190402171442251.jpg" width="50%" height="50%">
[作业收缴系统设计手册csdn地址](https://blog.csdn.net/qq_40693171/article/details/89891978)
@[TOC](目录)
# 系统介绍：
**系统概述**：写了个作业收缴系统。系统旨在优化作业上交流程，解决收缴作业的繁琐过程，传统收缴作业基于qq文件，或者邮箱收发。需要大量的人工操作和精力取维护。而本系统将作业系统部署到服务器，大大方便了教师/收作业者对作业的管理。系统简单易用。能够满足大部分需求。项目已开源，可以自己使用或者二次开发等等。欢迎star！！详细的设计文案将在最近给出。

# 基本功能
教师端登录地址：http://localhost:8080/loginteacher.html (数据库teacher表)
学生端登录地址：http://localhost:8080/login.html  （数据库student表）
数据库监控地址：http://localhost:8080/druid/login.html  （数据库账号密码）
教师端和druid一览
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190506185412566.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190506185453804.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)

# 管理端
 ## 信息管理
 这块主要
 #### 1. 修改密码
 可以直接输入旧密码和新密码进行修改
 #### 2. 学生添加（支持excel格式的批量导入）
 该项功能是往系统平台中添加学生。你可根据表单手打学生信息进行导入。有了学生信息后学生才能加入课程班号。
 
若使用excel的xls或xlsx格式进行导入，要遵从文件的相应格式，从第二行起，表格的前四列应遵从如下格式：

| 学号 | 姓名      |拼音|密码|
|:--------:|:-------------| ---------|--|
| 16221070 | 张赛 |zhang1sai4|162125|
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190505170901530.png)
当既有excel文件又有表单数据时候，优先考虑excel数据。忽略表单添加。
#### 3.学生管理：
此页面下可以对学生进行模糊搜索，搜索指定学生，指定学号范围、姓名的学生，针对表格的学生数据，可以==直接进行点击编辑==。学生除了学号外其他信息均可修改。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190505203917827.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
此外，页面还提供导出学生excel表的功能。将选定的可以导出成xls或者cvs文件。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190505204325582.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)


####  4. 教师的管理（增删）
这个模块只提供教师的增删功能。不提供修改功能。但是只有超级用户才能对教师账号的增删（程序拥有者数据库的level为0）；

## 课程管理
   #### 1.  我的课程
   本页面提供查看、编辑和删除自己所有课程的功能，可以根据需求修改自己发布课程的相关信息。也可以看到加入课程的学生。
   ####  2. 课程添加
   本页面也添加课程的界面。注意课时学分一栏的数据为数字类型。通过此页面即可将新课程发布到系统你的课程列表中。==注意填写学期的格式==，这个后台会根据此字段匹配是否为当前学期，否的话将在一些界面不展示。如果写错可到我的课程中进行修改！
   #### 3. 添加学生（课程）
   本界面是课程添加学生操作的界面。因为学生和课程是两个独立题。**本系统通过老师添加学生使得学生参加课程而不是学生自己选课**。添加学生分为单个添加和批量添加。因为一个班级学生往往学号是有规律的递增，所有我们提供通过学号首尾，添加数据库中有该字段学号的学生进入课程。添加后将返回成功失败的条数。添加之后，对应学生会在提交作业的可选列表多出改作业。
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190505211109201.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
   
  ## 作业管理
   #### 1. 作业管理（增删改）
   每一个课程下有若干实验或作业。学生提交的要有课程和作业两个选项。本页面提供编辑自己课程的作业（添加、修改，删除等功能）。注意的是你可编辑在当前学期的课程作业（防止使用太久课程太多障碍选项太多影响使用故屏蔽掉非本学期的课程）。
   #### 2. 作业发布
   此界面提供发布作业的功能，注意一些数字类型的栏目。
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190505211951235.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
   #### 3. 作业查看（打印，下载压缩文件等）
   本页面为核心功能。教师等收作业可通过本界面下资学生已经上传的实验的报告打包城的zip文件。还可以在线根据用户的实验进行在线评分和备注。最终可以保存成Excel文件到本地。
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/2019050521332517.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)

# 学生端
因为学生不是主要服务对象，所有就给了几个需要的界面，学生可以根据已经添加的课程进行上传作业。主界面如下：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190505214227986.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)