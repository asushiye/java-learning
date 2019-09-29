package exception;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * @author : zhenyun.su
 * @since : 2019/1/3
 */

public class TestTryException {

    public static void main(String[] args) {
        try {
//            tryCatchFinally();
//            tryCatchFinallyByReturn();
            tryCatchFinallyByWhile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static void tryCatchFinally() {
        System.out.println("method begin");
        try {
            System.out.println("try begin");
//            int i = 1 / 0;
            System.out.println("try end");
        } catch (Exception e) {
            System.out.println("catch, exception: " + e.getMessage());
        } finally {
            System.out.println("finally");
        }
        System.out.println("method end");
    }

    static int tryCatchFinallyByReturn() {
        System.out.println("method begin");
        try {
            System.out.println("try begin");
            int i = 1 / 0;
            System.out.println("try end");
            return 0;
        } catch (Exception e) {
            System.out.println("catch, exception: " + e.getMessage());
            e.printStackTrace();
            return 1;
        } finally {
            System.out.println("finally");
        }
    }
    //java.lang.ArithmeticException: / by zero

    static void tryCatchFinallyByWhile() {
        System.out.println("method begin");
        int iIndex = 0;
        while (iIndex <= 2) {
            iIndex++;
            System.out.println("while: " + iIndex + " begin");
            try {
                System.out.println("try begin");
                if (iIndex == 1) {
                    return;
//                    continue;
                }
                int i = 1 / 0;
                System.out.println("try end");
            } catch (Exception e) {
                System.out.println("catch, exception: " + e.getMessage());
            } finally {
                System.out.println("finally");
            }
            System.out.println("while: " + iIndex + " end");
        }
        System.out.println("method end");
    }


    static void tryCatchFinallyByThrow() throws Exception {
//        try {
//            System.out.println("try begin");
//            Request request = new Request.Builder()
//                    .url(url)
//                    .post(requestBody)
//                    .build();
//            Response response = client.newCall(request).execute();
//        } catch (SocketTimeoutException ste) {
//            System.out.println("catch SocketTimeoutException, exception: " + ste.getMessage());
//            throw new NetException("SocketTimeout");
//        } catch (SocketException se) {
//            System.out.println("catch SocketException, exception: " + se.getMessage());
//            throw new NetException("SocketException");
//        } catch (UnknownHostException uhe) {
//            System.out.println("catch UnknownHostException, exception: " + uhe.getMessage());
//            throw new NetException("UnknownHost");
//        } catch (Exception e) {
//            System.out.println("catch other all exception, exception: " + e.getMessage());
//            throw new NetException("other exceptions");
//        }
    }
}
