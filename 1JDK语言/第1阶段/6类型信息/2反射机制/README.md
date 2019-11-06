# 2反射机制
		反射机制概念
			RTTI不支持的场景
			反射机制支持的场景
		从类对象中获取反射类库
		反射机制实现
			构建函数 - Constructor
			方法调用 - Method
			成员属性设置 - Field
			注解访问 - Annotation
			泛型的反射 - Generics
		下一步行动

## 反射机制概念

### RTTI不支持的场景

RTTI运行时类型信息使我们能在运行时获取类对象进而获取到类型的运行信息。

有个前提条件：
`必须在运行前，编译时就已经知道这个类型，也就是说编译时能够打开，检查.class文件`

但是在某些特定场景.class文件不在本地，在编译时是不可获取的，

只能在运行时，通过读取项目外目录或通过网络来获取.class文件，再通过jvm动态加载来获取类对象，

再通过类对象和反射功能来实现反射机制

### 反射机制支持的场景

最典型RTTI应用场景
```
提供界面开发的IDE，用于处理图形用户界面(GUI)
通过拖拉控件来布局，通过设置控件属性来读取和写入类的属性，通过暴露控件事件来执行类的方法。
这些都是通过获取类对象及反射机制来实现的。
```

另外一种典型应用场景
```
提供在跨网络的远程平台上创建和运行对象能力，这称为远程方法调用RMI

1 将大量计算的任务分解，为了提供运算能力，将任务分解为小任务，并分布到多台机器上一起计算
2 应用C/S结构的应用，将客户端特殊任务，分发布到服务器来执行
```

CLass类与java.lang.reflect类库一起实现反射机制。

## 从类对象中获取反射类库

上一节，我们已经学习了类对象，现在学习下反射中相关类库

从类对象中可以获取到如下反射类库
```
		Class Name
		Class Modifies (public, private, synchronized etc.)
		Package Info
		Superclass
		Implemented Interfaces
		Constructors
		Methods
		Fields
		Annotations
```
Package Info `Package package = myObjectClass.getPackage();`
Superclass `Class superclass = myObjectClass.getSuperclass();`
Implemented Interfaces `Class[] interfaces = myObjectClass.getInterfaces();`
Constructors `Constructor[] constructors = myObjectClass.getConstructors();`
Methods `Method[] method = myObjectClass.getMethods();`
Fields `Field[] method = myObjectClass.getFields();`
Annotations `Annotation[] annotations = myObjectClass.getAnnotations();`


请参考 1类对象->类对象操作->获取类的类型信息/获取父类的类型信息

类的访问修饰符如何获取 `public，private，protected, static,final, abstract`等等

```java
    int modifiers = myObjectClass.getModifiers();
    Modifier.isAbstract(int modifiers)
    Modifier.isFinal(int modifiers)
    Modifier.isInterface(int modifiers)
    Modifier.isNative(int modifiers)
    Modifier.isPrivate(int modifiers)
    Modifier.isProtected(int modifiers)
    Modifier.isPublic(int modifiers)
    Modifier.isStatic(int modifiers)
    Modifier.isStrict(int modifiers)
    Modifier.isSynchronized(int modifiers)
    Modifier.isTransient(int modifiers)
    Modifier.isVolatile(int modifiers)
```

不同的访问修饰符java.lang.reflect.Modifier被定义为常量,
```java
		public static final int PUBLIC           = 0x00000001;

    public static boolean isPublic(int mod) {
        return (mod & PUBLIC) != 0;
    }
```

## 反射机制实现

* 构建函数 - Constructor
* 方法调用 - Method
* 成员属性设置 - field


### 构建函数 - Constructor

定义Animal类
```java
package reflection;
public class Animal {
    private String name;
    private Integer age;
    public Animal(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
    public Animal() {
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

```

```java
public class Test {

    public static void main(String[] args) {
        constructor();
    }

    public static void constructor() {
        Class<Animal> clazz = Animal.class;
        for (Constructor constructor: clazz.getConstructors()){
            System.out.println(constructor);
            for(Class param: constructor.getParameterTypes()){
                System.out.println(constructor.getName()+" param "+param);
            }
        }
        try{
            Constructor defaultCons = clazz.getConstructor();
            Animal animal1 = (Animal)defaultCons.newInstance();
            System.out.println(animal1);

            Constructor parmCons = clazz.getConstructor(String.class, Integer.class);
            Animal animal2 = (Animal)parmCons.newInstance("zhenyun", 30);
            System.out.println(animal2);

            Animal animal3= clazz.newInstance();   //由于clazz使用泛型定义，不用强制转换
            System.out.println(animal3);
        }catch(Exception e){
            System.out.println("constructor Exception "+e.getMessage());
        }
    }
}
```

### 方法调用 - Method

新增print方法
```java
public class Animal {
    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public Boolean print(String value, Integer qty){
        System.out.println("value: "+value+"; qty: "+qty);
        return true;
    }
}
```

下面通过反射机制调用toString() 和 print()方法

```java
public class Test {

    public static void main(String[] args) {
//        constructor();
        method();
    }

    public static void method() {
        Class<Animal> clazz = Animal.class;
        for(Method method: clazz.getMethods()){
            if (method.getName().contains("toString")||method.getName().contains("print")){
                System.out.println(method);
                for(Class<?> param: method.getParameterTypes()){
                    System.out.println(param);
                }
            }
        }
        try{
            Animal animal = clazz.newInstance();
            Method toString = clazz.getMethod("toString");
            String str= (String)toString.invoke(animal);
            System.out.println(str);

            Method print = clazz.getMethod("print", String.class, Integer.class);
            Boolean isSuccess= (Boolean)print.invoke(animal,"zhenyun.su", 1000);
            System.out.println(isSuccess);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
```

 输出如下：
 ```
public java.lang.String reflection.Animal.toString()
public java.lang.Boolean reflection.Animal.print(java.lang.String,java.lang.Integer)
class java.lang.String
class java.lang.Integer
Animal{name='null', age=null}
value: zhenyun.su; qty: 1000
true
 ```

### 成员属性设置 - field

```java
package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */

public class Test {

    public static void main(String[] args) {
//        constructor();
//        method();
        field();
    }
    public static void field() {
        Class<Animal> clazz = Animal.class;
        System.out.println("clazz.getDeclaredFields()");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field);
        }
        System.out.println("clazz.getFields()");
        for (Field field : clazz.getFields()) {
            System.out.println(field);
        }

        try{
            Animal animal = clazz.getConstructor(String.class, Integer.class)
                    .newInstance("zhenyun.su", 1000);
            System.out.println(animal);
            Field name = clazz.getDeclaredField("name");
						System.out.println(name.get(animal));
            name.set(animal,"asushiye");
            System.out.println(animal);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
```

getDeclaredFields返回所有成员，不管访问修饰符，是public，private还是其他的

getFields 只返回public的成员
```
clazz.getDeclaredFields()
private java.lang.String reflection.Animal.name
private java.lang.Integer reflection.Animal.age
clazz.getFields()
Animal{name='zhenyun.su', age=1000}

java.lang.IllegalAccessException: Class reflection.Test can not access a member of class reflection.Animal with modifiers "private"
```
报错是因为 成员name是私有的，不能进行读取也不能写入

若要访问成员，有三种方式，

#### 一种通过给成员添加读写器，然后通过调用方法来实现读取和写入

#### 另外一种，修改成员的访问修饰符
```java
public class Animal {
    public String name;
}
```

#### 第三种，动态修改成员的访问权限
```java
    Field name = clazz.getDeclaredField("name");
    name.setAccessible(true);
    System.out.println(name.get(animal));
    name.set(animal,"asushiye");
```

通过 setAccessible 设置访问权限，提示**动态修改访问权限，也适用方法**

重新运行,就可以正常输出
```
clazz.getDeclaredFields()
public java.lang.String reflection.Animal.name
private java.lang.Integer reflection.Animal.age
clazz.getFields()
public java.lang.String reflection.Animal.name
Animal{name='zhenyun.su', age=1000}
zhenyun.su
Animal{name='asushiye', age=1000}
```

在访问成员时，建议使用getField()方法来获取public的成员，若不在，则报Field 不存在异常

### 注解访问 - Annotation

下从类对象中，获取类注解，成员注解，方法注解，参数注解的内容信息。

#### 创建注解
```java
package reflection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyTypeAnnotation {
    String name();
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyFieldAnnotation {
    String name();
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface MyMethodAnnotation {
    String name();
    String value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface MyParameterAnnotation {
    String name();
    String value();
}
```

#### 创建动物并添加注解
```java
package reflection.annotation;
/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-05
 */
@MyTypeAnnotation(name="myType", value="class")
public class AnimalAnnotation {
    @MyFieldAnnotation(name="myField", value="field")
    private String name;
    private Integer age;

    public AnimalAnnotation(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public AnimalAnnotation() {
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @MyMethodAnnotation(name="myMethod", value="method")
    public Boolean print(@MyParameterAnnotation(name="myParam", value="param") String value, Integer qty){
        System.out.println("value: "+value+"; qty: "+qty);
        return true;
    }
}
```

```java
package reflection;

public class Test {

    public static void main(String[] args) {
//        constructor();
//        method();
//        field();
        annotation();
    }

		//循环输出注解信息，并强制转换对应注解类型以访问注解成员
    public static void printAnnotation(Annotation[] annotations){
        for (Annotation annotation: annotations){
            System.out.println(annotation);
            if (annotation instanceof MyTypeAnnotation){
                String name = ((MyTypeAnnotation) annotation).name();
                String value = ((MyTypeAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
            if (annotation instanceof MyFieldAnnotation){
                String name = ((MyFieldAnnotation) annotation).name();
                String value = ((MyFieldAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
            if (annotation instanceof MyMethodAnnotation){
                String name = ((MyMethodAnnotation) annotation).name();
                String value = ((MyMethodAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
            if (annotation instanceof MyParameterAnnotation){
                String name = ((MyParameterAnnotation) annotation).name();
                String value = ((MyParameterAnnotation) annotation).value();
                System.out.println("name: "+name+"; value: "+value);
            };
        }
    }

    public static void annotation() {
        Class<AnimalAnnotation> clazz = AnimalAnnotation.class;
        printAnnotation(clazz.getAnnotations()); //从类对象获取注解数组

        try{
            Field name = clazz.getDeclaredField("name");
            printAnnotation(name.getAnnotations()); //从成员获取注解数组

            Method print = clazz.getMethod("print", String.class, Integer.class);
            printAnnotation(print.getAnnotations()); //从方法获取注解数组

            for(Annotation[] annotations: print.getParameterAnnotations()){ //从方法获取参数的注解数组
                printAnnotation(annotations);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
```

通过类对象，可以很容易获取到注解内容，我们可以更加注解能，编写代码逻辑。

### 通过反射获取泛型类型 - Generics

下面通过List泛型成员，来读取泛型参数类型

在Animal添加泛型成员
```java
public class Animal {
    private String name;
    private Integer age;
    private List<String> members;

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
```

```java
public class Test {

    public static void main(String[] args) {
//        constructor();
//        method();
//        field();
//        annotation();
        generic();
    }

		//输出成员类型中泛型的参数类型
    public static void printGeneric(Type generiType){ ;
        System.out.println("type: "+ generiType);
        if (generiType instanceof ParameterizedType){
            ParameterizedType pType = (ParameterizedType)generiType;
            for(Type aType: pType.getActualTypeArguments()){
                System.out.println("generi type: "+aType);
            }
        }
    }

    public static void generic() {
        Class<Animal> clazz = Animal.class;
        try{
            System.out.println("1 Field.getGenericType---------------");
            Field members = clazz.getDeclaredField("members");
            System.out.println("field: "+members);
//            printGeneric(members.getType());
            printGeneric(members.getGenericType());

            System.out.println("2 Method.getGenericReturnType---------------");
            Method getMembers = clazz.getMethod("getMembers");
            System.out.println("method: "+getMembers);
//            printGeneric(getMembers.getReturnType());
            printGeneric(getMembers.getGenericReturnType());

            System.out.println("3 Method.getGenericParameterTypes---------------");
            Method setMembers = clazz.getMethod("setMembers", List.class);
            System.out.println("method: "+ setMembers);
//            System.out.println(setMembers.getParameterTypes());
            for(Type paramType: setMembers.getGenericParameterTypes()){
                printGeneric(paramType);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
```

输出
```
1 Field.getGenericType---------------
field: private java.util.List reflection.Animal.members
type: java.util.List<java.lang.String>
generi type: class java.lang.String
2 Method.getGenericReturnType---------------
method: public java.util.List reflection.Animal.getMembers()
type: java.util.List<java.lang.String>
generi type: class java.lang.String
3 Method.getGenericParameterTypes---------------
method: public void reflection.Animal.setMembers(java.util.List)
type: java.util.List<java.lang.String>
generi type: class java.lang.String
```

## 下一步行动

使用反射机制 可以很好实现动态代理及远程代理调用，将在后面章节介绍
