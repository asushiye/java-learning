# Basic try-catch-finally Exception Handling in Java
chapter-07-01

		Exception propagation
		Throwing Exceptions
		Catching Exceptions
		Propagating Exceptions
		Example: Catching IOException's
		Example: Propagating IOException's
		Finally
		Catch or Propagate Exceptions?


## Exception propagation

if a method A calls B, and B calls C then the call stack looks like this:

    A
    B
    C
 When method C returns the call stack only contains A and B. If B then calls the method D, then the call stack looks like this:

    A
    B
    D

Understanding the call stack is important when learning the concept of exception propagation.

Exception are propagated up the call stack, from the method that initially throws it, until a method in the call stack catches it.


## Throwing Exceptions

```
    public static float divide(int numberToDivide, int numberToDivideBy)
            throws Exception {
        if(numberToDivideBy == 0){
            throw new Exception("Cannot divide by 0");
        }
        return numberToDivide / numberToDivideBy;
    }

```

## Catching Exceptions

```
    public static void main(String[] args){
        try {
            MyException.divide(2,0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
```
 
## Propagating Exceptions

如果原来方法已经throw exception，那么在这个方法内部可以不用throw new exception，否则必须throw new exception

```
    public static float divide(int numberToDivide, int numberToDivideBy) throws Exception {
        if(numberToDivideBy == 0){
            throw new Exception("Cannot divide by 0");
        }
        return numberToDivide / numberToDivideBy;
    }

```


## finally Exceptions

```
    public void openFile(){
        FileReader reader = null;
        try {
            reader = new FileReader("someFile");
            int i=0;
            while(i != -1){
                i = reader.read();
                System.out.println((char) i );
            }
        } catch (IOException e) {
            //do something clever with the exception
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    //do something clever with the exception
                }
            }
            System.out.println("--- File End ---");
        }
    }
```


## Catch or Propagate Exceptions?

You might be wondering whether you should catch or propate exceptions thrown in your program. 
It depends on the situation. In many applications you can't really do much about the exception but tell the user that the requested action failed
In these applications you can usually catch all or most exceptions centrally in one of the first methods in the call stack

对应公用方法，一般采用异常传播方式，如果异常需要及时处理，则捕获异常的方式



for example

```

public class MyException {

    public static float divide(int numberToDivide, int numberToDivideBy)
            throws Exception {
        if(numberToDivideBy == 0){
            throw new Exception("Cannot divide by 0");
        }
        return numberToDivide / numberToDivideBy;
    }

    public static void main(String[] args){
        try {
            MyException.divide(2,0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

```