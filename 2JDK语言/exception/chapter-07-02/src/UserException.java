/**
 * @author : zhenyun.su
 * @since : 2019/1/3
 */
public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }

    public static void getException(String message) throws UserException {
        throw new UserException(message);
    }
}
