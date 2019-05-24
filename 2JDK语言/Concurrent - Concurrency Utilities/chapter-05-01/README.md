# BlockingQueue

chapter-05-01


the **java.util.concurrent package**. 
This package contains a set of classes that makes it easier to develop concurrent (multithreaded) applications in Java

# BlockingQueue

		BlockingQueue Usage
		BlockingQueue Implementations
		Java BlockingQueue Example

The Java BlockingQueue interface in the java.util.concurrent package represents a queue which is thread safe to put into, 
and take instances from. 
In this text I will show you how to use this BlockingQueue.

A BlockingQueue is typically used to have on thread produce objects, which another thread consumes

Thread1 put item to BlockingQueue begin point, other Thread2 take item from BlockingQueue end point

The producing thread will keep producing new objects and insert them into the queue, until the queue reaches some upper bound on what it can contain. 
It's limit, in other words. If the blocking queue reaches its upper limit, 
the producing thread is blocked while trying to insert the new object. 
It remains blocked until a consuming thread takes an object out of the queue.

The consuming thread keeps taking objects out of the blocking queue, and processes them. 
If the consuming thread tries to take an object out of an empty queue,
the consuming thread is blocked until a producing thread puts an object into the queue.

>创建一个新对象插入队列中，如果已经满了，则线程处于阻塞状态，直到队列有对象被消费，而清出空间时，才能插入。

>从队列中消费一个对象，如果队列已经空了，则线程处理阻塞状态，直到队列有新对象被插入，才能消费。

## BlockingQueue Implementations

* ArrayBlockingQueue
* DelayQueue
* LinkedBlockingQueue
* PriorityBlockingQueue
* SynchronousQueue


## Java ArrayBlockingQueue Example

```
public class MyBlockingQueue {
    public static void main(String[] args)
    {
        try {
            BlockingQueue blockingQueue = new ArrayBlockingQueue(3);

            MyProducer producer = new MyProducer(blockingQueue);
            MyConsumer consumer =  new MyConsumer(blockingQueue);

            new Thread(producer).start();
            new Thread(consumer).start();

            Thread.sleep(4000);

        }catch (InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }
}
```

```
public class MyProducer implements Runnable {

    protected BlockingQueue queue = null;

    public MyProducer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6 ; i++) {
                queue.put(String.valueOf(i));
                System.out.println("product: "+i);
            }
        }catch (InterruptedException  e){
            System.out.println(e.getStackTrace());
        }
    }
}

```

```
public class MyConsumer implements Runnable{

    protected BlockingQueue queue = null;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 6; i++) {
                System.out.println("consumer: "+queue.take());
            }
        }catch (InterruptedException  e){
            System.out.println(e.getStackTrace());
        }
    }

    public MyConsumer(BlockingQueue queue) {
        this.queue = queue;
    }
}
```


## Java ArrayBlockingQueue Example
