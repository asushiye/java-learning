# 1环境搭建

    window开发环境
      Java开发最佳实践目录结构
    linux运行环境
      openJDK运行环境
      安装Oracle JDK运行环境


## 摘要

本章主要讲解
1. 如何在windows环境下搭建java开发环境及最佳目录结构
2. 如何在linux环境搭建java项目运行及最佳配置

## window开发环境

### Java开发最佳实践目录结构

`E:\6_Java\1_JDK`

官网下载后，放在`E:\6_Java\1_JDK\jdk1.8.0_91`

配置环境变量：
```
JAVA_HOME=E:\6_Java\1_JDK\jdk1.8.0_91
JRE_HOME=E:\6_Java\1_JDK\jdk1.8.0_91\jre

PATH变量中添加%JAVA_HOME%\bin;%JRE_HOME%\bin
```


### java开发环境


## linux运行环境

linux版本为 `CentOS Linux release 7.1.1503 (Core)  内核为 3.10.0-229.el7.x86_64`

centos7默认提供openJDK，我们看下是否满足需求

目录结构说明

在根目录下，新建公司目录：
```
/daphne 安装各种应用
/daphne/java 安装oracle java
```

### openJDK运行环境

查看当前java版本 `java -version`

查看jdk安装包 `rpm -qa|grep jdk`

```
java-1.8.0-openjdk-headless-1.8.0.31-2.b13.el7.x86_64
java-1.7.0-openjdk-headless-1.7.0.75-2.5.4.2.el7_0.x86_64
ldapjdk-4.18-14.el7.noarch
java-1.7.0-openjdk-1.7.0.75-2.5.4.2.el7_0.x86_64
java-1.6.0-openjdk-devel-1.6.0.34-1.13.6.1.el7_0.x86_64
java-1.7.0-openjdk-devel-1.7.0.75-2.5.4.2.el7_0.x86_64
java-1.6.0-openjdk-1.6.0.34-1.13.6.1.el7_0.x86_64
java-1.8.0-openjdk-devel-1.8.0.31-2.b13.el7.x86_64
java-1.8.0-openjdk-1.8.0.31-2.b13.el7.x86_64
```
这里安装1.6，1.7，1.8,默认使用了1.7

卸载jdk1.6版本
```
rpm -e --nodeps  java-1.6.0-openjdk-1.6.0.34-1.13.6.1.el7_0.x86_64 java-1.6.0-openjdk-devel-1.6.0.34-1.13.6.1.el7_0.x86_64
```

卸载jdk1.7版本
```
rpm -e --nodeps java-1.7.0-openjdk-headless-1.7.0.75-2.5.4.2.el7_0.x86_64 java-1.7.0-openjdk-1.7.0.75-2.5.4.2.el7_0.x86_64 java-1.7.0-openjdk-devel-1.7.0.75-2.5.4.2.el7_0.x86_64
```

再查看当前版本
```
java -version
openjdk version "1.8.0_31"
OpenJDK Runtime Environment (build 1.8.0_31-b13)
OpenJDK 64-Bit Server VM (build 25.31-b07, mixed mode)
```

### 安装Oracle JDK运行环境

下载官网的jdk-8u45-linux-x64.tar.gz 到/daphne/tools，这里我们使用1.8版本

解压到/daphne/java目录下 `tar -xzvf jdk-8u45-linux-x64.tar.gz`

配置环境变量

```
vi /etc/profile

export JAVA_HOME=/daphne/java/jdk1.8.0_77
export JRE_HOME=$JAVA_HOME/jre
export PATH=$JAVA_HOME/bin:$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$PATH
```
