# 7注解

		What is Annotation
		how to use
		Annotation Purposes
		Creating Your Own Annotations
		有什么用呢？如何实现我想要的逻辑

## what is Annotation

Java annotations are used to provide meta data for your Java code.

Being meta data, Java annotations do not directly affect the execution of your code,

although some types of annotations can actually be used for that purpose.

## how to use

```java
@Entity
@Table(name = "tt_sale_header", schema = "dsale")
public class SaleHeader implements Serializable {
}
```

@Table
```java
package javax.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})   // 用于在class上
@Retention(RetentionPolicy.RUNTIME) //在运行时，提供说明
public @interface Table {
    String name() default "";

    String catalog() default "";

    String schema() default "";

    UniqueConstraint[] uniqueConstraints() default {};

    Index[] indexes() default {};
}
```

## Annotation Purposes
Java annotations are typically used for the following purposes:

* Compiler instructions
* Build-time instructions
* Runtime instructions

### Compiler instructions

You can place Java annotations above **classes, interfaces, methods, method parameters, fields
Annotation syntax

		Annotation
		@Entity

		Annotation Elements
		@Entity(tableName = "vehicles")
		@Entity(tableName = "vehicles", primaryKey = "id")
		Annotation Placement
You can place Java annotations above
**classes, interfaces, methods, method parameters, fields and local variables**.

Here is an example annotation added above a class definition:

```
	@Entity
	public class Vehicle {
	}
```

### Build-time instructions
* @Deprecated
* @Override
* @SuppressWarnings

#### @Deprecated
The @Deprecated annotation is used to mark a class, method or field as deprecated, meaning it should no longer be used

```
@Deprecated
public class MyComponent {
}
```

If your code uses deprecated classes, methods or fields, the compiler will give you a warning
warning MyComponent 已过期

#### @Override
The @Override Java annotation is used above methods that override methods in a superclass.

If the method does not match a method in the superclass, the compiler will give you an error.

#### @SuppressWarnings

The @SuppressWarnings annotation makes the compiler suppress warnings for a given method. For instance,

if a method calls a deprecated method, or makes an insecure type cast, the compiler may generate a warning.

You can suppress these warnings by annotating the method containing the code with the @SuppressWarnings annotation.


## Creating Your Own Annotations
It is possible to create your own (custom) Java annotations.

Annotations are defined in their own file, just like a Java class or interface

### define annotation

```java
public @interface MyAnnotation {
    String   value() default "";
    String   name();
    int      age();
    String[] newNames();
}
```

Notice the @interface keyword. This signals to the Java compiler that this is a Java annotation definition

### To use the above annotation
```java
@MyAnnotation(
    name="Jakob",
    age=37,
    newNames={"Jenkov", "Peterson"}
)
public class MyClass {
}

```

### custom Annotation can use
1. @Retention - jvm是否加载并读取
2. @Target    - 应用类，还是方法，还是成员上
3. @Inherited - 注解可被继承
4. @Documented - 将注解添加到javadoc


#### @Retention
You can specify for your custom annotation if it should be available at runtime, for inspection via reflection. You do so by annotating your annotation definition with the @Retention annotation. Here is how that is done:

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String   value() default "";
}
```

RetentionPolicy.RUNTIME

Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively.

* RetentionPolicy.SOURCE  //注解在编译时就被丢弃， 这个选项不知道有什么用？
* RetentionPolicy.CLASS   //注解在编译时记录在class文件中，在运行时不被加载到JVM中
* RetentionPolicy.RUNTIME //注解在编译时记录在class文件中，在运行时被加载到JVM中，利用反射机制读取


#### @Target
You can specify which Java elements your custom annotation can be used to annotate

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
public @interface MyAnnotation {
    String   value();
}
```

The ElementType class contains the following possible targets:

* ElementType.ANNOTATION_TYPE //注解上
* ElementType.CONSTRUCTOR //构造方法上
* ElementType.FIELD //成员上
* ElementType.LOCAL_VARIABLE  //局部成员上
* ElementType.METHOD //method
* ElementType.PACKAGE  //package
* ElementType.PARAMETER //method 参数上
* ElementType.TYPE   //class

#### @Inherited
自定义注释可以跟随类被继承
java.lang.annotation.Inherited
```
@Inherited
public @interface MyAnnotation {
}

@MyAnnotation
public class MySuperClass { ... }
public class MySubClass extends MySuperClass { ... }
```

#### @Documented
自定义注释也可也在Java Doc中看到
```java
import java.lang.annotation.Documented;

@Documented
public @interface MyAnnotation {

}
@MyAnnotation
public class MySuperClass { ... }
```

When generating JavaDoc for the MySuperClass class,

the @MyAnnotation is now included in the JavaDoc.

You will not use the @Documented annotation often,

but now you know it exists, if you should need it.
