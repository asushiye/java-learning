# 3动态代理
		代理概念
			什么是代理
			java是如何实现代理
		代理的实现
			使用代理设计模式
			使用反射机制 - Proxy类
		下一步行动

## 代理概念

### 什么是代理
```
代理技术是整个java技术中最重要的一个技术，它是学习java框架的基础，
了解动态代理技术，有利学习Spring这些框架
```
**代理技术**就是用来产生一个对象的代理对象的。

在开发中为什么需要为一个对象产生代理对象呢？

举一个现实生活中的例子：
```
明星都有一个自己的经纪人，这个经纪人就是他们的代理人，
当我们需要找明星做演出时，不能直接找到该明星，只能是找明星的代理人。
这个代理人存在的价值就是拦截我们对明细的直接访问！
```

java中代理和这个例子一样的，

**代理目的** 通过产生一个对象的代理对象，来拦截直接对真实业务对象的访问。

那么代理对象应该具有什么方法呢？代理对象应该具有和目标对象相同的方法

所以在这里明确代理对象的两个概念：
1、代理对象存在的价值主要用于拦截对真实业务对象的访问。
2、代理对象应该具有和目标对象(真实业务对象)相同的方法。

### java是如何实现代理

java有两种方法来实现代理功能，一种利用代理设计模式，另外一种反射机制

java使用反射机制，可以在运行时创建接口的动态实现，进而实现动态代理功能

您可以使用类java.lang.reflect.Proxy进行操作。

动态代理可以应用在如下场景

`例如， 数据库连接和事务管理，用于单元测试的动态模拟对象，以及其他类AOP方法拦截目的。`

## 代理的实现

下面为刘德华实现一个经纪人

* 使用代理设计模式
* 使用反射机制 - Proxy类

### 代理设计模式

#### 定义业务对象类
```java
public interface Person {
    String sing(String name);
    String dance(String name);
}

public class LiuDeHua implements Person {
    @Override
    public String sing(String name) {
        System.out.println("请刘德华出场唱"+name+"歌");
        return "歌已唱，谢谢大家！";
    }

    @Override
    public String dance(String name) {
        System.out.println("请刘德华出场跳"+name+" 舞");
        return "舞蹈跳完，谢谢大家！";
    }
}
```

#### 定义简单代理类
```java
public class LiuDeHuaSimpleProxy implements Person {
    private Person person;

    public LiuDeHuaSimpleProxy(Person person) {
        this.person = person;
    }
    public static Person getPersonProxy(Person person){
        return new LiuDeHuaSimpleProxy(person);
    }

    @Override
    public String sing(String name) {
        System.out.println("我是他的经纪人，要找他唱歌，先交10万");
        return person.sing(name);
    }

    @Override
    public String dance(String name) {
        System.out.println("我是他的经纪人，要找他跳舞，先交20万");
        return person.dance(name);
    }
}
```

#### 编写测试代码
```java
public class PersonProxyTest {
    public static void main(String[] args) {
        simpleProxy();
    }
    public static void simpleProxy() {
//        Person person = new LiuDeHua();
        Person person = LiuDeHuaSimpleProxy.getPersonProxy(new LiuDeHua());
        System.out.println(person.sing("冰雨"));
        System.out.println(person.dance("街舞"));
    }
}
```

输出如下
```
我是他的经纪人，要找他唱歌，先交10万
请刘德华出场唱冰雨歌
歌已唱，谢谢大家！
我是他的经纪人，要找他跳舞，先交20万
请刘德华出场跳街舞 舞
舞蹈跳完，谢谢大家！
```

利用接口来实现代理的设计模式，并没有直接访问LiuDehua,而通过LiuDeHuaSimpleProxy代理类来实现对LiuDehua的访问。

### 使用反射机制 - Proxy类

java提供了`java.lang.reflect.Proxy`类

用于生成某一个对象的代理对象，这个代理对象通常也要编写一个类来生成。

在java中如何用程序去生成一个对象的代理对象呢

#### 实现代理方法

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LdhInvocationHandler implements InvocationHandler {
    private Person person = null;

    public LdhInvocationHandler(Person person) {
        this.person = person;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("sing".equals(method.getName())){
            System.out.println("我是他的经纪人，要找他唱歌，先交10万");
            return method.invoke(person,args);
        }
        if ("dance".equals(method.getName())){
            System.out.println("我是他的经纪人，要找他跳舞，先交20万");
            return method.invoke(person,args);
        }
        return null;
    }
}
```

#### 实现业务类的代理类

```java
public class LiuDeHuaProxy {
    public static Person getPersonProxy(Person person){
        InvocationHandler ldhInvocationHandler = new LdhInvocationHandler(person);
        try {
            return(Person) Proxy.newProxyInstance(
                    LiuDeHuaProxy.class.getClassLoader(),
                    person.getClass().getInterfaces(),
                    ldhInvocationHandler
            );
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
```

#### 编写测试类
```java
package reflection.proxy;

public class PersonProxyTest {
    public static void main(String[] args) {
        // simpleProxy();
       dynamicProxy();
    }
    public static void dynamicProxy() {
        Person person= LiuDeHuaProxy.getPersonProxy(new LiuDeHua());

        String retSing = person.sing("冰雨");
        System.out.println(retSing);
        String retDance = person.dance("街舞");
        System.out.println(retDance);
    }
}
```

方法如下：
`static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) `

newProxyInstance方法用来返回一个代理对象，这个方法总共有3个参数，

* ClassLoader loader用来指明生成代理对象使用哪个类装载器，可以从已经加载的类中获取类装载器
* Class<?>[] interfaces用来指明生成哪个对象的代理对象，通过接口指定，
* InvocationHandler h用来指明产生的这个代理对象要做什么事情。

所以我们只需要调用newProxyInstance方法就可以得到某一个对象的代理对象了。


java提供的动态代理并不是日常使用的工具，但是它可以非常好解决某些类型的问题。


## 下一步行动

使用其他代理框架来更加容易实现动态代理功能，比如Cglib

JDK动态代理和CGLIB字节码生成的区别？

1. JDK动态代理只能对实现了接口的类生成代理，而不能针对类
2. CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法

下一步我们将在学习Spring 切面编程Aop中进一步了解。
