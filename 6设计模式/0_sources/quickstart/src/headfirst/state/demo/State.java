package headfirst.state.demo;

/**
 * @author : zhenyun.su
 * @comment : 状态接口
 * @since : 2019/8/28
 */

public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
