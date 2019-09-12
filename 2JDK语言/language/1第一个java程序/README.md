# 第一个java程序
    创建你的第一个java程序
    java项目结构
      java源代码和Class目录
      编译java源代码


## 创建你的第一个java程序
使用idea 2017.4版本工具

菜单 new -> project 按默认下一步，选择项目路径

在src目录下,新增package myfirstapp

新增MyJavaApp类
```java
package myfirstapp;
/**
 * @author : zhenyun.su
 * @comment :  运行时，配置program arguments hello world
 * @since : 2019/9/12
 */
public class MyJavaApp {
    public static void main(String[] args){
        System.out.println("your first java app");
        System.out.println(args[0]);
        System.out.println(args[1]);
    }
}
```

右击编辑窗口，选择 run MyJavaApp

设置命令行参数`hello world`

Run Configuration
配置 application 在program arguments： Hello World

控制台输出结果：
```
your first java app
hello
world
```

## java项目结构

a basic Java project is structured,

how the Java code is compiled,

and how to execute the finished Java program

### java源代码和Class目录

package 是将一组相关java文件放在一个文件夹

默认约定java source 文件一般放在src文件夹中。

如果maven建构，则放在src/main/java目录下 java目录下就你存放项目的路径。

### 编译java源代码

下面使用JDK工具来编译和运行
```
mkdir out
javac src/mypackage/*.java -d out
java -cp out myfirstapp.MyJavaApp hello zhenyun.su
```

输出结果
```
your first java app
hello
zhenyun.su
```
