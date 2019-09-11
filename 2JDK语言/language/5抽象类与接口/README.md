# 5抽象类与接口

    Java Abstract Classes
    Java Interface
    Interfaces vs. Abstract Classes

## Java abstract class
A Java abstract class is a class which cannot be instantiated,

meaning you cannot create new instances of an abstract class.

## The Purpose of Abstract Classes

The purpose of abstract classes is to function as base classes

which can be extended by subclasses to create a full implementation.

For instance, imagine that a certain process requires 3 steps:

1. The step before the action.
2. The action.
3. The step after the action.

```java
public abstract class MyAbstractProcess {

    public void process() {
        stepBefore();
        action();
        stepAfter();
    }

    public void stepBefore() {
        //implementation directly in abstract superclass
    }

    public abstract void action(); // implemented by subclasses

    public void stepAfter() {
        //implementation directly in abstract superclass
    }
}
```

Notice how the action() method is abstract. Subclasses of MyAbstractProcess can now extend MyAbstractProcess and just override the action() method.


## java interface

A Java interface is a bit like a class, except a Java interface can only contain method signatures and field

Java中的接口作为实现多态的一种方式

```java
public interface MyInterface {
    public String hello = "Hello";

    public void sayHello();
}
```

The variable can be accessed directly from the interface

`System.out.println(MyInterface.hello);`

### Implementing an Interface

```java
public class MyInterfaceImpl implements MyInterface {

    public void sayHello() {
        System.out.println(MyInterface.hello);
    }
}
```

A class that implements an interface must **implement all the methods** declared in the interface


Interface Instances

```java
MyInterface myInterface = new MyInterfaceImpl();
myInterface.sayHello();
```


# Interfaces vs. Abstract Classes

接口是暴露公共接口的更灵活的机制

If you need to separate an interface from its implementation, use an interface.

If you also need to provide a base class or default implementation of the interface, add an abstract class (or normal class) that implements the interface.


First the interface:
```java
public interface URLProcessor {

    public void process(URL url) throws IOException;
}

Second, the abstract base class:

public abstract class URLProcessorBase implements URLProcessor {

    public void process(URL url) throws IOException {
        URLConnection urlConnection = url.openConnection();
        InputStream input = urlConnection.getInputStream();

        try{
            processURLData(input);
        } finally {
            input.close();
        }
    }

    protected abstract void processURLData(InputStream input)
        throws IOException;

}
```
Third, the subclass of the abstract base class:

```java
public class URLProcessorImpl extends URLProcessorBase {

    @Override
    protected void processURLData(InputStream input) throws IOException {
        int data = input.read();
        while(data != -1){
            System.out.println((char) data);
            data = input.read();
        }
    }
}
```

Fourth, how to use the interface URLProcessor as variable type, even though it is the subclass UrlProcessorImpl that is instantiated.

```java
URLProcessor urlProcessor = new URLProcessorImpl();
urlProcessor.process(new URL("http://jenkov.com"));
```
Using both an interface and an abstract base class makes your code more flexible.
It possible to implement simple URL processors simply by subclassing the abstract base class.
If you need something more advanced, your URL processor can just implement the URLProcessor interface directly,
and not inherit from URLProcessorBase.
