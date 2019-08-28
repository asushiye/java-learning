package headfirst.state.demo;

import java.util.Random;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/28
 */

public class HasQuarterState implements State {
    Random random = new Random(System.currentTimeMillis());
    private GumballMachine gumballMachine;

    public HasQuarterState(GumballMachine gumballMachine) {
        this.gumballMachine = gumballMachine;
    }

    @Override
    public void insertQuarter() {
        System.out.println("You can't inserted another quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("quarter return");
        gumballMachine.setState(gumballMachine.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("You can dispense gumball");
        int winner = random.nextInt(5);
        if ((winner ==0)&&(gumballMachine.getCount() >=2)){
            gumballMachine.setState(gumballMachine.getWinnerState());
        }else{
            gumballMachine.setState(gumballMachine.getSoldState());
        }
        gumballMachine.dispense();
    }

    @Override
    public void dispense() {
        System.out.println("have not dispense");
    }
}
