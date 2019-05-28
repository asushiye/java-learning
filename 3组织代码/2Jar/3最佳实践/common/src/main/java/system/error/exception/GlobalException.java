package system.error.exception;

/**
 * @author : zhenyun.su
 * @since : 2018/9/13
 * @Comment: 自定义全局异常
 */

public class GlobalException extends Exception {
    private Integer code;

    private Object data;

    public GlobalException() {
        super();
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException(Integer code, String message) {
        super(message);
        this.code = code;
        this.data = null;
    }

    public GlobalException(Integer code, String message, Object data) {
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
