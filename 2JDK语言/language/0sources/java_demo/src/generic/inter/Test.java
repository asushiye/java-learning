package generic.inter;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-18
 */

public class Test {
    public static void main(String[] args) {
        System.out.println("according to for, print coffee------------");
        CoffeeGenerator coffeeGenerator = new CoffeeGenerator();
        for (int i = 0; i <6 ; i++) {
            System.out.println(coffeeGenerator.next().toString());
        }

        System.out.println("according to Iterator, print coffee------------");
        for (Coffee c: new CoffeeGenerator(5)){
            System.out.println(c.toString());
        }
    }
}
