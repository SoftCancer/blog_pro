# blog_pro
SpringCloud 学习实现 博客项目

## blog_pro 项目组成：

####  1. blog_common ：公共部分

```$xslt
主要实现工具类的封装，统一返回数据的封装，全局异常的处理
```
####  2. blog_base ：标签部分
```$xslt
主要 通过Jpa实现 标签的增删查改及分页功能
```
####  3. blog_recruit ：职位推荐部分
```$xslt
主要通过JPA的属性名查询数据
```
####  4. blog_qa ：问答模块部分
```$xslt
主要通过主键SQL语句的方式实现数据的查询
```
####  5. blog_article ：公共部分
```$xslt
整合并操作 Redis，通过注解SQL语句的方式操作数据
```
####  6. blog_gathering ：活动部分
```$xslt
整合SpringCache 缓存，
```
####  7. blog_spit ：吐槽评论部分
```$xslt
整合MongoDB数据库，并实现增删查改分页操作及Redis缓存
```
####  8. blog_elasticsearch ：elasticsearch 操作
```$xslt
整合 elasticsearch 并实现数据 保存及查询分页功能。
```

#### 9. blog_user ：用户、管理员模块
```$xslt
用户的注册，增删查改功能，使用Redis和RabbitMQ实现验证码发送。
```

#### 10. blog_eureka ：注册中心
```$xslt
注册中心，由于管理各个组件，进行组件的注册与管理。
```

#### 11. blog_config ：配置中心
```$xslt
配置中心，用于配置管理。当把配置放到配置中心管理时，需要先启动配置中心模块。
启动顺序：blog_config --> blog_eureka 等。
```

