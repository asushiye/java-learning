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
    泛型在接口中应用
      实现咖啡对象生成器 - CoffeeGenerator
      实现咖啡迭代器 - CoffeeIterator
    泛型在方法中应用
      类型参数推断
      在类中定义泛型方法
      可变类型与泛型方法
      通用生成器 - Generator
      通用元祖工具 - Tuple
      通用Sets工具 - Sets



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

从特定类型的列表中随机获取元素

```java
public class RandomList<T> {
    private ArrayList<T>  list = new ArrayList<>();
    private Random random = new Random();
    public void add(T item){
        list.add(item);
    }
    public T select(){
        return list.get(random.nextInt(list.size()));
    }
    public int size(){
        return list.size();
    }
    public boolean remove(T item){
        return list.remove(item);
    }
    @Override
    public String toString(){
        return list.toString();
    }
}
```


```java
public class Test {
    public static void main(String[] args) {
        RandomList<String> randomList = new RandomList<>();
        for(String item: "hello su zhen yun".split(" ")){
            randomList.add(item);
        }
        String item = randomList.select();
        System.out.println("get "+item+" from "+randomList.toString());
        randomList.remove(item);
        System.out.println("randomList: "+randomList.toString());
    }
}
```

随机输出：`su` 每次执行值可能不同


## 泛型在接口中应用

泛型也可以应用在接口中，例如对象生成器Generator，专门用于负责创建对象的接口，

实际上这是工厂方法设计模式的一种应用，只不过工厂方法设计模式需要参数来决定创建哪种对象，而这个不需要

### 实现咖啡对象生成器 - CoffeeGenerator

生成器接口
```java
public interface Generator<T> {
    T next();
}
```

定义类
```java
public abstract class Coffee {
    private static long counter = 0;
    private final long id=counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName()+" "+id;
    }
}

public class Coffee1 extends Coffee {}
public class Coffee2 extends Coffee {}
public class Coffee3 extends Coffee {}
public class Coffee4 extends Coffee {}
```

实现咖啡生成器类
```java
public class CoffeeGenerator implements Generator<Coffee> {
    private Class[] types = {Coffee1.class, Coffee2.class, Coffee3.class, Coffee4.class};
    private static Random random = new Random(47);

    public CoffeeGenerator() { }

    public int size(){
        return types.length;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee)types[random.nextInt(types.length)].newInstance();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }
}
```

随机生成咖啡对象，生成6个
```java
public class Test {
    public static void main(String[] args) {
        CoffeeGenerator coffeeGenerator = new CoffeeGenerator();
        for (int i = 0; i <6 ; i++) {
            System.out.println(coffeeGenerator.next().toString());
        }
    }
}
```
执行结果
```
Coffee3 0
Coffee2 1
Coffee3 2
Coffee1 3
Coffee1 4
Coffee3 5
```

### 实现咖啡迭代器 - CoffeeIterator

1. 在咖啡生成器增加对可迭代接口Iterable<Coffee>实现，返回迭代器Iterator
2. 在咖啡生成器中，添加内部咖啡迭代器

实现一个咖啡迭代器CoffeeIterator，时咖啡生成器具有遍历功能

```java
public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[] types = {Coffee1.class, Coffee2.class, Coffee3.class, Coffee4.class};
    private static Random random = new Random(47);

    public CoffeeGenerator() { }

    public int size(){
        return types.length;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee)types[random.nextInt(types.length)].newInstance();
        }catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    private int total=0;

    public CoffeeGenerator(int total) {
        this.total = total;
    }
    class CoffeeIterator implements Iterator<Coffee> {
        int count = CoffeeGenerator.this.total;
        @Override
        public boolean hasNext() {
            return count > 0;
        }
        @Override
        public Coffee next() {
            count --;
            return CoffeeGenerator.this.next();
        }
        @Override
        public void remove() {
            throw  new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("according to for, print coffee------------");
        CoffeeGenerator coffeeGenerator = new CoffeeGenerator();
        for (int i = 0; i <6 ; i++) {
            System.out.println(coffeeGenerator.next().toString());
        }

        System.out.println("according to Iterator, print coffee------------");
        for (Coffee c: new CoffeeGenerator(5)){
            System.out.println(c.toString());
        }
    }
}
```

输出如下
```
according to for, print coffee------------
Coffee3 0
Coffee2 1
Coffee3 2
Coffee1 3
Coffee1 4
Coffee3 5
according to Iterator, print coffee------------
Coffee1 6
Coffee2 7
Coffee3 8
Coffee3 9
Coffee2 10
```


## 泛型在方法中应用

泛型方法与所在类是不是泛型类，是没有关系的，可以独立于类而产生变化。

基本指导原则：无论何时，只要你能做到，就应用尽量使用泛型方法，

也就是说如果泛型方法可以取代整个类的泛型化，那么就只使用方法的泛型，而不用将类也定为泛型类。


### 在类中定义泛型方法
```java
public class GenericMethods {
    public <T> void getClassName(T x){
        System.out.println(x.getClass().getName()+" value:"+x);
    }
}
```
泛型参数放在返回值前面

GenericMethods类，并不需要参数化，在这个只要方法拥有类型参数就行
```java
public class Test {
    public static void main(String[] args) {
        GenericMethods genericMethods = new GenericMethods();
        genericMethods.getClassName(1);
        genericMethods.getClassName(1L);
        genericMethods.getClassName(1.0f);
        genericMethods.getClassName(1.0d);
        genericMethods.getClassName("hello");
        genericMethods.getClassName('c');
    }
}
```

在使用泛型类，创建对象时需要指定类型参数，而使用泛型方法，通常不用指定参数类型，

因为编译器能为我们找出具体类型。这称为类型参数推断(type argument inference)

输出
```
java.lang.Integer value:1
java.lang.Long value:1
java.lang.Float value:1.0
java.lang.Double value:1.0
java.lang.String value:hello
java.lang.Character value:c
```

### 类型参数推断

利用泛型方法，编译器帮助我们实现类型参数的推断

下面定义集合对象创建工具，利用静态泛型方法来返回对应集合对象
```java
package generic.method;

import java.util.*;

public final class CollectionNewUtils {
    public static <T> ArrayList<T> arrayList(){
        return new ArrayList<T>();
    }
    public static <T> LinkedList<T> linkedList(){
        return new LinkedList<T>();
    }
    public static <T> HashSet<T> hashSet(){
        return new HashSet<T>();
    }
    public static <T> TreeSet<T> treeSet(){
        return new TreeSet<T>();
    }
    public static <T> LinkedHashSet<T> linkedHashSet(){
        return new LinkedHashSet<T>();
    }
    public static <T> PriorityQueue<T> priorityQueue(){
        return new PriorityQueue<>();
    }
    public static <T, K> HashMap<T,K> hashMap(){
        return new HashMap<T, K>();
    }
    public static <T, K> TreeMap<T,K> treeMap(){
        return new TreeMap<T, K>();
    }
    public static <T, K> LinkedHashMap<T,K> linkedHashMap(){
        return new LinkedHashMap<T, K>();
    }
}
```

利用编译器类型参数推断，给引用变量赋值
```java
    public static void collectionNew() {
        GenericMethods genericMethods = new GenericMethods();

        ArrayList<String> arrayList= CollectionNewUtils.arrayList();
        genericMethods.getExClassName(arrayList);

        LinkedList<Integer> linkedList= CollectionNewUtils.linkedList();
        genericMethods.getExClassName(linkedList);
    }
```

方法`CollectionNewUtils.arrayList()` 并没有给出具体的泛型类型，编译器通过引用变量的类型推断出集合对象元素类型。

注意： 类型参数推断，只对**赋值操作有效**， 其他时候不起作用。

如果你将一个泛型方法调用结果作为参数，传递给另一个方法。这是编译器不会执行类型参数推断

```java
  public void printList(ArrayList<String> list){  }

  printList(CollectionNewUtils.arrayList()); //# 编译器不能推断出正确类型，只能以Object类型赋值 list
```

### 可变类型与泛型方法

```java
public class GenericVarargs {
    public static <T> List<T> makeList(T...args){
        List<T> result= Arrays.asList(args);
        return result;
    }
}
```

```java
    public static void genericVarargs() {
        List<String> list = GenericVarargs.makeList("su", "zhen", "yun");
        System.out.println("list:"+list);
    }
```

结果：`list:[su, zhen, yun]`


### 一个通用生成器 - Generator

java编程思想 - 页397

通用生成器 - BasicGenerator
```java
public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        try{
            return type.newInstance();
        }catch(Exception e){
            throw  new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type){
        return new BasicGenerator<T>(type);
    }
}
```

`BasicGenerator(Class<T> type)`由构造器泛型参数来决定，创建何种类型对象。

`T next()` 按类型创建对象，`newInstance()`创建的类必须由默认构建方法（无参数构建方法）

编写测试代码
```java
    public static void basicGenerator() {
        Generator<Coffee1> generator = BasicGenerator.create(Coffee1.class);
        for (int i = 0; i < 3; i++) {
            Coffee1 coffee1 = generator.next();
            System.out.println(coffee1.toString());
        }
    }
```

输出
```
Coffee1 0
Coffee1 1
Coffee1 2
```

更加简化通用生成器
```java
public class ObjectGenerator {
    public static <T> T create(Class<T> t){
        try{
            return t.newInstance();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    };
}
```

```java
    public static void objectGeneratorTest() {
        Coffee coffee2 = ObjectGenerator.create(Coffee2.class);
        System.out.println(coffee2.toString());
        Coffee coffee3 = ObjectGenerator.create(Coffee3.class);
        System.out.println(coffee3.toString());
    }
```

大家只是学习下，实际上并没有简化代码

### 通过元祖工具 - Tuple

```java
public class Tuple {
    public static <A, B> TwoTuple<A, B> tuple(A a, B b){
        return new TwoTuple<A, B>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c){
        return new ThreeTuple<A, B, C>(a, b, c);
    }

    public static <A, B, C,D> FourTuple<A, B, C,D> tuple(A a, B b, C c, D d){
        return new FourTuple<A, B, C, D>(a, b, c, d);
    }
}
```

```java
    public static void tupleUtilTest() {
        TwoTuple<String, Integer> twoTuple = Tuple.tuple("zhenyun.su", 25);
        ThreeTuple<Integer, String, Long> threeTuple = Tuple.tuple(24, "hello", 23L);
        FourTuple<Integer, String, Long, Double> fourTuple = Tuple.tuple(24, "hello", 23L, 3.3);
        System.out.println("TwoTuple:" + twoTuple);
        System.out.println("ThreeTuple:" + threeTuple);
        System.out.println("FourTuple:" + fourTuple);
    }
```

```
TwoTuple:TwoTuple{first=zhenyun.su, secord=25}
ThreeTuple:ThreeTuple{three=23, first=24, secord=hello}
FourTuple:FourTuple{four=3.3, three=23, first=24, secord=hello}
```


### 通用Sets工具 - Sets

利用泛型方法，完成集合的并集，交集，删除，除去交集的集合等等集合操作
```java
public class Sets {
    //并集
    public static <T> Set<T> union(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.addAll(b);
        return result;
    }

    //交集
    public static <T> Set<T> intersection(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.retainAll(b);
        return result;
    }
    //去除集合中其他集合元素
    public static <T> Set<T> difference(Set<T> a, Set<T> b){
        Set<T> result = new HashSet<T>(a);
        result.removeAll(b);
        return result;
    }
    //去除交集元素的两个集合元素
    public static <T> Set<T> complement(Set<T> a, Set<T> b){
        return difference(union(a, b), intersection(a, b));
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        Set<Integer> a = new HashSet<Integer>(Arrays.asList(1,2,3));
        Set<Integer> b = new HashSet<Integer>(Arrays.asList(3,4,5));
        System.out.println("a: "+ a+"; b: "+ b);
        System.out.println("Sets.union(a, b): "+ Sets.union(a, b));
        System.out.println("Sets.intersection(a, b): "+ Sets.intersection(a, b));
        System.out.println("Sets.difference(a, b): "+ Sets.difference(a, b));
        System.out.println("Sets.complement(a, b): "+ Sets.complement(a, b));
    }
}
```

```
a: [1, 2, 3]; b: [3, 4, 5]
Sets.union(a, b): [1, 2, 3, 4, 5]   //并集时，去除了重复元素
Sets.intersection(a, b): [3]
Sets.difference(a, b): [1, 2]
Sets.complement(a, b): [1, 2, 4, 5]
```

## 泛型是用擦除来实现的

泛型是用擦除来实现的， 这意味着在使用泛型时，任何具体类型信息都被擦除掉，你唯一知道是你在使用一个对象，

因此List<String>和List<Integer>在运行时，事实上一个同一个类型，List

如何理解擦除及应该如何处理它，是学习java泛型的最大障碍

```java
public class HasF {
    public void f(){
        System.out.println("HasF.f()");
    };
}
```

```java
public class Mainpulator<T> {
    T obj;

    public Mainpulator(T obj) {
        this.obj = obj;
    }

    public void print(){
        obj.f(); // 编译报错，找不到 f()方法
    }

    public static void main(String[] args) {
        HasF hf= new HasF();
        Mainpulator<HasF> mainpulator = new Mainpulator<>(hf);
        mainpulator.print();
    }
}
```

由于有了擦除，java编译器无法将print()方法中用obj上调用f()这个需求映射到HasF拥有f()。

为了调用f(),需要协助泛型类，给定泛型类的边界，以告知编译器能接受边界类型。


```java
public class Mainpulator<T extends HasF> {
    T obj;

    public Mainpulator(T obj) {
        this.obj = obj;
    }

    public void print(){
        obj.f(); // 正常编译
    }

    public static void main(String[] args) {
        HasF hf= new HasF();
        Mainpulator<HasF> mainpulator = new Mainpulator<>(hf);
        mainpulator.print();
    }
}
```

给Mainpulator泛型类定义泛型类型为HasF继承。

下面我们使用接口作为泛型参数类型，来举例子
```java
public interface Animal {
    void eat();
    void swiming();
}


public class Duck implements Animal {
    @Override
    public void eat() {
        System.out.println("duck eat");
    }

    @Override
    public void swiming() {
        System.out.println("duck swiming");
    }
}

public class GenercDuck {
    public static <T extends Animal> void genercDuck(T a){
        a.eat();
    }

    public static void main(String[] args) {
        GenercDuck.genercDuck(new Duck());
    }
}
```

在GenercDuck类的泛型方法genercDuck,指定参数 T为 Animal边界类型，

这样T类型参数a才能方法中调用Animal方法
