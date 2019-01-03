public class MyUseUnCheckException {

    public static String testException(Boolean bException){
        if (bException) {
            throw new MyUnCheckException("MyUnCheckException throw Exception");
        };
        return "test";
    }

    public static void main(String[] args) {
        try {
            MyUseUnCheckException.testException(Boolean.TRUE);
            MyRunning myRunning = new MyRunning();
            new Thread(myRunning).start();
        }catch (MyUnCheckException e){
            System.out.println(e.getMessage());
        }

    }
}
