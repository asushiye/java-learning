# 2集合操作

## 集合初始化


```java
    public static void init() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5);
        intList.set(0, 99);
        System.out.println(intList);
        intList.add(6); //不能新增元素，异常 UnsupportedOperationException

        Collection<Integer> collections = new ArrayList<>(intList);
        collections.add(6);
        Integer[] ints = {7, 8, 9};
        collections.addAll(Arrays.asList(ints));
        System.out.println(collections);
    }
```

通过Arrays.asList返回List，该返回是固定长度的List是不能添加新的元素

将List添加到集合Collection中，利用collections.addAll()可以添加其他集合的元素


## 添加元素

```java
package collection;

import java.util.Collection;
import java.util.Map;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-10
 */

public class FillCollection {

    static Collection fill(Collection<String> collection){
        collection.add("rat");
        collection.add("dog");
        collection.add("cat");
        return collection;
    }

    static Map fill(Map<String, String> map){
        map.put("name", "zhenyun.su");
        map.put("country", "china");
        map.put("post", "programmer");
        return map;
    }
}
```

```java
    public static void printCollection(){
        System.out.println((FillCollection.fill(new ArrayList<String>())));
        System.out.println((FillCollection.fill(new LinkedList<String>())));
        System.out.println((FillCollection.fill(new HashSet<String>())));
        System.out.println((FillCollection.fill(new TreeSet<String>())));
        System.out.println((FillCollection.fill(new LinkedHashSet<String>())));
        System.out.println((FillCollection.fill(new HashMap<String, String>())));
        System.out.println((FillCollection.fill(new TreeMap<String, String>())));
        System.out.println((FillCollection.fill(new LinkedHashMap<String, String>())));
    }
```

输出如下
```
[rat, dog, cat]
[rat, dog, cat]
[rat, cat, dog]
[cat, dog, rat]
[rat, dog, cat]
{country=china, post=programmer, name=zhenyun.su}
{country=china, name=zhenyun.su, post=programmer}
{name=zhenyun.su, country=china, post=programmer}
```
#### list集合
* ArrayList 随机访问快，当时插入或 删除元素慢
* LinkedList 相对ArrayList正好相反
#### set集合
* HashSet 采用比较复杂方式来存储元素，不过访问速度最快
* TreeSet 采用升序方式来存储元素
* LinkedHashSet 采用插入顺序方式来存储元素
#### map集合
* HashMap 采用比较复杂方式来存储元素，不过访问速度最快
* TreeMap 采用升序方式来存储元素
* LinkedHashMap 采用插入顺序方式来存储元素


java编程思想 - 256页
