public class MyUseCheckException {

    public static String testException(Boolean bException) throws MyCheckException{
        if (bException) {
            throw new MyCheckException("MyCheckException throw Exception");
        };
        return "test";
    }

    public static void main(String[] args) {
        try {
            UserException.getException("运行报错");
        }catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }
}
