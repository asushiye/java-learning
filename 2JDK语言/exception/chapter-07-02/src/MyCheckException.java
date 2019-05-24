public class MyCheckException extends Exception {
    public MyCheckException(String message) {
        super(message);
    }

    public static void testException(Boolean bException) throws MyCheckException{
        if (bException) {
            throw new MyCheckException("MyCheckException throw Exception");
        };
    }
}
