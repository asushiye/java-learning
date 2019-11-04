# 8类型信息

		何为类型信息
		类型信息如何产生
		类对象 - Class对象
			类对象产生
			  通过创建对象来产生Class对象
			  通过Class.forName()来产生Class对象
			  通过类字面常量来产生Class对象
			  Class对象，如何触发初始化
			类对象操作
				 获取类的类型信息
				 获取父类的类型信息
			泛型类对象


## 何为类型信息

java程序运行时由对象组成，那么我们是如何知道运行时对象的类型信息的呢？

java提供了运行时类型信息。这些类型信息，通过 **类的类对象(Class对象)** 这种特殊对象来表示

Class对象包含类有关的信息，比如 所有方法，接口，成员信息。

## 类型信息如何产生

每当编写并且编译一个新类时，就会产生一个Class对象，被保存在.class文件中

当需要运行并创建类的对象时，Java虚拟机通过使用**类加载器子系统**来加载class文件，

并验证class文件是否完整，验证通过后就可创建对象，

也就说再编译时，就会生成Class对象，并保存在.class文件中，如果我们想获取Class对象，必须通过类加载器加载到JVM中


关于 **类加载器子系统** 将在深入Java虚拟机 详解

## 类对象 - Class对象

### 类对象产生

关于Class对象产生，我们通过下面实例来理解

* 通过创建对象来产生Class对象
* 通过Class.forName()来产生Class对象
* 通过类字面常量来产生Class对象
* Class对象，如何触发初始化

#### 通过创建对象来产生Class对象
```java
public class Candy {
    static {
        System.out.println("Loading Candy");
    }

    public Candy() {
        System.out.println("Construct Candy");
    }
}
```

JVM加载类成功后，就会自动调用static {} 方法，通过这个方法来确定什么时候产生类对象的

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        new Candy();
        System.out.println("after new Candy()");
    }
}
```

输出
```
before new Candy()
Loading Candy    先输出，说明类先被加载
Construct Candy
after new Candy()
```

#### 通过Class.forName()来产生Class对象

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        try{
            Class clazz = Class.forName("rtti.Candy");
        }catch(ClassNotFoundException e){
            System.out.println("Candy ClassNotFoundException");
        }
        System.out.println("after new Candy()");
    }
}
```

```
before new Candy()
Loading Candy
after new Candy()
```

通过`Class.forName(rtti.Candy)` 方法获取Candy的Class对象，参数必须是完整的包名

#### 通过类字面常量来产生Class对象

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        Class clazz= Candy.class;
        System.out.println(clazz.getName());
        System.out.println("after new Candy()");
    }
}
```

```
before new Candy()
rtti.Candy
after new Candy()
```

通过类字面常量来获取Class对象，虽然没有触发static的执行,但是使用更加简单和安全，因此它会在编译时就做检查

类字面常量不仅可以用于普通的类，也可以用于基本数据类型，接口及数组。

对应基本类型的包装类是没有.class，但是提供.TYPE

```java
        Class intClass = int.class;
        Class IntegerClass = Integer.TYPE;
        intClass = IntegerClass;   //基本类型和对应包装类型等价的
```

所有基本类型和对应包装类型，对应获取类字面常量是等价的

我们推荐使用类字面常量来获取类对象，但是.class 它不会自动地初始化该Class对象，

这样设计有什么好处呢？，初始化有效的实现了**惰性**

#### Class对象，如何触发初始化

调整Candy代码，新增常量，静态变量，静态方法
```java
public class Candy {
    static final int id= 0;
    static int count= 0;
    static void print(){
        System.out.println("Print Candy");
    }
    static {
        System.out.println("Loading Candy");
    }

    public Candy() {
        System.out.println("Construct Candy");
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        System.out.println(Candy.id);
        // System.out.println(Candy.count);
        // Candy.print();
        System.out.println("after new Candy()");
    }
}
```

```
before new Candy()
0
after new Candy()
```

成功输出 id常量值，但是并没有打印出Loading Candy，说明对应编译器常量，是不会进行类加载的

当我们调用类的静态变量Candy.count 和静态方法 Candy.print()时，都打印出Loading Candy，

说明这时已经进行类加载，并进行初始化工作

### 类对象操作

* 获取类的类型信息
* 获取父类的类型信息

#### 获取类的类型信息

给Candy新增游泳和开车的能力接口

```java
public class Candy implements Swimable, Driverable {
	  private String name = "suzhenyun";
    static final int id= 0;
    static int count= 0;
    static void print(){
        System.out.println("Print Candy");
    }
    static {
        System.out.println("Loading Candy");
    }

    public Candy() {
        System.out.println("Construct Candy");
    }

    @Override
    public void drive() {
        System.out.println("Candy can drivering");
    }

    @Override
    public void swim() {
        System.out.println("Candy can swimming");
    }
}
```

```java
    public static void getRtti() {
        Class clazz = Candy.class;
        System.out.println("Class clazz = Candy.class");
        System.out.println("clazz.getName(): "+clazz.getName());
        System.out.println("clazz.getSimpleName(): "+clazz.getSimpleName());
        System.out.println("clazz.toString(): "+clazz.toString());
        System.out.println("clazz.getSuperclass(): "+clazz.getSuperclass());
        for (Class inter: clazz.getInterfaces()){
            System.out.println("Interface: "+inter.getName());
        }
        for (Constructor con: clazz.getConstructors()){
            System.out.println("Constructor: "+con.getName());
        }

        for(Field field: clazz.getDeclaredFields()){
            System.out.println("Field: "+field.getName());
        }
        for(Method method: clazz.getMethods()){
            System.out.println("Method: "+method.getName());
        }
    }
```

输出
```
Class clazz = Candy.class
clazz.getName(): rtti.Candy
clazz.getSimpleName(): Candy
clazz.toString(): class rtti.Candy
clazz.getSuperclass(): class java.lang.Object  默认父类为Object
Interface: rtti.Swimable
Interface: rtti.Driverable
Constructor: rtti.Candy
Field: name
Field: id
Field: count
Method: swim
Method: drive
Method: wait
Method: wait
Method: wait
Method: equals
Method: toString
Method: hashCode
Method: getClass
Method: notify
Method: notifyAll
```


#### 获取父类的类型信息

```java
public class SuperCandy {
    void call(){
        System.out.println("SuperCandy call");
    };
}

public class Candy extends SuperCandy implements Swimable, Driverable {}
```


```java
public static void getSuperRtti() {
        System.out.println("Class clazz = Candy.class");
        Class clazz = Candy.class;
        Class superClazz = clazz.getSuperclass();
        try{
            SuperCandy candy = (SuperCandy)superClazz.newInstance();
            candy.call();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
```

```
Class clazz = Candy.class
SuperCandy call
```

从clazz.getSuperclass() 获取Candy父类的类对象，赋值给定义Class变量,

newInstance()返回Object类型，需要强制转换为SuperCandy

单纯使用Class `Class clazz = Candy.class;` 是不能够在编译时检查出类型错误

通过定义泛型类变量Class<SuperCandy>

### 泛型类对象

* 使用通配符定义类对象

#### 使用通配符定义类对象

使用通配符 **?** 来接收任何类的类对象
```java
    public static void getRttiDD() {
        System.out.println("Class clazz = Candy.class");
        Class<?> clazz = Candy.class;
        clazz = clazz.getSuperclass();
        try{
            SuperCandy candy = (SuperCandy)clazz.newInstance();
            candy.call();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
```

#### 使用使用通配符与extends关键字定义类对象

```java
		Class<? extends SuperCandy> clazz = Candy.class;
```
类对象变量 clazz 可以引用从SuperCandy继承下所有的Class对象

#### 使用使用通配符与super关键字定义类对象

```java
    Class<Candy> clazz = Candy.class;
		Class<SuperCandy> clazzSuper =  clazz.getSuperclass(); // 报错
    Class<? super Candy> clazzSuper = clazz.getSuperclass();
```

若已经指定泛型 Class<Candy>， 则在获取父类的类对象时，不同使用Class<SuperCandy>接收，否则报错

则是因为编译已经知道是Candy的父类，但是并不知道Candy父类是谁，所以使用Class<? super Candy> 来获取
