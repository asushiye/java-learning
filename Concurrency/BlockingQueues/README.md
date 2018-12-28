# Java Blocking Queues  ----- 阻塞队列

		什么是阻塞队列
		阻塞队列实现逻辑


## 什么是阻塞队列

阻塞队列是一个什么样队列呢？

1. 尝试从空队列中出队的线程被阻塞，直到某个其他线程将项目插入队列。 
2. 尝试往满的队列中的插入项的线程将被阻塞，直到某个其他线程将一个或多个项目移除出队列或完全清除队列。

下面的图表，显示了通过阻塞队列协作的两个线程

![blockqueue](blockqueue.png)

线程1往阻塞队列中新增数据

线程2从阻塞队列中获取数据

## 阻塞队列实现逻辑

```
public class BlockingQueue {

  private List queue = new LinkedList();
  private int  limit = 10;

  public BlockingQueue(int limit){
    this.limit = limit;
  }


  public synchronized void enqueue(Object item)
  throws InterruptedException  {
    while(this.queue.size() == this.limit) {
      wait();
    }
    if(this.queue.size() == 0) {
      notifyAll();
    }
    this.queue.add(item);
  }


  public synchronized Object dequeue()
  throws InterruptedException{
    while(this.queue.size() == 0){
      wait();
    }
    if(this.queue.size() == this.limit){
      notifyAll();
    }

    return this.queue.remove(0);
  }

}
```