public class MyUseCheckException {

    public static String testException(Boolean bException) throws MyCheckException{
        if (bException) {
            throw new MyCheckException("MyCheckException throw Exception");
        };
        return "test";
    }

    public static void main(String[] args) {
        try {
            MyUseCheckException.testException(Boolean.TRUE);
            MyRunning myRunning = new MyRunning();
            new Thread(myRunning).start();
        }catch (MyCheckException e) {
            System.out.println(e.getMessage());
        }
    }
}
