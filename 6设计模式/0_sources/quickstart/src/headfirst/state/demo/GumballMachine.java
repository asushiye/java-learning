package headfirst.state.demo;

/**
 * @author : zhenyun.su
 * @comment : 糖果机
 * @since : 2019/8/28
 */

public class GumballMachine {
    private HasQuarterState hasQuarterState;
    private NoQuarterState noQuarterState;
    private SoldOutState soldOutState;
    private SoldState soldState;
    private WinnerState winnerState;
    State state = soldOutState;
    int count = 0;

    public GumballMachine(int count) {
        this.hasQuarterState = new HasQuarterState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.soldOutState = new SoldOutState(this);
        this.soldState = new SoldState(this);
        this.winnerState = new WinnerState(this);
        this.count = count;
        if (count > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        this.state.insertQuarter();
    }

    public void ejectQuarter() {
        this.state.ejectQuarter();
    }

    public void turnCrank() {
        this.state.turnCrank();
    }

    public void dispense() {
        this.state.dispense();
    }

    public void releaseBall(){
        if (this.count >=1) {
            this.count = this.count - 1;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public HasQuarterState getHasQuarterState() {
        return hasQuarterState;
    }

    public NoQuarterState getNoQuarterState() {
        return noQuarterState;
    }

    public SoldOutState getSoldOutState() {
        return soldOutState;
    }

    public SoldState getSoldState() {
        return soldState;
    }

    public WinnerState getWinnerState() {
        return winnerState;
    }
}
