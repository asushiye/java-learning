package generic.inter;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-18
 */

public class Test {
    public static void main(String[] args) {
//        coffeeGenerator();
        basicGenerator();
        objectGenerator();
    }

    public static void coffeeGenerator() {
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
    public static void basicGenerator() {
        Generator<Coffee1> generator = BasicGenerator.create(Coffee1.class);
        for (int i = 0; i < 3; i++) {
            Coffee1 coffee1 = generator.next();
            System.out.println(coffee1.toString());
        }
    }

    public static void objectGenerator() {
        Coffee coffee2 = ObjectGenerator.create(Coffee2.class);
        System.out.println(coffee2.toString());
        Coffee coffee3 = ObjectGenerator.create(Coffee3.class);
        System.out.println(coffee3.toString());
    }
}
