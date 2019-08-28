package headfirst.state.demo;

/**
 * @author : zhenyun.su
 * @comment : 适配器模式 测试
 * @since : 2019/8/8
 */

public class Test {
    public static void main(String[] args) {
        // 类适配器
        startTest();

    }

    public static void startTest(){
        GumballMachine gumballMachine =  new GumballMachine(5);
        System.out.println("------once-------");
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();

        System.out.println("------twice-------");
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        System.out.println("------three-------");
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();

        System.out.println("------thou-------");
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
    }
}
