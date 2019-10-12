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

    public static void listSort(Queue<String> queue) {
        System.out.println("queue sort begin: "+queue);
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(new MyComparator());
        priorityQueue.addAll(queue);
        System.out.println("queue sort end: "+priorityQueue);
    }
}
