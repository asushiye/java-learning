package headfirst.state.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/28
 */

public class SoldOutState implements State {
    private GumballMachine gumballMachine;

    public SoldOutState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("SoldOut, You can't inserted  a quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("SoldOut, You can't eject  a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("SoldOut, You can't turn Crank");
    }

    @Override
    public void dispense() {
        System.out.println("SoldOut, You can't dispense");
    }
}
