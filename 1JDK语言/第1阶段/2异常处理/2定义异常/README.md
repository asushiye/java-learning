# 2定义异常
		java提供异常
		自定义异常
			如何自定义异常
			自定义异常实例

## java提供异常


## 自定义异常

### 如何自定义异常

自定义异常用于处理，可能出现异常，进行统一处理，可以从**Exception和RuntimeException**继承

推荐使用RuntimeException上继承使用

### 自定义异常实例

#### 简单实例
```java
package exception;
/**
 * @author : zhenyun.su
 * @since : 2019/1/3
 */
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
    public static void getException(String message) throws MyException {
        throw new MyException(message);
    }
}
```

#### 自定义全局网络异常
自定义全局网络异常,用于http异常处理
```java
package exception;
/**
 * @author : zhenyun.su
 * @since : 2019/9/7
 * @Comment: 自定义全局网络异常,用于http异常处理
 */
public class NetException extends RuntimeException {
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

    public static void throwException(Integer code, String message) throws NetException {
        throw new NetException(code, message);
    }
    public static void throwException(Integer code, String message, Object data) throws NetException {
        throw new NetException(code, message, data);
    }
}
```
