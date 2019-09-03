package headfirst.complex.start;

/**
 * @author : zhenyun.su
 * @comment : 鸭子模拟器
 * @since : 2019/9/3
 */

public class DuckSimulator {
   void simulate(){
       CountingDuckFactory duckFactory = new CountingDuckFactory();
       DuckComposite duckComposite = new DuckComposite();
       duckComposite.add(duckFactory.createGreenDuck());
       duckComposite.add(duckFactory.createRedDuck());
       duckComposite.add(duckFactory.createCallDuck());
       duckComposite.add(duckFactory.createRubberDuck());

       Quackable gooseAdapter= new GooseAdapter(new Goose());
       duckComposite.add(gooseAdapter);
       System.out.println("鸭子模拟器");
       duckComposite.quack();
       System.out.println("鸭子总共叫了："+ QuackCounter.getCount()+" 次");
   }
   void quack(Quackable quck){
       quck.quack();
   }
    public static void main(String[] args) {
        DuckSimulator duckSimulator = new DuckSimulator();
        duckSimulator.simulate();
    }
}
