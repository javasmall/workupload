[项目github地址，欢迎star！💗](https://github.com/javasmall/workupload)

[作业收缴系统使用手册csdn地址](https://blog.csdn.net/qq_40693171/article/details/89850715)


# 项目介绍
系统旨在优化作业上交流程，解决收缴作业的繁琐过程，传统收缴作业基于qq文件，或者邮箱收发。需要大量的人工操作和精力取维护。而本系统将作业系统部署到服务器，大大方便了教师/收作业者对作业的管理。系统精美简单易用。能够满足大部分需求。项目已开源，可以自己使用或者二次开发等等。**欢迎star**😭！！
开发工具：IDEA+postman+nivicat
主要框架：Springboot+Mybatis+Shiro+Druid
其他框架/工具：devtools,Easyexcel(poi)，Mybatis-generator，
运行环境：Tomcat8.5以上，Mysql5或8

# 数据库设计
这个数据库是老师给我让我完成的，7张表只用了6张，老师给了一些关键性的外键，其实还有一些外键参考，但鉴于系统并不是完全完善并且对逻辑影响不是很大，所以我就没加上那些外键。如果有需要可自行添加。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190506200730347.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
对于数据库解读是最重要的一步。对于项目并没有太多复杂的逻辑需求。所以直接从数据库开始。
### teacher
- 此表包含教师信息的基本字段，包过工号，姓名，密码，level是权限用的，管理员教师可以操作其他教师，level为0权限为管理员，其他为普通教师。
### student
- 此表包含学生的基本信息
### teachclass
- 此表为课程表，一个老师不同学期可能带几个班级，这个课程就要有学期，名称，学分，课程类型，对应教师等信息。
### job
- 这个可以理解为具体实验表(作业)，没门课程老师可能发布不同的作业，就要有对应的介绍。
### studentclass
- 这是学生和课程联系的中介。一个学生可以在不同课程中上课，课程id（teachclass表的ID和学号为唯一索引）
### score
- 这就是提供教师打分记录表，学生提交后教师可对学生打分存入数据库。
# 项目目录
### 前端
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507112948562.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
前端文件一览：
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190506213036639.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
前端采用后台管理[**layui**](https://www.layui.com/)，并没有用别人写好的layui模板进行嵌套修改，而是从0开始从layui官方开始参考文档一点点用组件。

前端为主界面+iframe小界面，中间的内容框为ifame界面显示内容。

**浅谈layui：**
- 以前就接触过layui，以前和队友配合队友写前端自己队友用的就是前端。还有以前帮姐姐写的小东西也是用的layui，不过那次用的layui不是真的layui。。那只是用到layui漂亮的外表。清晰记得。套过来layui的壳子，然后能用thymleaf交互的地方就不用ajax。。遇到ajax的地方（比如表格）等等就疯狂Jquery拼凑html，虽然外观还行，但是可维护性很差，自己都不清楚自己写那去了。

- 造成上述的原因主要是因为自己太过墨守成规，以为ui框架只是提供ui，**而事实上一个优秀的框架往往比你想象的还要优秀的多**。不仅是美丽的外观，还有强大的功能和便捷的使用。这就要耐心的阅读文档，不要被文档吓到。

- 在本系统中用到layui多个组件。如表单，表格，时间日期，文件上传，表格等等，layui虽然不是双向绑定模式，但是layui对于控件fitter的绑定和监听做的特别好,虽然大部分方便了使用但是会使得部分传统方法出现失效的问题需要自己解决。layui大部分都是基于ajax的异步传输。在系统初用的是thymleaf,后来发现在layui的领域thymleaf并不能展现过大的便捷性，后面的就都用html了。其次就是layui的一些东西可能对后端新手（比如我）有一些新颖。layui表单等等封装了很便捷的异步提交方式。你大部分的传输方式要按照他的规则来，但是也有一些时候他可能满足不了你的需求你需要解决。对于layui更多功能，详细参考[layui官当demo](https://www.layui.com/demo/)
### 后端
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190506214822533.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
后端就是mvc的设计架构：
- config:
 shiro的配置和自定义releam配置在文件中，还有就是druid监控的一些内容也放在里面。
- controller：
controller层分了几个controller，比如文件处理一个，登录授权一个，thymleaf控制一个，还有studentcontroller处理一些学生端的事务，因为教师端的内容比较多，根据处理不同的信息分了处理学生，处理课程，处理实验等controller。
- service：
当时为了方便只写了两个service,学生和教师，所以service内容比较多，你可以从controller的内容找入service
- dao：
为mybatis逆向工程生成基本文件和自己添加一些接口
- pojo
 数据库映射对象，其中student继承满足poi导入excel的类。
 - log
  logback日志配置，放到服务器要修改日志文件地址(**用绝对路径否则日志文件将不存在**)

### 模板引擎和ajax
项目采用thymleaf+html的样式，因为个人开发对于一些参数用thymleaf还是会方便很多。但是thymleaf对于数据绑定对动态数据不太好处理，所以不涉及静态数据绑定的界面一般都是html。
# 功能设计
### 学生端
- **文件上传**
 客户端文件上传
  - layui的form表单默认是同步上传，而同步上传需要跳转界面并不是我想要的结果，所以本系统用异步上传文件来完成。所用的是ajax的formdate进行文件上传。具体代码可参考templates/student/upload.html这个thymleaf文件。
  ![在这里插入图片描述](https://img-blog.csdnimg.cn/2019050708180361.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
 
  服务端文件接受：
  -  服务端以前的文件接收用的是[servlet3.0](https://blog.csdn.net/qq_40693171/article/details/80437387)，但是Springmvc的[MultipartFile接受文件](https://blog.csdn.net/qq_40693171/article/details/88429760)更加便捷，所以采用对于上传的路径。为tomcat项目相对路径fileget/"+lessonid+"/"+jobid+"/"+文件名;这里文件名设置为学号+姓名+实验名+实验几。具体可参考fileController.java代码内容。
  - 还有就是本来是写了作业补交的功能的，但是出于需求考虑补交部分被注释掉。所以截至日期暂时没有明显作用。只是上传作业的时候会提示。
  
### 教师端
- 文件打包成zip：
 要将服务器所有已经上传的[文件打包成zip文件](https://blog.csdn.net/qq_40693171/article/details/89406706)，要注意打包成zip文件的时候不能在服务器保存—不要占用额外的空间，所有就要用io临时生成zip文件传输到客户端，这就要很好的处理下io流的内容，还要考虑[文件下载](https://blog.csdn.net/qq_40693171/article/details/80460402)的内容。谨防异常和文件名乱码等。具体可参考fileController下代码。
 - 接受excel并解析：
  java解析excel的工具并不多，当前较为流行的apache下的poi。我用的是阿里的easyexcel—基于poi封装改进的框架。但是基于poi如果解析excel生成实体类需要对pojo对象继承BaseRowModel类并在字段上给出对应注解：![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507085921120.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
  - layui表格
   layui表格按照固定格式ajax渲染而来的，项目中多次用到layui表格。提供强大的在线编辑，排序，导出excel/cvs等功能。你只需耐心阅读layui表格相关部分文档就可以了解其中流程。
   ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507090329763.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
   - 其他
    项目中其他部分无非就是表单提交。后台增删改查。模糊查询。前端jq处理逻辑等等。
# 权限设计
权限设计基于Shiro进行，
### 登录验证
- 其实这里我当时纠结的挺久就是shiro的releam一般是针对一个user表中的数据进行验证，但是项目中的用户来源自学生表和教师表。因为shiro的session和request的session其实是一个session，所以你可以很灵活的完成一些内容。学生端，教师端并不是一个统一的登录入口，所以在两个登录的端口分别用一个session防一个role身份。在releam中用shiro的session判断角色，if else判断角色写方法解决。
### 授权管理
- 登录验证可以解决非系统用户访问系统的问题，但是不进行权限处理会造成用户抓到接口可能会进行越权操作。对系统稳定和安全造成威胁。一定需要授权。因为我的接口（教师端）都加了前缀teacher/xxx,更适合url统一管理，对于url统一管理，我采用的是针对身份的管理而不是资源的细化管理。因为主要还是教师和学生两类用户。用role可以满足需求。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507091917595.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
# 项目安装
项目环境为tomcat8.5以上，mysql5或8.
1. 首先复制db目录receve的内容放到nivicat等数据库建库建表（数据已经进行阉割）
2. 如有需要，修改application.properties文件的数据库账号密码
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019050709233280.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
3. 修改logback.xml的日志路径（如果需要）
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507092534892.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
 4.如果本地可直接运行，如果打包部署，需要先clean，然后修改maven配置
 将两个被注释的还原![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507092807813.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190507111114688.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwNjkzMTcx,size_16,color_FFFFFF,t_70)
 然后再target目录下找到war包，修改成你要的项目名，用ssh、scp等工具指令放到tomcat指定目录下。就可以访问了。
 教师端登录地址：http://localhost:8080/loginteacher.html (数据库teacher表)
学生端登录地址：http://localhost:8080/login.html （数据库student表）
数据库监控地址：http://localhost:8080/druid/login.html （数据库账号密码）
