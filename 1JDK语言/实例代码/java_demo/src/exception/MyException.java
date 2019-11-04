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
