# filter
    配置
    匹配
      匹配顺序
      最常用配置
      最佳实战

## 配置
```xml
    <filter>
        <filter-name>LogCost</filter-name>
        <filter-class>com.morange.system.context.filter.LogCostFilter</filter-class>
        <init-param>
            <param-name>costType</param-name>
            <param-value>time</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>LogCost</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
```

##  url-pattern

只能使用/*，不能使用/，因为tomcat关于加载filter的配置代码没有关于“/”的
