package jvm;

import jvm.classs.ByteCode;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-07
 */

public class Test {
    final static String s1= "zhenyun.su";
    final static String s2 = "zhenyun.su";
    final static String s3 =  new String("zhenyun.su");

    public Test() {
//        s1 = "zhenyun.su";
//        s2 = "zhenyun.su";
//        s3 = new String("zhenyun.su");
    }

//    String s3 = new String("zhenyun.su");

    public static void main(String[] args) {
//        Test test = new Test();
        String s1 = "zhenyun.su";
        String s2 = "zhenyun.su";
        String s3 = new String("zhenyun.su");

        System.out.println(Test.s1==Test.s2);
        System.out.println(s1.equals(s2));
        System.out.println(Test.s1==Test.s3);
        System.out.println(s1.equals(s3));

        ByteCode byteCode = new ByteCode();
        byteCode.add();
    }
}
