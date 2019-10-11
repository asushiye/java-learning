# 5 map

    介绍
    代码实现
    总结

## 介绍

map将对象映射到其他对象的能力是一种常用解决编程问题。

例如
```java
package collection.map;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        quickStart();
    }

    public static void quickStart() {
        Map<Integer,  Integer> map = new HashMap<>();
        Random random = new Random(37);
        for (int i = 0; i < 1000; i++) {
            Integer rand =  random.nextInt(5);
            Integer value = map.get(rand);
            map.put(rand, map.containsKey(rand) ? (value+1) : 1);
        }
        System.out.println(map);
    }
}
```
输出
```
{0=221, 1=206, 2=176, 3=192, 4=205}
```


## 代码实现

### map遍历

```java
    public static void forPrint(Map<String, Object> map){
        System.out.println("第一种遍历方法，利用Map实现Iterable接口的forEach()方法");
        map.forEach((key, value)->{
            System.out.println("key="+key+"; value="+value);
        });

        System.out.println("第二种遍历方法，通过key集合遍历来获取值方法");
        Set<String> sets = map.keySet();
        //在上面中演示三种遍历方法
        for (String set:sets){
            System.out.println("key="+set+"; value="+map.get(set));
        }
    }
```

### map基本操作
```java
package collection.map;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Test {
    public static void main(String[] args) {
        quickStart();
    }

    public static void map() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhenyun.su");
        map.put("age", 20);
        map.put("gender", true);
        System.out.println(map);
        System.out.println("map.size(): "+map.size());
        System.out.println("map.clear()");
//        map.clear();
        System.out.println("map.isEmpty(): "+map.isEmpty());


        System.out.println("map.get(): "+map.get("name"));  // 获取name键的值，若没有返回null
        System.out.println("map.getOrDefault(): "+map.getOrDefault("title", "not title")); // 获取name键的值，若没有返回默认值
        System.out.println("map.containsKey: "+map.containsKey("name"));
        Set<String> sets = map.keySet();  // 获取所有键的集合set，若没有返回空值数组[]
        System.out.println("map.keySet(): "+sets);
        Collection<Object> values = map.values(); // 获取所有值的集合collection，若没有返回空值数组[]
        System.out.println("map.values(): "+values);

        forPrint(map);
    }
}
```
输出
```
{gender=true, name=zhenyun.su, age=20}
map.size(): 3
map.clear()
map.isEmpty(): false
map.get(): zhenyun.su
map.getOrDefault(): not title
map.containsKey: true
map.keySet(): [gender, name, age]
map.values(): [true, zhenyun.su, 20]
第一种遍历方法，利用Map实现Iterable接口的forEach()方法
key=gender; value=true
key=name; value=zhenyun.su
key=age; value=20
第二种遍历方法，通过key集合遍历来获取值方法
key=gender; value=true
key=name; value=zhenyun.su
key=age; value=20
```

### 嵌套map

```java
    public static void nestMap() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "zhenyun.su");
        map1.put("age", 20);
        map1.put("gender", true);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "cuiyun.shi");
        map2.put("age", 30);
        map2.put("gender", false);

        Map<Integer, Object> nestMap = new HashMap<>();
        nestMap.put(1, map1);
        nestMap.put(2, map2);
        System.out.println(nestMap);
    }
```
输出
```
{1={gender=true, name=zhenyun.su, age=20}, 2={gender=false, name=cuiyun.shi, age=30}}
```

## 总结

269 页  --queue
