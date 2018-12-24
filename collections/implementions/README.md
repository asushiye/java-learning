# collection implementions

		List实现类
		Set实现类
		Map实现类
		集合实现类比较

collection implementions class

下面将介绍常用集合实现类

## List实现类

List Implementations

> * java.util.ArrayList
> * java.util.LinkedList
> * java.util.Vector
> * java.util.Stack

ArrayList Class hierarchy
![arrayList](arrayList.png)

```
List listA = new ArrayList();
List listB = new LinkedList();
List listC = new Vector();
List listD = new Stack();
```

* Adding and Accessing Elements

```
List listA = new ArrayList();
listA.add("element 1");
listA.add("element 2");

String element0 = listA.get(0);
String element1 = listA.get(1);
```

* Removing Elements
> 1. remove(Object element)
> 2. remove(int index)



* Clearing a List
> list.clear();
* List Size
> int size = list.size();
* Generic Lists

```
MyObject myObject = list.get(0);

for(MyObject anObject : list){
   //do someting to anObject...
}
```

* Iterating a List

```
Iterator iterator = listA.iterator();
while(iterator.hasNext(){
  String element = (String) iterator.next();
}


//access via new for-loop
for(Object object : listA) {
    String element = (String) object;
}
```

## Set实现类

Set Implementations

> * java.util.EnumSet
> * java.util.HashSet
> * java.util.LinkedHashSet
> * java.util.TreeSet

HashSet Class hierarchy
![hashset](hashset.png)

```
Set setA = new EnumSet();
Set setB = new HashSet();
Set setC = new LinkedHashSet();
Set setD = new TreeSet();
```

Adding and Accessing , remote, clear and iterator Elements same list

```
Set setA = new HashSet();

setA.add("element 0");
setA.add("element 1");
setA.add("element 2");

//access via Iterator
Iterator iterator = setA.iterator();
while(iterator.hasNext(){
  String element = (String) iterator.next();
}


//access via new for-loop
for(Object object : setA) {
    String element = (String) object;
}

```

## Java Collections - SortedSet


put out set item for sorted aec

```

    public static void main(String[] args){
        SortedSet<Integer> sset = new TreeSet<>();
        sset.add(2);
        sset.add(1);
        sset.add(3);
        Iterator<Integer> iterator= sset.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
```

## Map实现类

hashMap Class hierarchy
![hashMap](hashMap.png)

## 集合实现类比较

|集合类|结构|描述|
|-|-|-|
|ArrayList|列表结构|遍历速度快，CRUD操作相对慢，通过下标访问元素最快|
|LinkedList|链表结构|CRUD操作相对快，只需要改变前后两个节点指针指向，输出顺序和输入顺序一致|
|HashSet|集合结构|存储无序的，唯一的，可包含唯一一个Null对象，通过foreach和迭代器来遍历元素|
|HashMap|键值对集合结构||

