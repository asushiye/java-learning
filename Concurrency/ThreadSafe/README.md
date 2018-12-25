#  Thread Safe  ------ 线程安全

		竞争状态和临界区
		避免竞争状态
		临界区的吞吐量


## 竞争状态和临界区

从下面实例来了解多线下竞争条件和临界区
```
 public class Counter {

     protected long count = 0;

     public void add(long value){
         this.count = this.count + value;
     }
  }
```

想象一下如果两个线程A和B正在执行同一个Conter类的实例的add方法。没有办法知道操作系统什么时候在这两个线程间切换。

在add()方法中的代码不会被java虚拟机当作单原子操作。它被当为一些列更小的指令执行，类似于这样：

* Read this.count from memory into register(寄存器).
* Add value to register.
* Write register to memory.

现在有 两个线程A和B在混合执行

```
    this.count = 0;

   A:  Reads this.count into a register (0)
   B:  Reads this.count into a register (0)
   B:  Adds value 2 to register
   B:  Writes register value (2) back to memory. this.count now equals 2
   A:  Adds value 3 to register
   A:  Writes register value (3) back to memory. this.count now equals 3
```

在上面的执行顺序例子中，两个线程从内存中读取0，接着它们增加了各自的值2和3，并且写回到内存。

最终的结果是最后的线程写入的值，而不是值5.在上面的例子中，是线程A的值，但也可是是线程B的。

**结论**

在两个线程竞争相同资源的情况下，访问资源顺序很重要，则该资源(count)称之为竞争状态。

导致竞争状态的代码块(add)成为临界区

## 避免竞争状态

为了避免发生竞争状态必须保证 **临界区作为原子操作执行**。

意思是说一旦一条线程在执行它，没有其他线程可以执行它，直到第一条线程释放了临界区。

通过 **线程同步** 避免竞争状态，线程同步可以使用java代码中的同步锁来实现。

线程同步也可以使用其他的同步构造器，例如锁或者原子变量，像java.util.concurrent.atomic.AtomicInteger

## 临界区的吞吐量

对于更小的临界区，作为整个临界区是肯能的。

但是，对于较大的临界区，分割为较小的临界区，允许多个线程去执行更小的临界区是可能是有好处的。

这降低了对于共享资源的竞争，以提高整个临界区的吞吐量。 

这里是一个非常简单的java代码例子：

```
public class TwoSums {

    private int sum1 = 0;
    private int sum2 = 0;

    public void add(int val1, int val2){
        synchronized(this){
            this.sum1 += val1;   
            this.sum2 += val2;
        }
    }
}
```

注意add()方法是怎么向不同的sum成员变量增加值的。为了避免竞争状态，求和在java同步块中执行。

通过这种实现，只有一个线程可以求和操作，在同一时刻。 

然而，因为这两个成员变量相互是独立的，你可以把求和的方法分为两个同步块，像这样：

```
public class TwoSums {

    private int sum1 = 0;
    private int sum2 = 0;

    private Integer sum1Lock = new Integer(1);
    private Integer sum2Lock = new Integer(2);

    public void add(int val1, int val2){
        synchronized(this.sum1Lock){
            this.sum1 += val1;   
        }
        synchronized(this.sum2Lock){
            this.sum2 += val2;
        }
    }
}

```

现在两个线程可以同时执行add()方法。第一个同步块的线程，第二个同步块的线程，两个同步块在不同的对象上同步，

因此，不同的线程可以独立地执行两个代码块。这种方式，线程可以更少等待执行add()方法


## 线程安全

我们知道当多个线程正在访问同一资源，并且一个或多个线程写入资源时，才会出现竞争条件。 

如果多个线程读取相同的资源竞争条件，则不会发生。

通过多线程共享的对象永远不会被任何线程更新，方法是使共享对象不可变，从而保证线程安全

```
public class ImmutableValue{

  private int value = 0;

  public ImmutableValue(int value){
    this.value = value;
  }

  public int getValue(){
    return this.value;
  }
}

上面类中没有提供setValue的方法，也就共享对象value是不可变的，就能保证线程安全。



