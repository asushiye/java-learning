# Thread Signaling   ------ 线程信号

		概念
		如何实现线程信号
		忙碌等候信号


## 概念

线程信号目的是使线程能够相互发送信号。 

另外线程信号使线程能够等待来自其他线程的信号。 

例如，线程B可能等待来自线程A的信号，指示数据已准备好被处理。

## 如何实现线程信号

通过共享对象发送信号

线程相互发送信号的一种简单方法是在某个共享对象中设置信号值。 

线程A可以从同步块内部将布尔成员变量hasDataToProcess设置为true，并且线程B可以读取同步块内的hasDataToProcess成员变量。 

下面是一个可以容纳这种信号的对象的简单示例，并提供了设置和检查它的方法：

```
public class MySignal{

  protected boolean hasDataToProcess = false;

  public synchronized boolean hasDataToProcess(){
    return this.hasDataToProcess;
  }

  public synchronized void setHasDataToProcess(boolean hasData){
    this.hasDataToProcess = hasData;  
  }

}
```

线程A和B必须同时使用相同的MySignal实例才能使信号生效。 

如果线程A和B具有对不同MySignal实例的引用，则它们将不会检测彼此的信号。

## 忙碌等候信号

线程B等候线程A的处理完成数据的信号，若收到hasDataToProcess为true的信号，则线程B就能继续执行。

```
protected MySignal sharedSignal = ...

...

while(!sharedSignal.hasDataToProcess()){
  //do nothing... busy waiting
}
```

这种忙碌等候信号不利于CPU资源的使用，除非平均等候时间很短。

如果等候信号时间很长，以某种方式将等待线程进入睡眠或非活动状态

Java内部机制，可以让线程进入非活动状态

java.lang.Object类定义了三个方法，wait（），notify（）和notifyAll（）来实现

在任何对象上调用wait（）的线程变为非活动状态，直到另一个线程在该对象上调用notify（）。

调用线程必须从同步块内部调用wait（）或notify（）



以后在详细学习