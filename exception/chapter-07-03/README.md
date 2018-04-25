# Exception


		Exception Hierarchies
		Checked or Unchecked Exceptions?
		Exception Wrapping
		Fail Safe Exception Handling
		Validation - Throw Exceptions Early
		logging Exceptions: Where to Log Exceptions?



It is a good idea to create a base exception for your API or application, and have all other exceptions subclass this base exception. 

Individual subclasses makes it possible (but not obligatory) to catch and handle these individual exceptions differently. 

You should create individual exceptions only for errors that can actually be handled differently.

```
public class MyException extends Exception{
    //constructors etc.
}
```

## Checked or Unchecked Exceptions?

* Checked exceptions must be explicitly caught or propagated as described in Basic try-catch-finally Exception Handling. Unchecked exceptions do not have this requirement. They don't have to be caught or declared thrown.

* Checked exceptions in Java extend the java.lang.Exception class. Unchecked exceptions extend the java.lang.RuntimeException.

我对你的建议是只使用检查的异常或只使用未检查的异常。混合异常类型通常会导致混淆和不一致的使用

### Checked  Exceptions

```
public class MyCheckException extends Exception {
    public MyCheckException(String message) {
        super(message);
    }
}


public class MyUseCheckException {

    public static String testException(Boolean bException) throws MyCheckException{
        if (bException) {
            throw new MyCheckException("MyCheckException throw Exception");
        };
        return "test";
    }

    public static void main(String[] args) {
        try {
            MyUseCheckException.testException(Boolean.TRUE);
        }catch (MyCheckException e) {
            System.out.println(e.getMessage());
        }
    }
}

```

### Unchecked Exceptions

```
public class MyUnCheckException extends RuntimeException {
    public MyUnCheckException(String message) {
        super(message);
    }
}


public class MyUseUnCheckException {

    public static String testException(Boolean bException){
        if (bException) {
            throw new MyUnCheckException("MyUnCheckException throw Exception");
        };
        return "test";
    }

    public static void main(String[] args) {
        MyUseUnCheckException.testException(Boolean.TRUE);


    }
}

```

Notice how the testException() method no longer declares that it throws MyUnCheckException

The MyUseUnCheckException main() method doesn't have to catch the BadUrlException either

and it no longer has to declare that it propagates the exception.


## Exception Wrapping
		What is Exception Wrapping?
		Why Use Exception Wrapping?


### What is Exception Wrapping?

Exception wrapping is wrapping is when you catch an exception, wrap it in a different exception and throw that exception. Here is an example:

```

    try{
        dao.readPerson();
    } catch (SQLException sqlException) {
        throw new MyException("error text", sqlException);
    }
```

### Why Use Exception Wrapping?

人们使用异常包装的主要原因是阻止代码进一步向上传播，不必知道系统中每个可能的异常。

上层组件不必知道下层组件异常细节，比如DAO层，负责提供数据，而数据从不同的地方读取时对应异常

数据库，Web Service及file中读取，则对应抛出SQLException, RemoteException,IOException

我们可以定义DaoException 来包装SQLException, RemoteException,IOException统一来处理异常


## Fail Safe Exception Handling

如果在catch或finally块内引发异常，则此异常可能会隐藏该块捕获的异常。 试图确定错误的原因时，这是误导性的。

```
  InputStream input = null;

  try{

    input = new FileInputStream("myFile.txt");

    //do something with the stream

  } catch(IOException e){
    throw new WrapperException(e);
  } finally {
    try{
     input.close();
    } catch(IOException e){
       throw new WrapperException(e);
    }
  }

```

FileInputStream constructor throws a FileNotFoundException, and NullPointerException thrown from the finally block

将IOException ， NullPointerException warppering WrapperException

```
    try{
     if(input != null) input.close();
    } catch(IOException e){  //second catch block
       throw new WrapperException(e);
    }
```

## Validation - Throw Exceptions Early

验证 - 提早抛出异常

For instance, in a DAO method that inserts a user and an address into two different tables in a database, do like this:

```
  check if user already exists
  validate user
  validate address

  insert user
  insert address
```
Do not do like this:

```
  check if user already exists
  validate user
  insert user

  validate address
  insert address

```
## logging Exceptions: Where to Log Exceptions?

记录异常：在哪里记录异常？

When designing the logging of an application the question often arise: Where in the code should the exceptions be logged? Basically you have three different options:

* Bottom Level Logging
> Logging in the component where the exception occurs

* Mid Level Logging
> Logging somewhere in the middle of the call stack, where sufficient information is available (the context of the component call)

* Top Level Logging
> Logging centrally at the top of the call stack

我建议尽可能使用顶级日志记录，因为它最容易编码和维护，并且如果您不想在开始时对它感到困扰，也可以在开发过程中稍后添加。 您可能会遇到必须在较低级别捕获和处理异常的情况，但这些情况在我的经验中不会经常发生

