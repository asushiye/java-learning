## 12复合模式

1. 子有不同叫声 - 策略模式
2. 鸭子模拟器像鸭子一样使用鹅 - 适配器模式
3. 统计鸭子叫声次数 - 装饰者模式
4. 统一鸭子创建 - 抽象工厂模式
5. 统一管理一群鸭子 - 组合模式
6. 鸭子学者，希望鸭子一叫，就被通知到 - 观察者模式 (还未完成，headfirst515页)

## 1鸭子有不同叫声 - 策略模式

鸭子的叫声接口
```java
public interface Quackable {
    public void quack();
}
```

绿头鸭
```java
public class GreenDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
```
红头鸭
```java
public class RedDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}

```

鸭鸣器 - 模拟鸭子叫声的东西
```java
public class CallDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("模拟鸭子呱呱叫");
    }
}
```

橡皮鸭
```java
public class RubberDuck implements Quackable {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}
```

鸭子模拟器
```java
public class DuckSimulator {
   void simulate(){
       Quackable greenDuck = new GreenDuck();
       Quackable redDuck= new RedDuck();
       Quackable callDuck= new CallDuck();
       Quackable rubberDuck= new RubberDuck();
       System.out.println("鸭子模拟器");
       quack(greenDuck);
       quack(redDuck);
       quack(callDuck);
       quack(rubberDuck);
   }
   void quack(Quackable quck){
       quck.quack();
   }
    public static void main(String[] args) {
        DuckSimulator duckSimulator = new DuckSimulator();
        duckSimulator.simulate();
    }
}
```

输出如下
```
鸭子模拟器
呱呱叫
呱呱叫
模拟鸭子呱呱叫
吱吱叫
```

## 2鸭子模拟器像鸭子一样使用鹅 - 适配器模式

鹅
```java
public class Goose {
    public void honk() {
        System.out.println("鹅咯咯叫");
    }
}
```

娥适配器类
```java
public class GooseAdapter implements Quackable {
    private Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }
    @Override
    public void quack() {
        this.goose.honk();
    }
}
```

```java
public class DuckSimulator {
   void simulate(){
       Quackable greenDuck = new GreenDuck();
       Quackable redDuck= new RedDuck();
       Quackable callDuck= new CallDuck();
       Quackable rubberDuck= new RubberDuck();
       Quackable gooseAdapter= new GooseAdapter(new Goose());
       System.out.println("鸭子模拟器");
       quack(greenDuck);
       quack(redDuck);
       quack(callDuck);
       quack(rubberDuck);
       quack(gooseAdapter);
   }
   void quack(Quackable quck){
       quck.quack();
   }
    public static void main(String[] args) {
        DuckSimulator duckSimulator = new DuckSimulator();
        duckSimulator.simulate();
    }
}
```

输出
```
鸭子模拟器
呱呱叫
呱呱叫
模拟鸭子呱呱叫
吱吱叫
鹅咯咯叫
```

## 3统计鸭子叫声次数 - 装饰者模式

```java
public class QuackCounter implements Quackable {
    private Quackable quck;
    private static int count=0;
    public QuackCounter(Quackable quck) {
        this.quck = quck;
    }
    @Override
    public void quack() {
        quck.quack();
        count++;
    }
    public static int getCount(){
        return count;
    }
}
```

```java
public class DuckSimulator {
   void simulate(){
       Quackable greenDuck = new QuackCounter(new GreenDuck());
       Quackable redDuck= new QuackCounter(new RedDuck());
       Quackable callDuck= new QuackCounter(new CallDuck());
       Quackable rubberDuck= new QuackCounter(new RubberDuck());
       Quackable gooseAdapter= new GooseAdapter(new Goose());
       System.out.println("鸭子模拟器");
       quack(greenDuck);
       quack(redDuck);
       quack(callDuck);
       quack(rubberDuck);
       quack(gooseAdapter);
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
```

```
输出
鸭子模拟器
呱呱叫
呱呱叫
模拟鸭子呱呱叫
吱吱叫
鹅咯咯叫
鸭子总共叫了：4 次
```

## 4统一鸭子创建 - 抽象工厂模式

抽象鸭子工厂
```java
public abstract class AbstractDuckFactory {
    public abstract Quackable createGreenDuck();
    public abstract Quackable createRedDuck();
    public abstract Quackable createCallDuck();
    public abstract Quackable createRubberDuck();
}
```

鸭子工厂
```java
public class DuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createGreenDuck() {
        return new GreenDuck();
    }
    @Override
    public Quackable createRedDuck() {
        return new RedDuck();
    }
    @Override
    public Quackable createCallDuck() {
        return new CallDuck();
    }
    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
```

可计数鸭子工厂
```java
public class CountingDuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createGreenDuck() {
        return new QuackCounter(new GreenDuck());
    }
    @Override
    public Quackable createRedDuck() {
        return new QuackCounter(new RedDuck());
    }
    @Override
    public Quackable createCallDuck() {
        return new QuackCounter(new CallDuck());
    }
    @Override
    public Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }
}
```

```java
public class DuckSimulator {
   void simulate(){
       CountingDuckFactory duckFactory = new CountingDuckFactory();
//       DuckFactory duckFactory = new DuckFactory();
       Quackable greenDuck = duckFactory.createGreenDuck();
       Quackable redDuck= duckFactory.createRedDuck();
       Quackable callDuck= duckFactory.createCallDuck();
       Quackable rubberDuck= duckFactory.createRubberDuck();

       Quackable gooseAdapter= new GooseAdapter(new Goose());
       System.out.println("鸭子模拟器");
       quack(greenDuck);
       quack(redDuck);
       quack(callDuck);
       quack(rubberDuck);
       quack(gooseAdapter);
       System.out.println("鸭子总共叫了："+ QuackCounter.getCount()+" 次");
   }
}
```

## 5统一管理一群鸭子 - 组合模式

```java
public class DuckComposite {
    private List<Quackable> quackableList = new ArrayList<>();

    public void add(Quackable duck){
        quackableList.add(duck);
    }

    public void quack(){
        Iterator<Quackable> iterator = quackableList.iterator();
        while (iterator.hasNext()){
            iterator.next().quack();
        }
    }
}
```

```java
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
}
```

输出
```
鸭子模拟器
呱呱叫
呱呱叫
模拟鸭子呱呱叫
吱吱叫
鹅咯咯叫
鸭子总共叫了：4 次
```

我们知道组合模式，存在节点和叶子节点，她们都是用抽象组合类中继承，具有一组相同的方法。

其中包含add()方法，这样设计好处是，叶子节点和节点是透明的。

客户根本不用管是节点，还是叶子节点。

而这里将节点和叶子节点分开，只让节点有add（）方法，这样设计好处时安全，只有组合才能add（）

在OO设计过程中，折中一直都是免不了的

## 6鸭子学者，希望鸭子一叫，就被通知到 - 观察者模式

```java

```

```java

```
