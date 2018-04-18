# Java Collections  and collections interface


The Java Collections API's provide Java developers with a set of classes and interfaces that makes it easier to handle collections of objects

		Overview of Java Collections
		The Central Java Collection Interfaces
		Java Collections and Generics
		Java Collections and the equals() and hashCode() Methods


## Java Collections - Overview
 it is useful to have an overview of the interfaces it contains


 there are two "groups" of interfaces: Collection's and Map's.

###the Collection interface hierarchy


>Iterable -> iterator()

>>Collection -> size()

>>>List    Set(SortedSet, NavigableSet)   Queue(Deque)


###the Map interface hierarchy
>Map->(keySet() values())
>>SortedMap
>>>NavigableMap


## Java Collections - Iterable

The Iterable interface (*java.lang.Iterable*) is one of the root interfaces of the Java collection classes. 
The Collection interface extends Iterable, so all subtypes of Collection also implement the Iterable interface

```
List list = new ArrayList();

for(Object o : list){
    //do something o;    
}
```

The Iterable interface has only one method:
```
public interface Iterable<T> {
  public Iterator<T> iterator();    
}
```

## Java Collections - Collection

The Collection interface (java.util.Collection) is one of the root interfaces of the Java collection classes. 

1. Collection Subtypes
2. Adding and Removing Elements
3. Checking if a Collection Contains a Certain Element
4. Collection Size
5. Iterating a Collection

### 1. Collection Subtypes

* List
* Set
* SortedSet
* NavigableSet
* Queue
* Deque


```
public class MyCollectionUtil {

    public static void printlnItem(Collection collection){
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}


public class MyMain {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("zhenyun.su");

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        MyCollectionUtil.printlnItem(list);
        MyCollectionUtil.printlnItem(set);
    }
}

```

### Adding and Removing Elements

```
public class MyCollectionUtil {

    public static void addItem(Collection collection, Object o){
        collection.add(o);
    }

    public static void clearItem(Collection collection){
        collection.clear();
    }

    public static void remoteItem(Collection collection, Object o){
        collection.remove(o);
    }
}

public class MyMain {
    public static void main(String[] args){
        List<String> list = new ArrayList<>();
        list.add("zhenyun.su");

        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "asu1");
        map.put(2, "asu2");

        MyCollectionUtil.clearItem(list);
        MyCollectionUtil.addItem(list, "asushiye");
        MyCollectionUtil.printlnItem(list);

        MyCollectionUtil.remoteItem(set, 2);
        MyCollectionUtil.printlnItem(set);
        MyCollectionUtil.printlnItem(map.entrySet());

    }
}

```
addAll Collection from other collection

```
        Collection collection = new HashSet();
        collection.addAll(set);
        MyCollectionUtil.printlnItem(collection);
        collection.removeAll(set);
```


### Checking if a Collection Contains a Certain Element and collection size

```
        System.out.println(collection.contains(1));
        collection.removeAll(set);
        System.out.println(collection.contains(1));
	int numberOfElements = collection.size();
```

## Java Collections - Generic Collections

²Î¿¼£ºJavaGenerics


