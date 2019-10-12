# 3 queue - 列队

    介绍
    代码实现
    总结

## 介绍

queue是一个典型的先进先出FIFO的集合容器，即从容器一端放入元素，从另外一端取出，

并且元素放入容器顺序和取出顺序是相同的，它可以安全的将对象从一个任务传输给另外一个任务。

列队在并发编程中特别重要

由于LinkedList除了实现List接口外，也实现了Queue接口，因此我们可以用LinkedList来实现列队的功能



## 代码实现

在有些场景中，列队元素，按一定规则需要被优先被处理，我们可以利用具有排序功能列队来实现

而优先级列队**PriorityQueue**正好能满足，下面使用优先级列队来讲解

### 列队遍历

```java
    public static void forPrint(Queue<String> queue){
        System.out.println("第一种遍历方法，利用实现Iterable接口的forEach()方法");
        queue.forEach(animal -> {
            System.out.println(animal);
        });

        System.out.println("第二种遍历方法，利用实现Iterable接口的Iterator方法");
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name);
        }

        System.out.println("第三种遍历方法，最简单方式");
        for (String name : queue) {
            System.out.println(name);
        }

        System.out.println("第四种遍历方法，使用for循环方式");
        while(queue.size()>=1){
            System.out.println(queue.poll());
        }
    }
```


### 列队操作

```java
package collection.queue;


import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-12
 */

public class Test {
    public static void main(String[] args) {
        priorityQueue(); //优先级列队
    }

    public static void priorityQueue() {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("dog"); //从头插入
        priorityQueue.offer("elephant"); //从尾插入
        priorityQueue.offer("lion"); //从尾插入
        priorityQueue.add("cat");
        System.out.println("priorityQueue: " + priorityQueue);

        System.out.println("priorityQueue.size(): " + priorityQueue.size());
        System.out.println("priorityQueue.isEmpty(): " + priorityQueue.isEmpty());

        System.out.println("priorityQueue.peek(): " + priorityQueue.peek()+"; priorityQueue: "+priorityQueue); //获取列头元素，若空，则返回null
        System.out.println("priorityQueue.element(): " + priorityQueue.element()+"; priorityQueue: "+priorityQueue);//获取列头元素，若空，则返回异常NoSuchElementException

        System.out.println("priorityQueue.poll(): " + priorityQueue.poll()+"; priorityQueue: "+priorityQueue); //获取列头元素，并删除，若空，则返回null
        System.out.println("priorityQueue.poll(): " + priorityQueue.remove()+"; priorityQueue: "+priorityQueue); //获取列头元素，并删除，若空，则返回异常NoSuchElementException
        forPrint(priorityQueue);
    }
}

```
输出
```
priorityQueue: [cat, dog, lion, elephant]
priorityQueue.size(): 4
priorityQueue.isEmpty(): false
priorityQueue.peek(): cat; priorityQueue: [cat, dog, lion, elephant]
priorityQueue.element(): cat; priorityQueue: [cat, dog, lion, elephant]
priorityQueue.poll(): cat; priorityQueue: [dog, elephant, lion]
priorityQueue.poll(): dog; priorityQueue: [elephant, lion]
第一种遍历方法，利用实现Iterable接口的forEach()方法
elephant
lion
第二种遍历方法，利用实现Iterable接口的Iterator方法
elephant
lion
第三种遍历方法，最简单方式
elephant
lion
第四种遍历方法，使用for循环方式
elephant
lion

```

## 总结
