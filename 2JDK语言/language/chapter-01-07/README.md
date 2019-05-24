# chapter-01-07

Java Annotation

Java annotations are used to provide meta data for your Java code. 
Being meta data, Java annotations do not directly affect the execution of your code, 
although some types of annotations can actually be used for that purpose.

## Java Annotation Purposes
Java annotations are typically used for the following purposes:

* Compiler instructions
* Build-time instructions
* Runtime instructions


### Compiler instructions
Annotation syntax

		Annotation
		@Entity

		Annotation Elements
		@Entity(tableName = "vehicles")
		@Entity(tableName = "vehicles", primaryKey = "id")
		Annotation Placement
You can place Java annotations above **classes, interfaces, methods, method parameters, fields and local variables**. 

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
It is possible to create your own (custom) Java annotations. Annotations are defined in their own file, just like a Java class or interface

```
@interface MyAnnotation {

    String   value() default "";
    String   name();
    int      age();
    String[] newNames();
}
```

Notice the @interface keyword. This signals to the Java compiler that this is a Java annotation definition

To use the above annotation

```
@MyAnnotation(
    name="Jakob",
    age=37,
    newNames={"Jenkov", "Peterson"}
)
public class MyClass {

}

```

custom Annotation can use
1. @Retention
2. @Target
3. @Inherited
4. @Documented



## @Retention
You can specify for your custom annotation if it should be available at runtime, for inspection via reflection. You do so by annotating your annotation definition with the @Retention annotation. Here is how that is done:

```
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

@interface MyAnnotation {

    String   value() default "";
}
```

RetentionPolicy.RUNTIME

Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively.


* RetentionPolicy.SOURCE
* RetentionPolicy.CLASS
* RetentionPolicy.RUNTIME



## @Target
You can specify which Java elements your custom annotation can be used to annotate
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

```
@Target({ElementType.METHOD})
public @interface MyAnnotation {

    String   value();
}
```

The ElementType class contains the following possible targets:

* ElementType.ANNOTATION_TYPE
* ElementType.CONSTRUCTOR
* ElementType.FIELD
* ElementType.LOCAL_VARIABLE
* ElementType.METHOD
* ElementType.PACKAGE
* ElementType.PARAMETER
* ElementType.TYPE

## @Inherited
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

## @Documented
自定义注释也可也在Java Doc中看到

```
import java.lang.annotation.Documented;

@Documented
public @interface MyAnnotation {

}
@MyAnnotation
public class MySuperClass { ... }
```

When generating JavaDoc for the MySuperClass class, the @MyAnnotation is now included in the JavaDoc.
You will not use the @Documented annotation often, but now you know it exists, if you should need it.
