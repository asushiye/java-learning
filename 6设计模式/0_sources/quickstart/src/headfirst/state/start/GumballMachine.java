package headfirst.state.start;

/**
 * @author : zhenyun.su
 * @comment : 糖果机
 * @since : 2019/8/28
 */

public class GumballMachine {
    final static int SOLD_OUT = 0;
    final static int NO_QUARTER = 1;
    final static int HAS_QUARTER = 2;
    final static int SOLD = 3;
    int state = SOLD_OUT;
    int count = 0;

    public GumballMachine(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }

    public void insertQuarter(){
        if (state == NO_QUARTER){
            state = HAS_QUARTER;
            System.out.println("You inserted  a quarter");
        }else if (state == HAS_QUARTER){
            System.out.println("You can not inserted  another quarter");
        }else if (state == SOLD){
            System.out.println("please wait, we're already giving you a gumball");
        }else if (state == SOLD_OUT){
            System.out.println("You can't insert a quarter,the machine is sold out");
        }
    }

    public void ejectQuarter(){
        if (state == NO_QUARTER){
            System.out.println("You haven't inserted a quarter");
        }else if (state == HAS_QUARTER){
            state = NO_QUARTER;
            System.out.println("quarter return");
        }else if (state == SOLD){
            System.out.println("sorry, we're already turned the crank");
        }else if (state == SOLD_OUT){
            System.out.println("You can't eject,You haven't inserted a quarter yet");
        }
    }

}
