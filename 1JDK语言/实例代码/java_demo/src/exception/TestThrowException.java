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
