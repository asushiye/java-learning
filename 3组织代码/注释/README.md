# 注释与文档

    注释表示
    注释文档
      注释语法
      常用标签

## 注释表示

### 多行注释

/*
*  statement1; 这里*号也被注释
*  statement2;
*/
或
/* statement1;
statement2; */


### 单行注释

//statement


## 注释文档

代码文档撰写最大问题，大概就是对文档维护，

如果代码和文档分离，代码更新，文档也要更新，这是很难得

最简单方法将代码和文档结合起来，放在同一个文件中

java提供注释语法，使用javadoc工具，从java源文件中提取注释，并生成html文档

javadoc工具是JDK安装的一部分

1. 注释语法
2. 常用标签

### 注释语法

所有注释命令，只能出现"/**" 之后，注释结束于"*/"

注释位置可以在类，方法，成员前面

```java
/** class comment */
public class MyClass{
  /** field comment */
  protected String name;
  /** method comment */
  public void print(){
    system.out.println("hello");
  }
}
```

javadoc工具只能提取**public和protected**的成员和方法，因为这些才是提供客户端使用

### 常用标签

@see： 引用其他类，允许用户引用其他类的文档。生成的是html的超链接标签

@docRoot： 将注释生成到根文档，用于文档树页面的显示超链接

@version： 格式 @version version_information 显示版本信息

@author： 显示作者信息，格式 @author author_information

@param: 用于方法上，指定方法参数，格式 @param param_name description

@return: 用于方法上，指定返回值，格式 @return  description

@Deprecated : 表示该类或方法，已有新类和方法替代，将会在以后的版本中不在使用
