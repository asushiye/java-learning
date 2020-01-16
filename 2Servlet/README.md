# servlet


servlet

### url-pattern

最常用配置
1. 匹配所有url： <url-pattern>/</url-pattern>
2. 匹配扩展名：  <url-pattern>*.do</url-pattern>
3. 匹配路径：   <url-pattern>/druid/*</url-pattern>

关键说明：
```
若使用<url-pattern>/druid/*</url-pattern> ，就按绝对路径访问，
必须确保url地址和文件路径一致，否则找不到文件
```

不支持配置
1. <url-pattern>/*</url-pattern>
2. <url-pattern>/*.do</url-pattern> <url-pattern>/dd/*.do</url-pattern>


实例如下配置
```xml
    <servlet>
        <servlet-name>exception</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:servlet/exception-servlet.xml</param-value>
        </init-param>
    </servlet>
    <servlet-mapping>
        <servlet-name>exception</servlet-name>
        <url-pattern>*.exception</url-pattern>
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

    <servlet>
        <servlet-name>DruidStatView</servlet-name>
        <servlet-class>com.alibaba.druid.support.http.StatViewServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>DruidStatView</servlet-name>
        <url-pattern>/druid/*</url-pattern>
    </servlet-mapping>
```
