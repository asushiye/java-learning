package statics;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/9/19
 */
public class Duck{

    public static int duckCount=5;

    public Duck(){
        duckCount++;
    }

    public static void print(){
        System.out.println("static void print return :"+duckCount);
    }
}
