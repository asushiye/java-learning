# 3捕获&处理异常
		try-catch-finally用法
		try-catch-finally 正常使用
		try-catch-finally配合return使用
		try-catch-finally配合循环使用
		try-catch-finally配合循环及break使用
		try-catch-finally配合循环及continue使用
		try-catch-finally配合throw使用

## try-catch-finally用法

我们可以使用try-catch-finally语句来实现异常捕获和异常处理

try语句一定要使用，catch语句和finally语句 **选用**

## try-catch-finally 正常使用

下面使用 try-catch-finally语法来捕获和处理

```java
    static void tryCatchFinally() {
        System.out.println("method begin");
        try {
            System.out.println("try begin");
//            int i = 1 / 0;
            System.out.println("try end");
        } catch (Exception e) {
            System.out.println("catch, exception: " + e.getMessage());
        } finally {
            System.out.println("finally");
        }
        System.out.println("method end");
    }
```
try语句没有出现异常，则输出如下：
```
method begin
try begin
try end
finally
method end
```

如果在try语句中执行`int i = 1 / 0` 则出现下面异常 `java.lang.ArithmeticException: / by zero`
```
method begin
try begin
catch, exception: by zero
finally
method end
```
若出现异常，finally语句会执行， 由于在catch已经处理异常，方法最后的**method end**也会被输出。

## try-catch-finally配合return使用

如果在try语句中，存在return语句，则finally执行结束，才返回
```java
    static int tryCatchFinallyByReturn() {
        System.out.println("method begin");
        try {
            System.out.println("try begin");
            int i = 1 / 0;
            System.out.println("try end");
            return 0;
        } catch (Exception e) {
            System.out.println("catch, exception: " + e.getMessage());
            e.printStackTrace();
            return 1;
        } finally {
            System.out.println("finally");
        }
    }
```

输出结果如下， finally之前，不够有没有异常，如果return则，都要执行finally，才进行返回值
```
method begin
try begin
catch, exception: / by zero
finally
```

## try-catch-finally配合循环使用

如果try放在循环中，又如何处理呢？
```java
    static void tryCatchFinallyByWhile() {
        System.out.println("method begin");
        int iIndex = 0;
        while (iIndex <= 2) {
            iIndex++;
            System.out.println("while: "+iIndex+ " begin");
            try {
                System.out.println("try begin");
                int i = 1 / 0;
                System.out.println("try end");
            } catch (Exception e) {
                System.out.println("catch, exception: " + e.getMessage());
            } finally {
                System.out.println("finally");
            }
            System.out.println("while: "+iIndex+ " end");
        }
        System.out.println("method end");
    }
```

该try语句出现异常，则catch处理异常，则后面代码都正常执行，
```
method begin
while: 1 begin
try begin
catch, exception: / by zero
finally
while: 1 end
......
method end
```

若没有catch，异常没被处理，则跳出循环， 屏蔽上面catch语句和异常处理语句
```
method begin
while: 1 begin
try begin
finally
/ by zero
```
 在异常发生后，执行fially语句后，跳出循环及退出方法

## try-catch-finally配合循环及break使用

若在while循环中break时，又如何执行呢?
```java
static void tryCatchFinallyByWhile() {
        System.out.println("method begin");
        int iIndex = 0;
        while (iIndex <= 2) {
            iIndex++;
            System.out.println("while: " + iIndex + " begin");
            try {
                System.out.println("try begin");
                if (iIndex == 1) {
                    break;
                }
                System.out.println("try end");
            } catch (Exception e) {
                System.out.println("catch, exception: " + e.getMessage());
            } finally {
                System.out.println("finally");
            }
            System.out.println("while: " + iIndex + " end");
        }
        System.out.println("method end");
    }
```

在条件满足下，break时，跳过这次循环try语句后面代码，在执行完成fianlly语句后，直接跳出循环
```
method begin
while: 1 begin
try begin
finally
method end
```

## try-catch-finally配合循环及continue使用

如果是continue时，则只跳过try后面语句及try-catch-finally外的单次循环的代码
```java
    static void tryCatchFinallyByWhile() {
        System.out.println("method begin");
        int iIndex = 0;
        while (iIndex <= 2) {
            iIndex++;
            System.out.println("while: " + iIndex + " begin");
            try {
                System.out.println("try begin");
                if (iIndex == 1) {
                    continue;
                }
                System.out.println("try end");
            } catch (Exception e) {
                System.out.println("catch, exception: " + e.getMessage());
            } finally {
                System.out.println("finally");
            }
            System.out.println("while: " + iIndex + " end");
        }
        System.out.println("method end");
    }
```
输出结果
```
method begin
while: 1 begin
try begin
finally
while: 2 begin
try begin
catch, exception: / by zero
finally
while: 2 end
while: 3 begin
try begin
catch, exception: / by zero
finally
while: 3 end
method end
```


## try-catch-finally配合throw使用

自定义异常,用于统一处理网络异常
```java
public class NetException extends Exception {
    private Integer code;
    private Object data;

    public NetException() {
        super();
    }
    public NetException(String message) {
        super(message);
    }
    public NetException(Integer code, String message) {
        super(message);
        this.code = code;
        this.data = null;
    }
    public NetException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }
    public Object getData() {
        return data;
    }
}
```

使用try-catch-fianlly配合throw处理异常
```java
    static void tryCatchFinallyByThrow() throws Exception {
        try {
            System.out.println("try begin");
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
        } catch (SocketTimeoutException ste) {
            System.out.println("catch SocketTimeoutException, exception: " + ste.getMessage());
            throw new NetException("SocketTimeout");
        } catch (SocketException se) {
            System.out.println("catch SocketException, exception: " + se.getMessage());
            throw new NetException("SocketException");
        } catch (UnknownHostException uhe) {
            System.out.println("catch UnknownHostException, exception: " + uhe.getMessage());
            throw new NetException("UnknownHost");
        } catch (Exception e) {
            System.out.println("catch other all exception, exception: " + e.getMessage());
            throw new Exception("other exceptions");
        }
    }
```

在调用这个方法的方法后，可以捕获NetException以区别其他异常，分别进行统一处理
