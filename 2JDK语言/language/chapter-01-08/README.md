# chapter-01-08

Java Lambda Expressions

Lambda表达式提供了一个更加简练的函数式语法来写匿名方法.

		Java Lambdas and the Single Method Interface
			Matching Lambdas to Interfaces
		Lambda Type Inference
		Lambda Parameters
			Zero Parameters
			One Parameter
			Multiple Parameters
			Parameter Types
		Lambda Function Body
		Returning a Value From a Lambda Expression
		Lambdas as Objects

## Java Lambdas and the Single Method Interface

lambda expression是一个匿名函数，
使用运算符 ->，该运算符读为“goes to”。

语法如下：
形参列表 -> 函数体
函数体多于一条语句的可用大括号括起。

```
public interface LambdaService {
    public void printService();
}

public class LambdaController {
    public void addService (LambdaService lambdaService){
        lambdaService.printService();
    };
}
```

### In Java 7 you could add an event listener using an anonymous interface implementation, like this:

```
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

### In Java 8 you can add an event listener using a Java lambda expression, like this:

```
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

## Lambda syntax

### Matching Lambdas to Interfaces

A single method interface is also sometimes referred to as a functional interface. Matching a Java lambda expression against a functional interface is divided into these steps:

* Does the interface have only one method?
* Does the parameters of the lambda expression match the parameters of the single method?
* Does the return type of the lambda expression match the return type of the single method?

### Zero Parameters

If the method you are matching your lambda expression against takes no parameters, then you can write your lambda expression like this:

`() -> System.out.println("Zero parameter lambda");`

### One Parameter
`param -> System.out.println("One parameter: " + param);`

### Multiple Parameters
`(p1, p2) -> System.out.println("Multiple parameters: " + p1 + ", " + p2);`

### Parameter Types

`(Car car) -> System.out.println("The car is: " + car.getName());`

### Lambda Function Body

```
 (oldState, newState) -> System.out.println("State changed")


  (oldState, newState) -> {
    System.out.println("Old state: " + oldState);
    System.out.println("New state: " + newState);
  }
```

### Returning a Value From a Lambda Expression

```
(param) -> {
    System.out.println("param: " + param);
    return "return value";
  }

(a1, a2) -> { return a1 > a2; }

(a1, a2) -> a1 > a2;
```

### Lambdas as Objects

You can assign a lambda expression to a variable and pass it around, like you do with any other object

```
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