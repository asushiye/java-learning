# 2反射机制

		反射机制概念
			RTTI不支持的场景
			反射机制支持的场景

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

## 反射类库

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

### 获取类对象中反射类库

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

### 反射机制实现


#### 构建函数

```java

```
```java

```
