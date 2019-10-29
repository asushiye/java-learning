# Java Reflection - Generics,Arrays, Dynamic Proxies 

chapter-18-05

		Dynamic Proxies 
		Dynamic Class Loading and Reloading
		Modules

## Dynamic Proxies 



### Dynamic Proxies

Using Java Reflection you create dynamic implementations of interfaces at runtime. You do so using the class **java.lang.reflect.Proxy**

 Dynamic proxies can be used for many different purposes, e.g. 
 
 database connection and transaction management,
 
 dynamic mock objects for unit testing, 
 
 and other AOP-like method intercepting purposes.


使用Java Reflection，您可以在运行时创建接口的动态实现。 您可以使用类java.lang.reflect.Proxy进行操作。 这个类的名称是我将这些动态接口实现称为动态代理的原因。 动态代理可以用于许多不同的目的，例如， 数据库连接和事务管理，用于单元测试的动态模拟对象，以及其他类AOP方法拦截目的。
### Creating Proxies


### 

refer to : https://blog.csdn.net/pangqiandou/article/details/52964066

一、代理的概念

动态代理技术是整个java技术中最重要的一个技术，它是学习java框架的基础，不会动态代理技术，那么在学习Spring这些框架时是学不明白的。

动态代理技术就是用来产生一个对象的代理对象的。在开发中为什么需要为一个对象产生代理对象呢？

举一个现实生活中的例子：

明星都有一个自己的经纪人，这个经纪人就是他们的代理人，当我们需要找明星唱歌，跳舞时，不能直接找到该明星，只能是找明星的代理人。

这个代理人存在的价值就是拦截我们对刘德华的直接访问！

这个现实中的例子和我们在开发中是一样的，我们在开发中之所以要产生一个对象的代理对象，主要用于拦截对真实业务对象的访问。

那么代理对象应该具有什么方法呢？代理对象应该具有和目标对象相同的方法

所以在这里明确代理对象的两个概念：
　　　　1、代理对象存在的价值主要用于拦截对真实业务对象的访问。
　　　　2、代理对象应该具有和目标对象(真实业务对象)相同的方法。


二、java中的代理

2.1、"java.lang.reflect.Proxy"类介绍

现在要生成某一个对象的代理对象，这个代理对象通常也要编写一个类来生成，所以首先要编写用于生成代理对象的类。

在java中如何用程序去生成一个对象的代理对象呢


`static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h) `

newProxyInstance方法用来返回一个代理对象，这个方法总共有3个参数，

* ClassLoader loader用来指明生成代理对象使用哪个类装载器，
* Class<?>[] interfaces用来指明生成哪个对象的代理对象，通过接口指定，
* InvocationHandler h用来指明产生的这个代理对象要做什么事情。

所以我们只需要调用newProxyInstance方法就可以得到某一个对象的代理对象了。

2.2、下面我们举个例子

1、定义对象的行为接口

```
public interface Person {
    public String sing(String name);
    public String dance(String name);
}

```

2、定义目标业务对象类

```
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

3、实现代理方法

```
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

4、创建生成代理对象的代理类

```
public class LiuDeHuaProxy {
    private Person ldh = new LiuDeHua();

    public Person getPersonProxy(){
        InvocationHandler ldhInvocationHandler = new LdhInvocationHandler(ldh);
        try {
            return(Person) Proxy.newProxyInstance(LiuDeHuaProxy.class.getClassLoader(), ldh.getClass().getInterfaces(),
                    ldhInvocationHandler);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
```
5、编写测试类

```
public class PersonProxyTest {
    public static void main(String[] args) {
        LiuDeHuaProxy proxy = new LiuDeHuaProxy();

        Person person= proxy.getPersonProxy();
        String retSing = person.sing("冰雨");
        System.out.println(retSing);
        String retDance = person.dance("街舞");
        System.out.println(retDance);
    }
}

```