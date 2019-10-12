# 1概念

    为什么java需要引入泛型
    认识泛型
    泛型在类中应用
      元祖类库 - TwoTuple ThreeTuple FourTuple
        创建2维元祖
        通过继承创建多维元祖
        编程测试代码
      堆栈类 - LinkedStack
      随机列表 - RandomList (391页)

## 为什么java需要引入泛型

一般的类和方法，只能使用具体的类型，要么是基本类型，要么是自定义的类，

如果要写可以应用于多种类型的代码。这种规则大大限制代码通用性。

在面向对象编程中，利用多态性，或接口来实现泛化功能。

例如
1. 可以将变量，或方法参数定义基类，则所有基类的子类都可以赋值给变量或参数。
2. 可以将变量，或方法参数定位接口，则只要实现这个接口所有类都可以赋值给变量或参数。

使用基类来泛化，则受到基类的约束，只有继续基类的子类才能通用

使用接口来泛化，则受到接口的约束，只有实现接口的类才能通用

所以java引入：泛型的概念，将类型参数化，使用变量或参数适用所有的类型。

泛型的术语“适用许多许多的类型”，使代码更具有通用性，也实现代码解耦

什么是泛型，泛型是如何使用的， java泛型的边界在哪里，局限在哪里

## 认识泛型

我们知道数组和容器都可以用存储大量对象，不过数组相比容器，容器更加灵活，具备更多功能

java泛型产生的最主要一个原因就是容器的使用，我们为了容器类中大量使用了泛型

泛型的主要目的之一就是用来指定容器要持有什么类型的对象，而且由编译器来检查类型，保证类型的正确性

下面一步一步演示，使用泛型的好处

### 指定类型的实例变量
```java
public class AutoMobile {}
public class AutoTv {}
```

```java
public class Holder1 {
    private AutoMobile a;
    public AutoMobile getA() {
        return a;
    }
    public void setA(AutoMobile a) {
        this.a = a;
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        Holder1 holder1 = new Holder1();
        holder1.setA(new AutoMobile());
        holder1.setA(new AutoTv()); //显示异常，编译报错，类型不符合参数
    }
}
```

Holder1类 a实例变量只能存放AutoMobile对象，存放其他对象则编译报错

### 指定Object类型的实例变量

```java
public class Holder2 {
    private Object a;

    public Object getA() {
        return a;
    }
    public void setA(Object a) {
        this.a = a;
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        Holder2 holder2 = new Holder2();
        holder2.setA(new AutoMobile());
        holder2.setA(new AutoTv());
        AutoMobile mobile2 = (AutoMobile)holder2.getA();
    }
}
```
Object类型的变量a，可以存放任何的类的对象，因为所有类都继承自Object

不过在取出过程，必须进行强制类型转换，

每个类都会特定功能，如果定义Object后，所有类的对象都可以存放，如上所示，虽然编译不报错，但是在运行时，将报错

### 指定泛型的实例变量

```java
public class Holder3<T> {
    private T a;

    public T getA() {
        return a;
    }
    public void setA(T a) {
        this.a = a;
    }
}
```

对于泛型的类在定义时，将泛型参数化类型名称**T**,(名称任意去，一个大写字母表示)，置于尖括号<>内,

然后在实例变量或方法参数中使用T来代表其他类型

泛型的核心概念：告诉编译器想使用什么类型，然后编译器帮你处理一切的细节。
```java
public class Test {
    public static void main(String[] args) {
        Holder3<AutoMobile> mobileHolder3 = new Holder3<>();
        mobileHolder3.setA(new AutoMobile());
        AutoMobile mobile3 = mobileHolder3.getA();

        Holder3<AutoTv> tvHolder3 = new Holder3<>();
        tvHolder3.setA(new AutoTv());
    }
}
```
使用泛型的类Holder3，在声明创建时就已经指定需要使用类型，比如AutoMobile，名称置于尖括号<AutoMobile>内。

这样mobileHolder3对象，只能使用AutoMobile类型来操作，比如setA()方法，只能使用AutoMobile

这就能避免人为，导致运行时错误，因为在编译时就能检查出来

而且在后面对变量a的操作，取值都不需要进行类型转换。

当然你如要使用其他类，则定义为支持其他类的`Holder3<AutoTv> tvHolder3 = new Holder3<>();`


## 泛型在类中应用

### 元祖类库 - TwoTuple

方法的 return 只能返回一个对象，那如何支持返回多个对象呢?

解决方法，让返回的一个对象上持有多个对象。

当然可以在每次需要的时候专门创建这个类来完成，这样不同的方法，就写入很多不同的类来实现

有了泛型，我们只要定义一个泛型类，就可以实现，

这个概念称为元祖(tuple)， 将一组对象打包存放在单一的一个对象中，

通常元祖类，可以任意长度，任意类型，不过我们希望指定长度的任意类型的元祖，

下面通过创建元祖展示泛型使用：
* 创建2维元祖
* 通过继承创建多维元祖
* 编程测试代码

#### 创建2维元祖

```java
public class TwoTuple<A, B> {
    public final A first;
    public final B secord;

    public TwoTuple(A first, B secord) {
        this.first = first;
        this.secord = secord;
    }

    @Override
    public String toString() {
        return "TwoTuple{" +
                "first=" + first +
                ", secord=" + secord +
                '}';
    }
}
```

元祖只是为了实现将多个对象打包到单一的一个对象中，然后在读取出来，

因此我们只要构建时，赋值就可以，为什么变量作用域不使用private，不是违反安全性么？

这里我们使用final来确保变量，创建是被初始化，而以后不再赋值，保证安全性，而public方便直接方法变量。


#### 通过继承创建多维元祖

```java
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public final C three;

    public ThreeTuple(A first, B secord, C three) {
        super(first, secord);
        this.three = three;
    }

    @Override
    public String toString() {
        return "ThreeTuple{" +
                "three=" + three +
                ", first=" + first +
                ", secord=" + secord +
                '}';
    }
}
```

```java
public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
    public final D four;

    public FourTuple(A first, B secord, C three, D four) {
        super(first, secord, three);
        this.four = four;
    }

    @Override
    public String toString() {
        return "FourTuple{" +
                "four=" + four +
                ", three=" + three +
                ", first=" + first +
                ", secord=" + secord +
                '}';
    }
}
```

#### 编程测试代码

```java
public class Test {
    public static void main(String[] args) {
        TwoTuple<String, Integer> twoTuple = new TwoTuple<>("zhenyun.su", 25);

        ThreeTuple<Integer, String, Long> threeTuple = new ThreeTuple<>(24, "hello", 23L);

        FourTuple<Integer, String, Long, Double> fourTuple = new FourTuple<>(24, "hello", 23L, 3.3);
        System.out.println("TwoTuple:" + twoTuple);
        System.out.println("ThreeTuple:" + threeTuple);
        System.out.println("FourTuple:" + fourTuple);
    }
}
```

输出
```
TwoTuple:TwoTuple{first=zhenyun.su, secord=25}
ThreeTuple:ThreeTuple{three=23, first=24, secord=hello}
FourTuple:FourTuple{four=3.3, three=23, first=24, secord=hello}
```

### 堆栈类 - LinkedStack

栈stack使用通过后进先出的存储结构，通过push()来压进，pop()来弹出

```java
package generic.stack;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-12
 */

public class LinkedStack <T> {
    private static class Node<U>{
        U item;
        Node<U> next;
        Node(){ this.item = null;this.next = null;}
        Node(U item, Node<U> node){
            this.item = item;
            this.next = node;
        }
        boolean end(){return (item==null&& next==null);}
    }
    private Node<T> top = new Node<T>(); //end sentinel

    public Boolean hasNext(){
        return !top.end();
    }

    public void push(T item){
        top = new Node<T>(item, top);
    }

    public T pop(){
        T item = top.item;
        while (hasNext()){
            top = top.next;
            break;
        }
        return item;
    }
}
```

通过定义内部静态类Node来实现，链式存储机制

通过**末端哨兵end sentinel**来判断是否堆栈何时为空，在个末端哨兵在创建LinkedStack实例化的

当你调用push(T item) 方法时，会将创建一个Node<T>节点，并将原来Top节点作为这个新节点子节点。

也就是将原来顶层节点往下压。

当你调用pop()方法时，获取顶层节点数据，并将下一层节点，作为顶层节点，也就是原来顶层节点弹出。


### 随机列表 - RandomList

java编程思想(391页)
