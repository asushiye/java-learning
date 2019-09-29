# 2变量与类型

    变量
      1. 变量分类
      2. 变量语法
    数据类型
      1. 为什么要不同数据类型
      2. 基本数据类型
      3. 包装类型
      4. 基本类型和包装类型相会转换
      5. 5autoboxing - 自动装拆箱
      6. 和String相会转换
      7. 数字格式化
      8. 日期格式化
    类与对象
      1. Java Fields
      2. Java Methods
      3. Constructors
    包

## 变量
1. 变量分类
2. 变量语法

### 1变量分类

#### 按定义的位置分为

1. 实例变量： 对象中变量
2. 局部变量： 方法中变量

#### 按存放的值分为
1. 基本类型的变量称为基本变量，该变量存放值内容
2. 对象类型的变量称为引用变量，该变量存放对象的引用

#### 按static关键字分为
1. 静态变量
2. 非静态变量

提示：静态变量不能在方法局部中声明，也不能用于方法参数

#### 按final关键字分为
1. final变量，只能初始化赋值，以后就不能使用，结合static可定义常量
2. 非fianl变量，普通变量使用


### 2变量语法

1. 基本语法
2. 命名规范
3. 默认值

#### 基本语法

```
变量声明
type name ;
int myInt ;

变量赋值
myInt =1 ;
float myFloat =2.0 ;

读取变量内容
float myFloat =2.0 ;
myFloat = myFloat +1;
```
#### 命名规范
1. Java variable names are case sensitive. The variable name money is not the same as Money or MONEY.
2. Java variable names must start with a letter, or the $ or _ character.
3. Variable names cannot be equal to reserved key words in Java. For instance, the words int or for
4. If variable names consist of multiple words, each word after the first word has its first letter written in uppercase.
   For instance, "variableName" or "bigApple".
5. Static final fields (constants) are named in all uppercase, .  For instance "EXCHANGE_RATE" or "COEFFICIENT".

#### 默认值

如果是类成员的话，在实例对象后，默认值如下：
|类型|默认值|
|-|-|
|boolean   |false   |
|char   |\u0000(null)  |
|byte   |(byte)0   |
|short   |(short)0   |
|int   |0   |
|long   |0   |
|float   |0.0f   |
|double   |0.0d   |

局部变量，方法中变量

如果没有赋值，则不能使用


## 数据类型

1. 为什么要不同数据类型
2. 基本数据类型
3. 包装类型
4. 基本类型和包装类型相会转换
5. 5autoboxing - 自动装拆箱
6. 和String相会转换
7. 数字格式化
8. 日期格式化

### 1为什么要不同数据类型

为了保证java安全性，java注重类型，

我们可以想象变量就是杯子，它是一种容器。不同类型就是不同大小杯子。

java不会让你做出将大杯子的水装入小杯子中的危险动作。

![datatype](datatype.png)

### 2基本数据类型
Here are examples of how to declare variables of all the primitive data types in Java:
* boolean myBoolean   true or falses
* byte    myByte;      8bit   -128 ~ 127
* short   myShort;     16bit
* char    myChar;      16bit
* int     myInt;       32bit
* long    myLong;      64bit
* float   myFloat;     32bit
* double  myDouble;    64bit

特别注意：
1. 所有数值类型都有正负号，所以不要去找无符号的数值类型
2. boolean类型所占用空间大小没有明确指定，定义取true或false
3. 基本数据类型存放在"堆栈"中,速度更快
4. 基本数据类型都有一个包装类

### 3包装类型
Here are examples of how to declare variables of the object types in Java:
* Boolean    myBoolean   true or falses
* Byte       myByte;      8bit   -128 ~ 127
* Short      myShort;     16bit
* Character  myChar;      16bit
* Integer    myInt;       32bit
* Long    myLong;      64bit
* Float   myFloat;     32bit
* Double  myDouble;    64bit
* String  myString;

Character和Integer 和基本类型char和int名称不一样，其他都一样只是第一个字母大写

### 4基本类型和包装类型相会转换

#### 基本类型转换包装类型

```java
    int a=888;
    Integer count = new Integer(a);
    boolean yes=true;
    Boolean flag = new Boolean(yes);
```

#### 包装类型转换基本类型

街上面的代码
```java
    int b= count.intValue();
    boolean yesyes = flag.booleanValue();
```

除了intValue以外，还有下面的
```java
    byte bb =count.byteValue(); //将888转换byte时，超出范围，只截取前面8位的值，最后为120
    long l = count.longValue();
    short s = count.shortValue();
```

### 5autoboxing - 自动装拆箱

自动装拆箱，实际上java编译器能自动包装类型和基本类型，自动进行装箱和拆箱，实现类型自动转换。

![autoboxing1](autoboxing1.png)

![autoboxing2](autoboxing2.png)

为什么不直接声明为基本类型呢？ `ArrayList<int>`

这是因为泛型不支持基本数据类型

autoboxing乐趣多，到处都用的到

#### 方法参数，返回值, 条件判断布尔

![autoboxing3](autoboxing3.png)

#### 数值运算,赋值

Integer i=0;  i++; i=i+1;

Double d=x x可以是Integer 或int

### 6和String相会转换

将数值的字符串转换为 int，long，double类型，通过相对应包装类来实现
```java
        String value= "2";
        int x= Integer.parseInt(value);  //2
        long l = Long.parseLong(value);  //2
        double d = Double.parseDouble(value);  //2.0
        int y = Integer.valueOf(value); //2
        long l = Long.valueOf(value);  //2.0

```

如果value值，不是数值，则抛出 **NumberFormatExcetion**异常

将其他类型转换为字符串
```java
        Integer v1 = 22;
        Double v2 = 22.2;
        Boolean v3 = true;
        String value1 = v1.toString();
        String value2 = v2.toString();
        String value3= v3.toString();
```

### 7数字格式化

使用String.format()方法来实现

格式说明：
![format1](format1.png)

type类型和传递的值类型，必须一致
* %d 必须整型 int
* %f 必须浮点型
* %c 必须整型 int, short, long

```java
        int one = 20;
        double two = 1000334245.1234;
        String sformat = String.format("the rank is %,10d out of %,3.2f", one, two);
        System.out.println(sformat);
```

the rank is         20 out of 1,000,334,245.12

### 8日期格式化

```java
```
#### 高精度数字

java提供两个高精度计算的类：BigInteger和BigDecimal。

它们没有对应基本类型，但是可以向Integer和Float操作数据

他们不能直接使用运算符号来完成**加减乘除**操作,通过调用对象方法来实现，

相对速度慢，但是精度高，不会丢失信息

BigDecimal一般用来表示金额


## 类与对象

类定义对象的所知所为：

* 所知即是对象的实例变量
* 所为即是对象的方法

1. 方法可依据实例变量来执行不同逻辑
2. 方法可以有参数，返回值

方法定义的参数称为形参，供方法内部使用，传给方法的参数称为实参，

实参传形参都是值得拷贝，如果是对象的话，则将对象的引用拷贝给形参。

方法一定指定返回值，如果是void类型，则不需要返回，否则一定要返回可以类型一致的值。



Define Java Class in Java File

```java
public class MyClass {
    private int number = 0;

    public MyClass() {
    }
    public MyClass(int theNumber) {
        this.number = theNumber;
    }
}
```

java class access Modifiers  `package, public`, can not `private, protected`


A Java class can contain:

1. Java Fields
2. Java Methods
3. Constructors



### Java Fields

Field Declaration Syntax

[access_modifier] [static] [final] type name[= initial value] ;

#### Java Field Access Modifiers

```java
public class Customer {

    private   String email;      // the class itself can access this java field.
    [package] String position;  // no modifier = package access modifier  class in the same package can access
    protected String name;      // class itself and subclass can access modifier
    public    String city;      // all class in same application can access

}
```

#### Static and Non-static Fields

* Static Fields  // called class Variables
* Non-static Fields // called Object or install Variable

#### final Fields

A final field after object instance, cannot have its value changed

 when object instance, once assigned

```java
public class Customer {
    final String field1 = "Fixed Value";
}
```


Since static final fields are often used as constants

```java
public class Customer {
    static final String CONSTANT_1 = "Fixed Value";
}
```


### Java Methods

[access_modifier] [static] return_type function_name([final method parameter]) [throws Exception] ;

1. method parameter
2. access modifier
3. throws Exception
4. return type

#### method parameter
A method parameter similar to a variable. You can read its value, and change its value too

```java
public MyClass{
    public void writeText(String text1, String text2) {
        System.out.print(text1);    // read value of text1 parameter.
        System.out.print(text2);    // read value of text2 parameter.

        text1 = "new value 1";      // change value of text1
        text2 = "new value 2";      // change value of text2
    }
}
```

Final Parameters

The value of a final parameter cannot be changed. That is, if the parameter is a reference to an object, the reference cannot be changed,

```java
public void writeText(final String text1, final String text2) {
    System.out.print(text1);    // read value of text1 parameter.
    System.out.print(text2);    // read value of text2 parameter.

    text1 = "new value 1";      // error
    text2 = "new value 2";      // error
}
```

#### access modifier

same java field access Modifiers  `private, protected, package, public`

#### throws Exception
If an error occurs inside a method, the method may throw an exception.

Exceptions have to be declared in the method declaration, like this (marked in bold):

```java
public String concat(String string1, String string2) throws MyException {

    if(string1 == null) {
        throw new MyException("string1 was null");
    }
    if(string2 == null) {
        throw new MyException("string2 was null");
    }

    return string1 + string2;
}
```

#### return type

任何数据类型，若不需要返回，可定义为Void

### constructors

constructor name is same class name

constructor access modifies like public

Constructor Overloading

```java
public class MyClass {
    private int number = 0;

    public MyClass() {
    }
    public MyClass(int theNumber) {
        this.number = theNumber;
    }
}
```

Default, no-arg Constructor


Calling a Constructor From a Constructor
```java
public class Employee {
    private String firstName = null;
    private String lastName  = null;
    private int    birthYear = 0;

    public Employee(String first,
        String last,
        int    year   ) {

        firstName = first;
        lastName  = last;
        birthYear = year;
    }

    public Employee(String first, String last){
        this(first, last, -1);
    }
}
```

##  5.Java Packages

Java packages are always written in lowercase letters
