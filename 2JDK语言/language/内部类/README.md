# 内部类

		什么是内部类
		如何定义内部类
		如何实例化
		静态内部类

## 什么是内部类

一个类A可以嵌套在另外一个类B中，A类称为内部类，则B类为外部类

内部类可以自由的使用外部类中所有的方法和变量，就算是私有的也可以。这就是为什么内部类好用的原因。

## 如何定义内部类

```java
public class MyOuter {
    private int x;

    class MyInner {
        void go() {
            x = 50;  //可以自由使用外部类的实例变量x
            System.out.println(x);
        }
    }
}
```

## 如何实例化

### 直接给外部类时实例变量赋值内部类对象

```java
public class MyOuter {
    private int x;
		private MyInner myInner = new MyInner();

    class MyInner {
        void go() {
            x = 50;  //可以自由使用外部类的实例变量x
            System.out.println(x);
        }
    }
}
```

在`private MyInner myInner = new MyInner();` 在实例化MyOuter实例时，自定义实例化MyInner

### 独立实例化外部类和内部类
内部类的实例一定会绑在外部类的实例上

```java
public class Test {
    public static void main(String[] args) {
        MyOuter myOuter =  new MyOuter();
        MyOuter.MyInner myInnerClass = myOuter.new MyInner();
        myInnerClass.go();
    }
}
```

## 静态内部类

### 静态内部类定义

需要特别注意是，静态内部类是不能直接使用外部类的实例变量
```java
public class MyOuter2 {
    private int x;

    static class MyInner {
        void go() {
            x = 50;   // 错误，不允许使用
            System.out.println(x);
        }
    }
}
```

不过，可以使用外部类的，其他静态变量和静态方法。
```java
public class MyOuter2 {
    private static int x;

    static class MyInner {
        void go() {
            x = 50;   // 可以使用静态变量
            System.out.println(x);
        }
    }
}
```
### 静态内部类实例化

```java
public class MyOuter2 {
    private static int x;
		private MyInner myInner = new MyInner();


    static class MyInner {
        void go() {
            x = 50;   // 可以使用静态变量
            System.out.println(x);
        }
    }
}
```

或者

```java
public class Test {
    public static void main(String[] args) {
        MyOuter2.MyInner myInner = new MyOuter2.MyInner();
        myInner.go();
    }
}
```
