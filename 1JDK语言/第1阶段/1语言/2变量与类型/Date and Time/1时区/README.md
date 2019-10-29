# 1时区
		时区概念
		时区设置
			windows系统
			Centos系统
			mysql时区设置
			JDBC客户连接

## 时区概念
在了解时间之前，需要了解下时区，不同时区，时间值不一样。

将世界分为24个时区，以UTC时间为基准

![timezone1](timezone1.png)

UTC为世界协调时间，也称世界标准时间，将格林威治GMT时区的时间，作为UTC时间
```
往东边称为东时区，为正数， 比如东8区为北京时区也称为CST中国标准时间 UTC+08:00
往西边称为西时区，为负数， 比如西6区，UTC-06:00
```
UTC的时间为`2019-09-25 06:25:01`, 则中国时间为UTC+08:00 `2019-09-25 14:25:01`

为了更加容易记录不同地区的时区，采用如下的命名方式
```
CST - America/Chicago
CTT - Asia/Shanghai
JST - Asia/Tokyo
ECT - Europe/Paris
```

### 时间戳：是指从1970-01-01 00:00:00 UTC 时区开始算起

时间戳：是指从1970-01-01 00:00:00 UTC 时区开始算起

refer to web: https://www.timeanddate.com/time/zones/

## 时区设置

### windows系统

控制台 -> 时间和语言 -> 日期和时间  选择时区(UTC+08:00 北京，重庆)

### Centos系统
查看时区
```
cat /etc/sysconfig/clock
ZONE="Asia/Shanghai"

cd /usr/share/zoneinfo 查看所有时区
```
设置时区
```
rm -f /etc/localtime
ln -s /usr/share/zoneinfo/Asia/Shanghai  /etc/localtime

或者
tzselect -> 5) Asia -> 9) China  -> 1) east China - Beijing, Guangdong, Shanghai, etc. -> Yes
永久生效 export TZ='Asia/Shanghai'; 将添加 /etc/profile
```

### mysql时区设置

mysql有两个系统变量决定数据的时区，

system_time_zone 表示服务系统时区， mysql启动时从主机获取的时区作为mysql的系统时区，在以后不会改变的。

time-zone 表示当前时区，默认值SYSTEM 服务系统时区作为数据库时区

可以修改这个值，通过my.ini的default-time-zone配置

比如`default-time-zone = '+8:00'` 表示东8区

或者`default-time-zone = 'Asia/Shanghai'`

#### JDBC客户连接

在链接Mysql时，通过参数serverTimezone指定时区，会将时间由JDK时区转换为指定时区的时间
```
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://192.168.*.135:3306/dsale?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    username: dwechat
    password: Dwechat!123
    type: com.alibaba.druid.pool.DruidDataSource
```

这里serverTimezone=Asia/Shanghai 表示中国上海时区，也就是东8区 UTC+08:00

当我们将serverTimezone=UTC时，JDK运行环境的时区为东8区，则提交时，数据库时间自动减少8个小时。
