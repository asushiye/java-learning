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
