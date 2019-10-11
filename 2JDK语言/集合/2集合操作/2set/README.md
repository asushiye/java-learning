# 2set
    介绍
    代码实现
    总结

## 介绍

set不保存重复的元素

一般用于快速判断一个元素是否在一个集合中，因此查找就成为set最重要的功能

Set具体和Collection完全一样的接口，只是实现逻辑不一样

## 代码实现

### set遍历
```java
    public static void forPrint(Set<Integer> sets) {
        System.out.println("第一种遍历方法，利用arrayList实现Iterable接口的forEach()方法");
        sets.forEach(animal -> {
            System.out.println(animal);
        });

        System.out.println("第二种遍历方法，利用arrayList实现Iterable接口的Iterator方法");
        Iterator<Integer> iterator = sets.iterator();
        while (iterator.hasNext()) {
            Integer name = iterator.next();
            System.out.println(name);
        }

        System.out.println("第三种遍历方法，最简单方式");
        for (Integer name : sets) {
            System.out.println(name);
        }
    }
```

### set集合基本操作
```java
package collection.set;

import java.util.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-11
 */

public class Test {
    public static void main(String[] args) {
        quickstart();
    }

    public static void quickstart() {
        Set<Integer> hashSet = new HashSet<>(47);
        Random random = new Random(30);
        for (int i = 0; i < 10000; i++) {
            hashSet.add(random.nextInt(30));
        }
        System.out.println("hashSet:" +hashSet);

        System.out.println("hashSet.contains(2): "+hashSet.contains(2));
        System.out.println("hashSet.size(): "+hashSet.size());
        System.out.println("hashSet.isEmpty(): "+hashSet.isEmpty());

        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < 10000; i++) {
            treeSet.add(random.nextInt(10));
        }
        System.out.println("treeSet:" +treeSet);

        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(99);
        System.out.println("linkedHashSet:"+linkedHashSet);

        System.out.println("1 将treeSet和linkedHashSet两个集合并集set");
        Set<Integer> set = new TreeSet<>();
        set.addAll(treeSet);
        set.addAll(linkedHashSet);
        System.out.println("1 并集 set :"+set);


        System.out.println("2 获取hashSet和treeSet两个集合中的交集retainSet");
        Set<Integer> retainSet = new HashSet<>();
        retainSet.addAll(hashSet);
        System.out.println("retainSet.retainAll(treeSet): "+retainSet.retainAll(treeSet));
        System.out.println("2 交集 retainSet: "+retainSet);

        System.out.println("3 hashSet集合除去交集retainSet的集合数据");
        Set<Integer> subSet = new HashSet<>();
        subSet.addAll(hashSet);
        subSet.removeAll(retainSet);
        System.out.println("3 除去交集的集合数据 subset:"+subSet);


        System.out.println("4 集合包含判断");
        System.out.println("set.containsAll(linkedHashSet): "+set.containsAll(linkedHashSet));
        System.out.println("hashSet.containsAll(set): "+hashSet.containsAll(set));

    }
}

```

输出
```
hashSet:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
hashSet.contains(2): true
hashSet.size(): 30
hashSet.isEmpty(): false
treeSet:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
linkedHashSet:[99]
1 将treeSet和linkedHashSet两个集合并集set
1 并集 set :[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 99]
2 获取hashSet和treeSet两个集合中的交集retainSet
retainSet.retainAll(treeSet): true
2 交集 retainSet: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
3 hashSet集合除去交集retainSet的集合数据
3 除去交集的集合数据 subset:[10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29]
4 集合包含判断
set.containsAll(linkedHashSet): true
hashSet.containsAll(set): false;
```


## 总结

HashSet按散列函数来存储数据，存储顺序按由算法和元素值决定
TreeSet按红黑树来存储数据，按升序来存储和读取
LinkedHashSet按链表的存储数据，按插入顺序来存储和读取
