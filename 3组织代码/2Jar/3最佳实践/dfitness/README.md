# spring   springmvc-shiro-ace 优化1 ------  chapter-01-06
		
		本章目标
		持续改进项
			添加shiro事务和缓存机制
			整理目录结构，更加简洁和清晰
			完成请求POST中文支持
			整理请求路由
			整理前端框架技术
		后续模块开发内容
		


## 本章目标

1. 添加shiro事务和缓存机制
2. 整理目录结构，更加简洁和清晰
3. 整理JSP，静态资源文件的路由
4. 整理前端框架技术

我们在chapter-01-03(springmvc-shiro-ace快速入门)基础上进行调整

### 添加shiro事务和缓存机制

参考：chapter-01-05(springmvc-shiro-session 会话)

添加事务和缓存配置

cache/spring-cache.xml
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
						http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">

    <!--配置缓存管理器-->
    <bean id="shiroCacheManager" class="org.apache.shiro.cache.MemoryConstrainedCacheManager"></bean>

    <!--配置session缓存操作-->
    <bean id="shiroSessionDAO" class="org.apache.shiro.session.mgt.eis.MemorySessionDAO"></bean>

</beans>
```

如果需要ehcache或redis，对应修改这个文件， 参考： chapter-01-05(springmvc-shiro-session 会话)

mvc/spring-shiro.xml
```
 <!--配置Session管理器-->
    <bean id="shiroWebSessionManager" class="org.apache.shiro.web.session.mgt.DefaultWebSessionManager">
        <property name="cacheManager" ref="shiroCacheManager"></property>
        <property name="sessionDAO" ref="shiroSessionDAO"></property>
        <property name="sessionIdCookieEnabled" value="true"></property>
        <property name="sessionIdCookie" ref="sessionIdCookie"></property>
        <!--<property name="globalSessionTimeout" value="3600000"></property>-->
        <!--<property name="sessionValidationScheduler" ref="sessionValidationScheduler"></property>-->
        <!--<property name="sessionValidationSchedulerEnabled" value="false"></property>-->
        <!--<property name="deleteInvalidSessions" value="false"></property>-->
    </bean>

    <!--Shiro会话标识，默认为JSESSIONID，这与Web容器(JETTY, TOMCAT)默认会话标识JSESSIONID冲突，这里自定义Shiro会话标识-->
    <bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
        <constructor-arg name="name" value="ASESSIONID"/>
    </bean>

<!--    <bean id="sessionValidationScheduler"
          class="org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler">
        <property name="interval" value="3600000"></property>
    </bean>-->

    <!-- 安全管理器 -->
    <bean id="defaultWebSecurityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="realm" ref="shiroAuthorizingRealm"/>
        <property name="cacheManager" ref="shiroCacheManager"></property>
        <property name="sessionManager" ref="shiroWebSessionManager"></property>
        <property name="subjectDAO" ref="defaultSubjectDAO"></property>
    </bean>

    <bean id="defaultSubjectDAO" class="org.apache.shiro.mgt.DefaultSubjectDAO">
        <property name="sessionStorageEvaluator" ref="shiroSessionStorageEvaluator"></property>
    </bean>
```

新增session管理器，配置缓存，配置安全管理器支持事务和缓存。开启shiroAuthorizingRealm缓存机制

defaultSubjectDAO用于设置无状态应用（比如API接口服务）和有状态应用

com.morange.sys.session.ShiroSessionStorageEvaluator
```
public class ShiroSessionStorageEvaluator implements SessionStorageEvaluator {
    @Override
    public boolean isSessionStorageEnabled(Subject subject) {
        boolean enabled = false;
        if (WebUtils.isWeb(subject)) {
            HttpServletRequest request = WebUtils.getHttpRequest(subject);
            //set 'enabled' based on the current request.
            enabled = true;
        } else {
            //not a web request - maybe a RMI or daemon invocation?
            //set 'enabled' another way...
        }

        return enabled;
    }
}
```

## 整理目录结构，更加简洁和清晰

按代码模块化，标准化原则进行整理，以达到目录简洁清晰

整个项目目录结构分为三层
1. java代码目录结构
2. 配置文件目录结构
3. 前端及静态资源目录结构

先了解整体结构

整体目录结构:

![src-cata](src-cata.png)

java代码目录结构:

![java-cata](java-cata.png)

配置文件目录结构:

![resource-cata](resource-cata.png)

前端及静态资源目录结构:

![webapp-cata.png](webapp-cata.png)


### 完成请求POST中文支持

web.xml

```
    <!-- 解决Post请求乱码 start -->
    <filter>
        <filter-name>characterEncodingFilter</filter-name>
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <init-param>
            <param-name>forceEncoding</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>characterEncodingFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <!-- 解决Post请求乱码 end -->
```

### 整理请求路由

根路径的路由: 是指所有前端页面，以这个路径做为参考路径

默认情况根路径为 "/"


### 整理前端框架技术



## 后续模块开发内容

角色管理，权限管理等其他页面模块开发，参考用户管理

一个模块的开发，需要完成事项如下

1. 两个JSP界面（包含管理界面和编辑界面）。
2. 两个JS文件事件处理 (包含管理界面事件和编辑界面事件)。
3. 后端Dao，Service，Controller代码编写。

这样一个模块开发工作量，其实还是很大的。是否可以考虑将前端分离，有不同的人员来开发。


spring 分页 pagable
JSON结构， 下面数据总共207条数据，第20页的JSON格式
{
    "content":Array[7],
    "first":false,
    "last":true,
    "number":20,
    "numberOfElements":7,
    "pageable":{
        "offset":200,
        "pageNumber":20,
        "pageSize":10,
        "paged":true,
        "sort":{
            "sorted":true,
            "unsorted":false
        },
        "unpaged":false
    },
    "size":10,
    "sort":{
        "$ref":"$.pageable.sort"
    },
    "totalElements":207,
    "totalPages":21
}


