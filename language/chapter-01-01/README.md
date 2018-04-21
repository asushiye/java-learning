使用idea 2017.4版本工具


# create, compile and run your first Java app,

创建你的第一个java程序

>启动Idea  菜单 new -> project 按默认下一步，选择项目路径


在src目录下
```
package mypackage;

public class MyJavaApp {
    public static void main(String[] args){
        System.out.println("your first java app");
        System.out.println(args[0]);
        System.out.println(args[1]);
    }
}
```


右击编辑窗口，选择 run MyJavaApp
或者
Run Configuration
配置 application 在program arguments： Hello World


# a basic Java project is structured, how the Java code is compiled, and how to execute the finished Java program

## java source and class directories

package 是将一组相关java file放在一个文件夹

java source 文件一般放在src文件夹中。如果maven建构，则放在src/main/java目录下 java目录下就你存放项目的路径。

## compiling the java source code 

```
java sdk

cd chapter-01-01

mkdir out 

javac src/mypackage/*.java -d out

java -cp out mypackage.MyJavaApp hello world

```