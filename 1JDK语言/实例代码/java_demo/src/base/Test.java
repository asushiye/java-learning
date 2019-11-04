package base;

import java.math.BigDecimal;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/19
 */

public class Test {
    public static void main(String[] args) {
//        base();
        bigDecimal();
    }

    public static void base() {
        int a=888;
        Integer count = new Integer(a);
        boolean yes=true;
        Boolean flag = new Boolean(yes);

        int b= count.intValue();
        boolean yesyes = flag.booleanValue();

        byte bb =count.byteValue();
        long l = count.longValue();
        short s = count.shortValue();

        String value= "2";
        int x= Integer.parseInt(value);
        long l1 = Long.parseLong(value);
        double d = Double.parseDouble(value);




        Integer v1 = 22;
        Double v2 = 22.2;
        Boolean v3 = true;
        String value1 = v1.toString();
        String value2 = v2.toString();
        String value3= v3.toString();

        int one = 20;
        double two = 1000334245.1234;
        String sformat = String.format("the rank is %,10d out of %,3.2f", one, two);
        System.out.println(sformat);
    }

    public static void bigDecimal() {
        BigDecimal amount = new BigDecimal(0);
        BigDecimal amount1 = new BigDecimal(25);
        BigDecimal amount2 = new BigDecimal(26);
        amount = amount.add(amount1);
        amount = amount.add(amount2);
        amount = amount.add(amount2);

        System.out.println(amount);
        System.out.println(amount.compareTo(amount1));
        System.out.println(amount.compareTo(amount));
        System.out.println(amount1.compareTo(amount));
    }
}
