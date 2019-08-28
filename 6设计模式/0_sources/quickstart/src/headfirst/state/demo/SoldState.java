package headfirst.state.demo;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/28
 */

public class SoldState implements State {
    private GumballMachine gumballMachine;

    public SoldState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("please wait, we're already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("you can eject qurater, we're already giving you a gumball");
    }

    @Override
    public void turnCrank() {
        System.out.println("turning twice doesn't get you another gumball");
    }

    @Override
    public void dispense() {
        System.out.println("you get a gumball for you quarter");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() >=1){
            gumballMachine.setState(gumballMachine.getNoQuarterState());
        }else{
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}
