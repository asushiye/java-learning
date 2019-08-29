# 10状态模式

    业务场景
    面向对象解决方法
    代码实现
    模式定义
    深度和其他模式差异及主要应用在哪些场景

状态模式核心思想是解决控制和管理访问。

你能提供很优质的服务，但是你不希望每个人都叫你做事，因此你可以找个黑脸控制对你的访问。

## 业务场景

我们面对场景，使用糖果机实现
![state1](state1.png)

![state2](state2.png)

定义状态
```java
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
}
```
![state3](state3.png)

定义行为
```java
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
```

摇杆和发放糖果行为，略

假如公司为了提升销量，引入游戏机制，有10%概率中奖，能获取两个糖果。

那么需要修改上面代码？

新增中奖状态，每个方法针对状态都要修改。违反开闭原则。

下面我们使用面对对象设计原则来解决。

## 面向对象解决方法

我们将状态封装到对象中，实现如下

对上面代码进行改造

## 代码实现

* 定义状态接口
* 实现每个状态对象
* 状态机
* 测试

### 定义状态接口

```java
public interface State {
    void insertQuarter();
    void ejectQuarter();
    void turnCrank();
    void dispense();
}
```


### 实现每个状态对象

```java
package headfirst.state.demo;

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

public class WinnerState implements State {
    private GumballMachine gumballMachine;

    public WinnerState(GumballMachine gumballMachine) {
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
        System.out.println("you are a winner!, you get two gumball for you quarter");
        gumballMachine.releaseBall();
        if (gumballMachine.getCount() >=1){
            gumballMachine.setState(gumballMachine.getNoQuarterState());
            gumballMachine.releaseBall();
            if (gumballMachine.getCount() ==0) {
                gumballMachine.setState(gumballMachine.getSoldOutState());
            }
        }else{
            gumballMachine.setState(gumballMachine.getSoldOutState());
        }
    }
}

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
```

### 状态机

```java
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

```

### 测试

```java
    public static void main(String[] args) {
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
```


### 最终类图
![state4](state4.png)

## 模式定义

状态模式(composite Pattern)定义：允许对象在内部状态改变时改变它的行为，

对象看起来好像修改了它的类

1. 将状态封装成为独立的类，并将动作委托到代表当前状态的对象。
2. 使用组合通过简单引用不同的状态对象来造成类的改变假象。

![state0](state0.png)


## 深度和其他模式差异及主要应用在哪些场景

### 策略模式区别

策略模式想成是除了继承之外的一种弹性替代方案，

如果你使用继承定义了一个类的行为，你将被困住，甚至需要修改它都很难

而策略模式，可以通过组合的不同的对象来改变行为。

状态模式想成是不用在context中放置许多条件判断的替代方案。

通过将状态包装经状态对象中，你可以通过在context内简单的改变状态对象来改变context行为。
