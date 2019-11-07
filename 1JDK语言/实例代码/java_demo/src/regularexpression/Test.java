package regularexpression;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-07
 */

public class Test {
    public static void main(String[] args) {
//        testString();
        regex();
    }
    public static void testString() {
        System.out.println("-123".matches("-?\\d+"));  //true
        System.out.println("123".matches("-?\\d+"));   //true
        System.out.println("+123".matches("-?\\d+"));  //false
        System.out.println("+123".matches("(-|\\+)?\\d+"));  //true

        String value = "hello.. su zhen yun";
        System.out.println(Arrays.toString(value.split(" ")));
        System.out.println(Arrays.toString(value.split("\\W+")));
        System.out.println(Arrays.toString(value.split("u\\W+")));
    }

    public static void regex() {
        String mailRegex= "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        System.out.println("123zhen@qq.com.cn".matches(mailRegex));
        System.out.println("zhen@qq.com.cn".matches(mailRegex));
        System.out.println("zhen@qq.com".matches(mailRegex));
        System.out.println("@qq.com".matches(mailRegex));
        System.out.println("zhen@qq".matches(mailRegex));
        System.out.println("zhenqq.com".matches(mailRegex));
    }
}
