package collection.list;

import collection.FillCollection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-11
 */

public class Test {
    public static void main(String[] args) {
        arrayList();
//        linkedList();
    }

    public static void arrayList() {
        List<String> smallAnimals = new ArrayList<>();
        smallAnimals.add("dog");
        smallAnimals.add("cat");
        System.out.println("1 smallAnimals add dog,cat: " + smallAnimals);

        System.out.println("1 smallAnimals dog index: " + smallAnimals.indexOf("cat"));
        String dog = smallAnimals.get(0);
        smallAnimals.remove(dog);
        System.out.println("1 smallAnimals remove dog: " + smallAnimals);
        System.out.println("1 smallAnimals.contains(dog): " + smallAnimals.contains(dog));
        System.out.println("1 smallAnimals.isEmpty(): " + smallAnimals.isEmpty());
        System.out.println("1 smallAnimals.size(): " + smallAnimals.size());
        smallAnimals.clear();
        System.out.println("1 smallAnimals.clear():" + smallAnimals);
        smallAnimals.add("dog");
        smallAnimals.add("cat");

        List<String> bigAnimals = new ArrayList<>();
        bigAnimals.add("elephant");
        bigAnimals.add("lion");
        System.out.println("2 bigAnimals add elephant, lion: " + bigAnimals);

        List<String> animals = new ArrayList<>();
        animals.addAll(smallAnimals);
        animals.addAll(bigAnimals);
        System.out.println("2 animals add smallAnimals, bigAnimals : " + animals);

        System.out.println("2 animals.containsAll(bigAnimals) is " + animals.containsAll(bigAnimals));
        System.out.println("2 smallAnimals.containsAll(bigAnimals) is " + smallAnimals.containsAll(bigAnimals));

        List<String> subAnimals = animals.subList(2, animals.size());
        System.out.println("3 get sub animals from animals : " + subAnimals);

        forPrint(animals);

        listSort(animals);
    }

    public static void linkedList() {
        LinkedList<String> animals = new LinkedList<>();
        animals.addFirst("cat");  //addFirst 从列头添加数据
        animals.addFirst("dog");
        System.out.println("1 animals add cat, dog to list begin:" + animals);

        animals.addLast("elephant"); //addLast 从列尾添加数据
        animals.addLast("lion");
        System.out.println("1 animals add elephant, lion to list end:" + animals);


        System.out.println("2 animals.getFirst(): " + animals.getFirst()); //getFirst 获取列头，但不删除, 若空，异常NoSuchElementException
        System.out.println("2 animals.getFirst(): " + animals.getFirst());
        System.out.println("2 animals.getLast(): " + animals.getLast()); //getLast 获取列头，但不删除, 若空，异常NoSuchElementException
        System.out.println("2 animals.getLast(): " + animals.getLast());

        System.out.println("2 animals.peek(): " + animals.peek()); //peek 获取列头，但不删除, 若空,返回 null
        System.out.println("2 animals.peekLast(): " + animals.peekLast());

        System.out.println("2 animals.poll(): " + animals.poll() + "; animals: " + animals); //弹出列头元素，并删除, 若空,返回 null
        System.out.println("2 animals.pollLast(): " + animals.pollLast() + "; animals: " + animals);//弹出列尾元素，并删除, 若空,返回 null

        System.out.println("2 animals.removeFirst(): " + animals.removeFirst() + "; animals: " + animals); //弹出列头元素，并删除, 若空，异常NoSuchElementException
        System.out.println("2 animals.pop(): " + animals.pop() + "; animals: " + animals); //pop()调用removeFirst方法

        animals.add("dog");
        animals.add("cat");
        forPrint(animals);

        listSort(animals);
    }

    public static void forPrint(List<String> list) {
        System.out.println("第一种遍历方法，利用arrayList实现Iterable接口的forEach()方法");
        list.forEach(animal -> {
            System.out.println(animal);
        });

        System.out.println("第二种遍历方法，利用arrayList实现Iterable接口的Iterator方法");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name);
        }

        System.out.println("第三种遍历方法，最简单方式");
        for (String name : list) {
            System.out.println(name);
        }

        System.out.println("第四种遍历方法，使用for循环方式");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + "= " + list.get(i));
        }
    }

    public static void listSort(List<String> list) {
        System.out.println("4 animals sort start: " + list);
        list.sort(new MyComparator());
        System.out.println("4 animals sort end: " + list);
    }
}