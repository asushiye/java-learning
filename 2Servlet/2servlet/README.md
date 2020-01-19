# servlet
    配置
    匹配
      匹配顺序
      最常用配置
      最佳实战

## 配置
```xml
    <servlet>
        <servlet-name>login</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/servlet/login-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
        <multipart-config>
            <max-file-size>5242880</max-file-size>
            <max-request-size>10485760</max-request-size>
            <file-size-threshold>0</file-size-threshold>
        </multipart-config>
    </servlet>
    <servlet-mapping>
        <servlet-name>login</servlet-name>
        <url-pattern>*.html</url-pattern>
    </servlet-mapping>
```

## sevlet - url-pattern

### 匹配顺序

1. 精确匹配
2. 路径匹配
3. 扩展名匹配
4. 缺省匹配

#### 1精确匹配
```xm
配置的是完整地址
servlet1：<url-pattern>/world</url-pattern>; servle2：<url-pattern>/*</url-pattern>
当一个请求http://localhost:8080/world来的时候，servle1匹配到，不再用servlet2匹配,
```
#### 2路径匹配
```
serlvet1：<url-pattern>/a/b/*</url-pattern>; servlet2:<url-pattern>/a/b/c/*</url-pattern>
如果请求是http://localhost:8080/a/b/c,那么匹配到servlet1
```
#### 3扩展名匹配
```
servlet1：<url-pattern>/*</url-pattern>，servlet-mapping2：<url-pattern>*.jsp</url-pattern>
当一个请求http://localhost:8080/a.jsp来的时候，servlet1匹配到，不再用servlet2匹配不到
```
#### 4缺省匹配，可匹配所有地址
```
以上都找不到servlet，就用默认的servlet，配置为<url-pattern>/</url-pattern>
```

### 最常用配置
1. 匹配扩展名：  <url-pattern>*.do</url-pattern>
2. 匹配路径：    <url-pattern>/druid/*</url-pattern>
3. 匹配所有url： <url-pattern>/</url-pattern>

关键说明：
```
若使用<url-pattern>/druid/*</url-pattern> ，就按绝对路径访问，
必须确保url地址和文件路径一致，否则找不到文件
```

不支持配置
1. <url-pattern>/*</url-pattern>
2. <url-pattern>/*.do</url-pattern> <url-pattern>/dd/*.do</url-pattern>


### 最佳实战
```xml
    <servlet>
        <servlet-name>login</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/servlet/login-servlet.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>login</servlet-name>
        <url-pattern>*.html</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>DruidStatView</servlet-name>
        <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>DruidStatView</servlet-name>
        <url-pattern>/druid/*</url-pattern>
    </servlet-mapping>

    <servlet>
        <servlet-name>api</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:servlet/api-servlet.xml</param-value>
        </init-param>
        <load-on-startup>2</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>api</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
```
