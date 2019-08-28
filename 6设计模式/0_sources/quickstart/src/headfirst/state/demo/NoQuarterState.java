package headfirst.state.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/28
 */

public class NoQuarterState implements State {
    private GumballMachine gumballMachine;

    public NoQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You inserted  a quarter");
        gumballMachine.setState(gumballMachine.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("You haven't inserted a quarter");
    }

    @Override
    public void turnCrank() {
        System.out.println("You much have a quarter");
    }

    @Override
    public void dispense() {
        System.out.println("You can not dispense,you much have a quarter");
    }
}
