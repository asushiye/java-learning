# 5项目maven配置

    1认识pom配置
    2常用配置
      1本模块配置
      2属性配置
      3依赖配置
      4构建配置
          构建信息&资源配置
          插件配置
          插件管理配置
      5多环境配置
      6许可证&开发者&源代码管理&其他
    3多模块配置
      依赖关系
      聚合关系
      继承关系


## 1认识pom配置

如何使用maven来配置项目，完成项目功能。


## 2常用配置
下面了解常用maven配置

1. 本模块配置
2. 属性配置
3. 依赖配置
4. 构建配置
    构建信息&资源配置
    插件配置
    插件管理配置
5. 多环境配置
6. 许可证&开发者&源代码管理&其他

### 1. 本模块配置

也是一个pom最少必要的配置信息,通过idea的maven自动生成的
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
        <!-- 本模块描述符应遵循4.0.0pom模型 -->
    <modelVersion>4.0.0</modelVersion>
        <!-- 公司或者组织的唯一标志，也指定walker.study.maven包被安装本地仓库时的路径 -->
    <groupId>walker.study.maven</groupId>
        <!-- 本模块的唯一标识，一个groupId下面可能多个模块 -->
    <artifactId>sample</artifactId>
        <!-- 本模块的版本号，用于标识版本发布信息 -->
    <version>1.0-SNAPSHOT</version>


</project>
```

下面重要配置信息，可选择使用，以spring-boot-starter-parent为例说明
```xml
      <!-- 打包类型，支持jar,war,ear,pom类型 -->
  <packaging>pom</packaging>
      <!-- 本模块名称，maven产生文档时使用 -->
  <name>Spring Boot Starter Parent</name>
      <!-- 本模块描述，maven产生文档时使用 -->
  <description>Parent pom providing dependency and plugin management for applications
		built with Maven</description>
      <!-- 本模块主页地址，maven产生文档时使用 -->
  <url>https://projects.spring.io/spring-boot/#/spring-boot-starter-parent</url>
```

### 2. 属性配置
本模块中要使用的属性，统一在properties节点中配置
```xml
    <properties>
        <web.app.encoding>UTF-8</web.app.encoding>
        <jdk.version>1.8</jdk.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>

        <spring.version>5.1.2.RELEASE</spring.version>
        <mysql.version>6.0.6</mysql.version>
        <testng.version>6.14.3</testng.version>
        <servlet.version>4.0.0</servlet.version>
    </properties>
```

属性引用格式: `${java.version}`


### 3. 依赖配置

```xml
    <dependencies>
        <!-- 每个dependency都对应这一个jar包 -->
        <dependency>
            <!--通过groupId、artifactId、version指定要下载jar包 -->
            <groupId>walker.vip.system</groupId>
            <artifactId>common</artifactId>
            <version>1.0.0-SNAPSHOT</version>
            <!-- 指定包的作用域，scope取值如下 -->
            <!-- compile（编译并运行时都使用，默认值）、provided（编译时使用，打包不使用） -->
            <!-- runtime（运行时使用）、test（测试使用）、system（系统范围） -->
            <scope>test</scope>
            <!-- 设置指依赖是否可选，默认为false,在本模块中一定要引入 -->
            <optional>false</optional>
            <!-- 本依赖中，排除其他依赖，从本模块看可防止jar包冲突-->
            <exclusions>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>slf4j-api</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>
```

我们可以管理很多很多依赖，虽然在本模块中不使用，但是可以在子项目中被继承和选用
```xml
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot</artifactId>
        <version>2.1.5.RELEASE</version>
      </dependency>
    </dependencies>
  </dependencyManagement>
```


### 4. 构建配置

主要用配置，构建包的路径，包名，使用资源文件，使用构建插件

#### 构建信息&资源配置

```xml
    <build>
            <!-- 打包后的本地路径，默认target目录下 -->
        <directory>${local.app.directory}</directory>
            <!-- 打包名称，默认使用${artifactId}+${version} -->
        <finalName>${local.app.finalName}</finalName>
            <!-- 过滤需要引用的属性文件，用于多个环境，在运行或打包指定env值 -->
        <filters>
            <filter>src/main/resources/property/dbms-${env}.properties</filter>
            <filter>src/main/resources/property/suning-${env}.properties</filter>
        </filters>

            <!-- 资源文件配置 -->
        <resources>
            <resource>
                    <!-- 资源文件路径 -->
                <directory>src/main/resources</directory>
                    <!-- 启用过滤，按上面过滤来执行 -->
                <filtering>true</filtering>
                    <!-- 包含的资源文件列表，**/*.xml -->
                <includes>
                    <include>**/*-${env}.properties</include>
                </includes>
                    <!-- 例外的资源文件列表，**/*.xml -->
                <excludes>
                    <exclude>**/*.xml</exclude>
                </excludes>
            </resource>
        </resources>
    </build>
```

#### 插件配置

```xml
    <build>
            <!-- 插件配置 -->
        <plugins>
            <plugin>
                    <!-- groupId,artifactId,version 标识插件身份-->
                <groupId>org.eclipse.jetty</groupId>
                <artifactId>jetty-maven-plugin</artifactId>
                <version>9.4.5.v20170502</version>
                    <!-- jetty插件属性配置 -->
                <configuration>
                        <!-- 间隔扫描时间，用于热部署，若代码有调整，自动编译和运行 -->
                    <scanIntervalSeconds>10</scanIntervalSeconds>
                        <!-- http连接器配置，配置监听端口 -->
                    <httpConnector>
                        <port>${local.server.port}</port>
                    </httpConnector>
                        <!-- web应用程序配置，配置上下文路径 -->
                    <webApp>
                        <contextPath>${local.server.path}</contextPath>
                    </webApp>
                </configuration>
            </plugin>
                <!-- tomcat插件配置 -->
            <plugin>
                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <charset>${web.app.encoding}</charset>
                        <!-- 本地运行监听端口 -->
                    <port>${local.server.port}</port>
                        <!-- 上下文路径 -->
                    <path>${local.server.path}</path>
                        <!-- 部署地址 -->
                    <url>${deploy.url}</url>
                        <!-- 部署服务器，参考maven setting.xml配置 -->
                    <server>${deploy.server}</server>
                </configuration>
            </plugin>
                <!-- maven编译配置，指定java版本和编码格式，及编译器参数 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>${jdk.version}</source>
                    <target>${jdk.version}</target>
                    <encoding>${web.app.encoding}</encoding>
                    <compilerArgument/>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

#### 插件管理配置

用途和依赖管理配置，用于在子项目中继承和选用

```xml
  <build>
    <pluginManagement>
      <plugins>
        <plugin>
          <groupId>org.apache.johnzon</groupId>
          <artifactId>johnzon-maven-plugin</artifactId>
          <version>${johnzon.version}</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
```

### 5. 多环境配置
```xml
        <!-- 本模块使用的配置文件，用于指定多环境，和激活的环境-->
    <profiles>
        <profile>
                <!-- 标识 -->
            <id>dev</id>
                <!-- 激活配置，只能启动一个 -->
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
                <!-- 在当前环境下的属性配置 -->
            <properties>
                <env>dev</env>
                <logger.level>debug</logger.level>
                <deploy.url>http://192.168.*.145:80/manager/text</deploy.url>
                <deploy.server>tomcatprd</deploy.server>
            </properties>
        </profile>
        <profile>
            <id>prd</id>

            <properties>
                <env>prd</env>
                <logger.level>debug</logger.level>
                <deploy.url>http://192.168.*.102:80/manager/text</deploy.url>
                <deploy.server>tomcatprd</deploy.server>
            </properties>
        </profile>
    </profiles>
```

### 6. 许可证&开发者&源代码管理&其他

1. 组织
2. 许可证
3. 开发者
4. 源代码管理
5. 问题管理
6. 持续集成(未验证过)

project 根节点下的配置

```xml
      <!-- 模块归属组织 -->
  <organization>
    <name>Pivotal Software, Inc.</name>
    <url>https://spring.io</url>
  </organization>
      <!-- 许可证 -->
  <licenses>
    <license>
      <name>Apache License, Version 2.0</name>
      <url>https://www.apache.org/licenses/LICENSE-2.0</url>
    </license>
  </licenses>
      <!-- 开发者 -->
  <developers>
    <developer>
      <name>Pivotal</name>
      <email>info@pivotal.io</email>
      <organization>Pivotal Software, Inc.</organization>
      <organizationUrl>https://www.spring.io</organizationUrl>
    </developer>
  </developers>
      <!-- 源代码管理 -->
  <scm>
    <connection>scm:git:git://github.com/spring-projects/spring-boot.git</connection>
    <developerConnection>scm:git:ssh://git@github.com/spring-projects/spring-boot.git</developerConnection>
    <url>https://github.com/spring-projects/spring-boot</url>
  </scm>
      <!-- 问题管理 -->
  <issueManagement>
    <system>Github</system>
    <url>https://github.com/spring-projects/spring-boot/issues</url>
  </issueManagement>
      <!-- 持续集成 -->
  <ciManagement>
        <!-- 持续集成系统 -->
    <system></system>
        <!-- 链接 -->
    <url></url>
        <!-- 通知 -->
    <notifiers></notifiers>
  </ciManagement>
```

## 3多模块配置

我们这里模块是值jar包，war包，他们之间关系配置，有依赖关系，聚合关系，继承关系等等

依赖关系之前已经详细说明，下面说下聚合关系，继承关系

### 聚合关系

在一个maven项目中，我们可以同时聚合多个maven模块

配置如下，project根节点下
```xml
<modules>
      <!-- 模块名称,目录名称一致 -->
   <module>my-app1</module>
   <module>my-app2</module>
   <module>my-app3</module>
</modules>
```

### 继承关系

继承关系，我们这里使用spring boot来说明

```xml
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.1.5.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
```

正常springboot项目，我们都会继承spring-boot-starter-parent

`spring-boot-starter-parent` 为我们提供公用pom配置，比如属性，依赖，插件等等

我们可以从父级的pom中继承来使用

```xml
<?xml version="1.0" encoding="utf-8"?><project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.1.5.RELEASE</version>
    <relativePath>../../spring-boot-dependencies</relativePath>
  </parent>
  <artifactId>spring-boot-starter-parent</artifactId>
  <packaging>pom</packaging>
  <name>Spring Boot Starter Parent</name>
  <description>Parent pom providing dependency and plugin management for applications
		built with Maven</description>
  <url>https://projects.spring.io/spring-boot/#/spring-boot-starter-parent</url>
  <properties>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <java.version>1.8</java.version>
    <resource.delimiter>@</resource.delimiter>
    <maven.compiler.source>${java.version}</maven.compiler.source>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.target>${java.version}</maven.compiler.target>
  </properties>
  <build>
    <resources>
      <resource>
        <filtering>true</filtering>
        <directory>${basedir}/src/main/resources</directory>
        <includes>
          <include>**/application*.yml</include>
          <include>**/application*.yaml</include>
          <include>**/application*.properties</include>
        </includes>
      </resource>
      <resource>
        <directory>${basedir}/src/main/resources</directory>
        <excludes>
          <exclude>**/application*.yml</exclude>
          <exclude>**/application*.yaml</exclude>
          <exclude>**/application*.properties</exclude>
        </excludes>
      </resource>
    </resources>
    <pluginManagement>
      <plugins>
        <plugin>...</plugin>
        <plugin>...</plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>

```

**packaging** 为pom类型，表明该模块只是用配置pom.xml文件

在属性配置中，指定maven编译java版本和编码，及配置资源和插件管理

我并没有看到依赖配置，在哪配置呢？

该模块又从`spring-boot-dependencies`上继承的，而这个模块就指定springboot最佳实践的依赖包

1. 指定了依赖包及依赖包的版本号
2. 指定了插件及插件的版本号

继续的好处是，我们可以方便重父级模块中继承使用，简化pom配置，统一了第三方库的使用
