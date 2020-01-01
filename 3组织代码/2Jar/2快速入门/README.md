# 2快速入门
    1认识Jar
      什么是Jar
      Jar目录结构
    2构建Jar
      java打包jar
      idea打包jar
      maven打包jar
    3maven可执jar

## 1认识Jar

### 什么是Jar

jar是可复用的java库。

它是将Java类文件、相关的元数据和资源文件组成一个Jar文件，是代码更加方便使用和管理。

jar以ZIP格式构建.jar为文件扩展名, 可使用JDK自带的jar命令创建或提取JAR文件。

Jar可以将java项目代码进行模块化管理

### Jar目录结构
![spring-jar](spring-jar.png)

* org目录存放spring框架的class文件
* META-INF目录存放该Jar包的元数据
* 文件浏览器中地址栏，指定了spring-core的maven安装路径

Jar元数据，查看MANIFEST.MF文件
```
Manifest-Version: 1.0
Implementation-Title: spring-core
Automatic-Module-Name: spring.core
Implementation-Version: 5.1.6.RELEASE
Created-By: 1.8.0_201 (Oracle Corporation)
```

这个Jar是不能单独执行的，因为没有指定主类 `Main-Class: `

项目我们用不同的方式来实现jar


## 2构建Jar

### 2java打包jar

src/Hero.Java
```java
class Hello{
    public static void main(String[] agrs){
        System.out.println("hello jar");
    }
}
```

```
javac Hello.java  #用于编译
java Hello #执行Hello.class
jar -cvf hello.jar Hello.class #用于打包 c表示创建 v表示打包进度 f表示jar文件
```

执行jar `java -jar hello.jar`

报错，因为没有在jar元数据中指定java的入口类

在META-INF/MANIFEST.MF 新增 `Main-Class: Hello` **空格**不能省略
```
Manifest-Version: 1.0
Created-By: 1.8.0_91 (Oracle Corporation)
Main-Class: Hello
```

重新执行jar就能成功输出: hello jar


**下面使用带路径来说明**

src/asu/Hero.Java
```java
package asu;
class AsuHello{
    public static void main(String[] agrs){
        System.out.println("hello Asu jar");
    }
}
```

```
javac .\asu\*  #编译asu目录下所有java
java asu.AsuHello #执行asu/AsuHello.class
jar -cvf hello.jar Hello.class #用于打包 c表示创建 v表示打包进度 f表示jar文件
java -jar AsuHello.jar # 执行
```

在META-INF/MANIFEST.MF 新增 `Main-Class: asu.AsuHello`


在我们了解了如何使用jdk进行构建jar后，下面我们使用ide工具来快速构建


### idea打包jar

####  新建项目
新建名字叫idea的项目
![idea-jar](idea-jar.png)

#### 编辑构建Artifact

Project Structure -> Artifacts -> 新增 -> 选择Jar -> 选择

![idea-jar2](idea-jar2.png)

![idea-jar3](idea-jar3.png)

如果只是作为公用jar包使用，则不需要指定入口类

![idea-jar4](idea-jar4.png)
如果选择include in project build 则会在build 项目也构建jar包

编译jar `Build -> Build Artifacts`
![idea-jar5](idea-jar5.png)


由于指定了入口类，因此我们选择运行idea.jar

按这个方法，我们可以建构更多公用Jar包，也给其他项目使用。


### maven打包jar

#### 搭建Jar项目

新建simple的空maven项目

添加Main类
```java
/**
 * @author : zhenyun.su
 * @since : 2019/5/24
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```

执行`mvn package`，即可打包，并生成`\simple-1.0-SNAPSHOT.jar`

如何想控制jar生成，可添加jar插件

pom.xml添加maven构建jar插件
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>walker.study.jar</groupId>
    <artifactId>simple</artifactId>
    <version>1.0-SNAPSHOT</version>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <useUniqueVersions>false</useUniqueVersions>
                            <classpathPrefix>lib/</classpathPrefix>
                            <mainClass>Main</mainClass>
                        </manifest>
                        <manifestEntries>
                            <version>${project.version}</version>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
maven-compiler-plugin 用于指定编译的Jdk版本

maven-jar-plugin 用于配置Jar

#### 执行打包

![maven-package](maven-package.png)

在target目录下生成 simple-1.0-SNAPSHOT.jar
![mavenpackage](mavenpackage.png)
打开jar包，发现只有META-INF和Main.class

使用maven打包和直接idea打包区别，是在META-INF目录下除了MANIFEST.MF外，还多了maven目录

maven目录存放了项目pom.xml文件和pom.properties


## 3maven可执jar

参考：https://blog.csdn.net/silentwolfyh/article/details/81506977

2、方法一：使用maven-jar-plugin和maven-dependency-plugin插件打包
3、方法二：使用maven-assembly-plugin插件打包
4、方法三：使用maven-shade-plugin插件打包


### 使用maven-jar-plugin和maven-dependency-plugin插件打包
```xml

    <build>
        <plugins>

            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <addClasspath>true</addClasspath>
                            <classpathPrefix>lib/</classpathPrefix>
                            <mainClass>com.wqc.main.SpringStart</mainClass>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-dependency-plugin</artifactId>
                <version>3.1.1</version>
                <executions>
                    <execution>
                        <id>copy-dependencies</id>
                        <phase>package</phase>
                        <goals>
                            <goal>copy-dependencies</goal>
                        </goals>
                        <configuration>
                            <outputDirectory>${project.build.directory}/lib</outputDirectory>
                        </configuration>
                    </execution>
                </executions>
            </plugin>

        </plugins>
    </build>
```

生成jar包 和第三方库lib目录，只有同时存在这个两个文件，才能正常运行。

### maven-assembly-plugin 来实现
```xml

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.wqc.main.SpringStart</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

### maven-shade-plugin 来实现
```xml
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <!-- 用这个maven打包插件 -->
    <artifactId>maven-shade-plugin</artifactId>
    <version>3.2.1</version>
    <executions>
        <execution>
            <phase>package</phase>
            <goals>
                <goal>shade</goal>
            </goals>
            <configuration>
                <!-- 默认值为true.注意这个属性,如果你用这个插件来deploy,或者发布到中央仓库，这个属性会缩减你的pom文件,会把你依赖的<dependency>干掉 -->
                <createDependencyReducedPom>false</createDependencyReducedPom>
                <transformers>
                    <transformer
                            implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                        <resource>META-INF/spring.handlers</resource>
                    </transformer>
                    <transformer
                            implementation="org.apache.maven.plugins.shade.resource.AppendingTransformer">
                        <resource>META-INF/spring.schemas</resource>
                    </transformer>
                    <transformer
                            implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                        <!-- 这个是你的程序入口文件 -->
                        <mainClass>com.alibaba.dubbo.container.Main</mainClass>
                    </transformer>
                </transformers>
            </configuration>
        </execution>
    </executions>
  </plugin>
  <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-resources-plugin</artifactId>
      <version>3.1.0</version>
      <configuration>
          <!-- 设置字符编码集 -->
          <encoding>UTF-8</encoding>
      </configuration>
  </plugin>
```

在使用不同java框架时，配置有一些差别，运行异常时可自行百度解决
