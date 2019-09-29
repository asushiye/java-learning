# 2抛出异常


## 异常如何抛出

jvm对方法出现异常，自动抛出，则父方法能够捕获，若也没有处理，则在向上抛出。

异常具有传播性

比如A调B ，B调C，若C方法出现异常而又没有处理，则异常从C抛给B，若B没有处理继续抛给A，直到线程异常退出。

异常除了自动抛出外，我们也可以手工抛出异常。

java使用throw来抛出异常。

## 实例讲解

```java
package exception;
/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-09-29
 */
public class TestThrowException {
    public static void main(String[] args) {
        try{
            testThrow();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testThrow() throws MyException {
        throw new MyException("my exception");
    }
}
```

若在方法中存在使用throw new MyException，则一定要在方法testThrow 后面显性指出MyException异常

具有throws 关键字标识的方法，在调用时，一定进行try-catch处理,再抛给父方法throws。

输出如下：
```
my exception
exception.MyException: my exception
	at exception.TestThrowException.testThrow(TestThrowException.java:20)
	at exception.TestThrowException.main(TestThrowException.java:12)
```

按照方法入栈方式，并从最顶部开始执行，因此如上显示，先输出异常，接着定位在testThrow方法上，再定位在main方法
