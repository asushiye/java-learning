# 8个匿名函数
    Why Lambda
			define interface
			interface implementation
		Lambda syntax
			Matching Lambdas to Interfaces
			Lambda Parameters
				Zero Parameters
				One Parameter
				Multiple Parameters
				Parameter Types
			Lambda Function Body
			Returning a Value From a Lambda Expression
			Lambdas as Objects

## Why Lambda

java8特性

### define interface
Java Lambdas and the Single Method Interface
```java
public interface LambdaService {
    public void printService();
}

public class LambdaController {
    public void addService (LambdaService lambdaService){
        lambdaService.printService();
    };
}
```

### interface implementation

#### In Java 7

In Java 7 you could add an event listener using an anonymous interface implementation, like this:

```java
public class MyLambda {
    public static void main(String[] args){

        LambdaController lambdaController = new LambdaController();
        lambdaController.addService(new LambdaService(){
            public void printService(){
                System.out.println("In Java 7 you would have to implement this interface ");
            };
        });

    }
}
```

#### In Java 8

In Java 8 you can add an event listener using a Java lambda expression, like this:

```java
public class MyLambda {
    public static void main(String[] args){

        LambdaController lambdaController = new LambdaController();
        lambdaController.addService(()->System.out.println("In java 8 using a Java lambda expression "));
    }
}
```

The lambda expression is matched against the parameter type of the addStateListener() method's parameter.

If the lambda expression matches the parameter type (in this case the StateChangeListener interface) ,

then the lambda expression is turned into a function that implements the same interface as that parameter.

lambda极大简化代码。也让策略模式更加简单

## Lambda syntax

### Matching Lambdas to Interfaces

A single method interface is also sometimes referred to as a functional interface.

Matching a Java lambda expression against a functional interface is divided into these steps:

* Does the interface have only one method?
* 接口是否只有一个方法
* Does the parameters of the lambda expression match the parameters of the single method?
* 方法的参数是否和lambda参数匹配
* Does the return type of the lambda expression match the return type of the single method?
* 方法的返回值和lambda返回值匹配

### Zero Parameters

If the method you are matching your lambda expression against takes no parameters,

then you can write your lambda expression like this:

`() -> System.out.println("Zero parameter lambda");`

### One Parameter

`param -> System.out.println("One parameter: " + param);`

### Multiple Parameters

`(p1, p2) -> System.out.println("Multiple parameters: " + p1 + ", " + p2);`

### Parameter Types

`(Car car) -> System.out.println("The car is: " + car.getName());`

### Lambda Function Body

```java
  (oldState, newState) -> System.out.println("State changed")

  (oldState, newState) -> {
    System.out.println("Old state: " + oldState);
    System.out.println("New state: " + newState);
  }
```

多个语句使用花括号{}

### Returning a Value From a Lambda Expression

```java
(param) -> {
    System.out.println("param: " + param);
    return "return value";
  }

(a1, a2) -> { return a1 > a2; }

(a1, a2) -> a1 > a2;
```

### Lambdas as Objects

You can assign a lambda expression to a variable and pass it around, like you do with any other object

```java
public interface MyComparator {
    public boolean compare(int a1, int a2);
}

public class MyLambda {
    public static void main(String[] args){

        MyComparator myComparator = (a1, a2) ->{return a1 > a2; } ;
        boolean result = myComparator.compare(2, 5);
        System.out.println(result);
    }
}
```
