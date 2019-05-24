# Basic Exception Handling in Java

		Exception propagation  -  异常传播
		Throwing Exceptions    -  抛出异常
		try-catch-finally            -  异常处理语句
			try
			catch Exceptions   -  捕获异常
			catch Multiple Exceptions
			finally Exceptions        -   即使出现异常，一定要执行
		How to release resources after an exception  - 异常以后如何释放资源 ？
			1. Try-Catch-Finally     使用finally强制释放资源
			2. Try-with-resources    try调用 **java.lang.AutoCloseable** 接口来实现释放
		Catch or Propagate Exceptions?   - 异常即使处理还是向上传播异常？
		example

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

**默认情况下，如果方法没有对异常处理，异常一层层往上抛。直到交由JVM处理最终导致进程异常退出。**


## Throwing Exception

```
    public static float divide(int numberToDivide, int numberToDivideBy) throws Exception {
        if(numberToDivideBy == 0){
            throw new Exception("Cannot divide by 0");
        }
        return numberToDivide / numberToDivideBy;
    }

```

` throw new Exception("Cannot divide by 0");` 表示主动抛出异常，后面的代码不在执行，除非finally代码

`try-catch` 区别是，try-catch 在捕获异常后，try语句不在执行，而交给catch执行。


throws Exception放在方法后边,表示本方法不处理异常，交给被调用处处理.

而被调用处必须要处理，处理的方式：

1. 继续使用throws Exception往上抛，或异常停止在本方法中

若使用try catch 进行捕获，并不会捕获到调用方法的异常，除非本身代码出了异常，这个最后的实例中体现。

## try-catch-finally            -  异常处理语句

### try

try子语句用于在执行代码过程中捕获异常，出现异常后，将跳过后面的代码，转由catch语句匹配异常类型或throws 转交给上层方法处理

### catch  exception

```
    public static void main(String[] args){
        try {
            MyException.divide(2,0);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
```

使用该语句进行捕获不同的异常，并进行处理

* 可以记录日志
* 打印出异常信息
* 吃掉异常不进行处理

和throw new Exception("Cant divide zero") 区别是，throw主动抛出异常


使用catch语句来匹配异常，若匹配上则进入对应catch语句来处理异常。


### catch Multiple Exceptions

```
try {
   statement
} catch(SQLException e) {
    logger.log(e);
} catch(IOException e) {
    logger.log(e);
} catch(Exception e) {
    logger.severe(e);
}

或者

try {
   statement
} catch(SQLException | IOException e) {
    logger.log(e);
} catch(Exception e) {
    logger.severe(e);
}
```

### finally Exceptions

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


使用finally来确保程序即使出现异常时能保证代码继续执行，在主要应用资源的释放。

## How to release resources after an exception  - 异常以后如何释放资源 ？

下面有两种方法来实现资源的释放

1. Try-Catch-Finally     使用finally强制释放资源
2. Try-with-resources   try调用 **java.lang.AutoCloseable** 接口来实现释放

### Try-Catch-Finally

```
    private static final String FILE_FULL_NAME= "E:\\6_Java\\4_workdemo\\Java-language-learning\\exception\\chapter-07-01\\src\\file.txt";

    private static void printFile() throws IOException {
        InputStream input = null;

        try {
            input = new FileInputStream(FILE_FULL_NAME);

            int data = input.read();
            while(data != -1){
                System.out.print((char) data);
                data = input.read();
            }
        } finally {
            if(input != null){
                input.close();
            }
        }
    }
```
在finally 中调用FileInputStream.close()来释放资源


### Try-with-resources

```
    private static void pringFileFromJava() throws IOException{
        try (InputStream  input = new FileInputStream(FILE_FULL_NAME)) {
            int data = input.read();
            while(data != -1){
                System.out.println((char) data);
                data= input.read();
            }
        }
    }
```

在关键字 **try**  处声明了资源变量 input，并在try代码块结束后自动释放资源

为什么会释放呢？

FileInputStream实现 ** java.lang.AutoCloseable** 接口，接口中的close()方法实现资源释放


也就是如果我们也实现AutoCloseable接口的方法，也可以在try-with-resources中实现资源的自动释放。

实例如下：

编写资源类MyAutoClosable
```
public class MyAutoClosable implements AutoCloseable {

    public void doIt(){
        System.out.println("MyAutoClosable doing it!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable closed!");
    }
}

```

调用资源类
```
    private static void myClose() throws Exception{
        try(MyAutoClosable myAutoClosable =  new MyAutoClosable()){
            myAutoClosable.doIt();
        }
    
```

该方法在try块结束时，自动释放了资源，异常由throws Exception向上抛。



## Catch or Propagate Exceptions?   - 异常即使处理还是向上传播异常？

You might be wondering whether you should catch or propate exceptions thrown in your program. 
It depends on the situation. In many applications you can't really do much about the exception but tell the user that the requested action failed
In these applications you can usually catch all or most exceptions centrally in one of the first methods in the call stack

对应公用方法，一般采用异常传播方式，如果异常需要及时处理，则捕获异常的方式


## example


```
/**
 * @author : zhenyun.su
 * @since : 2019/1/3
 */

public class TestException {
    public TestException() {
    }

    boolean testEx() throws Exception {
        boolean ret = true;
        try {
            ret = testEx1();
        } catch (Exception e) {
            System.out.println("testEx, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx1() throws Exception {
        boolean ret = true;
        try {
            ret = testEx2();
            if (!ret) {
                return false;
            }
            System.out.println("testEx1, at the end of try");
            return ret;
        } catch (Exception e) {
            System.out.println("testEx1, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx1, finally; return value=" + ret);
            return ret;
        }
    }

    boolean testEx2() throws Exception {
        boolean ret = true;
        try {
            for (int i = 2; i >= -2; i--) {
                System.out.println("i=" + 2 / i);
            }
            return true;
        } catch (Exception e) {
            System.out.println("testEx2, catch exception");
            ret = false;
            throw e;
        } finally {
            System.out.println("testEx2, finally; return value=" + ret);
            return ret;
        }
    }

    public static void main(String[] args) {
        TestException testException1 = new TestException();
        try {
            testException1.testEx();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

很多人认为输出结果如下：
```
i=2
i=1
testEx2, catch exception
testEx2, finally; return value=false

testEx1, catch exception
testEx1, finally; return value=false

testEx, catch exception
testEx, finally; return value=false
```

事实上，输出结果如下：
```
i=1
i=2
testEx2, catch exception
testEx2, finally; return value=false
testEx1, finally; return value=false
testEx, finally; return value=false
```

你会很奇怪异常到哪去了呢？  

我们只要屏蔽return异常最终会有被main返回捕获并处理。

备注，概实例来之：http://www.importnew.com/14688.html