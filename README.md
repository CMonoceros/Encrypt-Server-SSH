Encrypt 第二版（进行中）
====

软件目的
------

利用服务器运算速度，进行远程文件加密，下载传输过程安全且下载后可以用于安全传输。<br />

总体功能
------

用户能够注册，并且登录。登录成功后，可以上传文件，获取文件列表。选择具体文件后，可以获取所支持的加密方法及加密介绍，并进行服务器加密，下载到本地。下载成功后可以进行本地解密。<br />

概念模型
------

![image](https://github.com/CMonoceros/Encrypt-Client-MVP/raw/master/screenshot/usecase.jpg)

系统设计
------

将整体分为，用户模块，文件模块，加密方式模块，加密关系模块，每个模块所对应的基本功能如下：<br />
1.用户模块：登录，注册<br />
2.文件模块：上传，下载，获取文件列表<br />
3.加密方式模块：获取加密方式<br />
4.加密关系模块：加密，解密<br />

服务器为MVC模式.<br />
Model中应包含操作实体对象类及数据库操作抽象方法基类及具体方法实现类。<br />
Controller中应包含具体模块数据库操作方法接口定义，具体模块数据库操作方法实现类，获取数据库操作接口的控制器基类，具体模块控制器继承类，Cookie过滤器。<br />
View为Android客户端。<br />

系统实现
------

针对用户模块：<br />
Model中包含BaseDaoSupport，数据库操作方法基类。BaseDaoSupportImpl，实现BaseDaoSupport，通过Resource注解获取SessionFactory实例后，通过Transactional注解开事务管理的数据库操作实现类。UserEntity，包含全部关系的Hibernate逆向工程类。UserBaseEntity，不包含外键关系，用于Json传输及解析的Hibernate逆向工程类。<br />
Controller中包含BaseUserService，继承BaseDaoSupport的User相关操作数据库方法的接口定义。UserServiceImpl，实现BaseUserService，继承BaseDaoSupportImpl，通过Service注解注入及Transactional注解开事务管理的可操作数据库的User相关操作数据库方法实现类。BaseAction，通过Resource注解获取Service接口实例的控制器抽象基类。UserAction，继承BaseAction，通过Controller注解管理，包含具体操作方法对实例进行操作的实际控制器类。CookieFilter，负责管理Cookie，过滤URL请求的过滤器。<br />
View为Android客户端。

![image](https://github.com/CMonoceros/Encrypt-Client-MVP/raw/master/screenshot/server_mvc.jpg)

其它
------
其他内容，包括相关方法接口，见doc文件夹下开发报告
