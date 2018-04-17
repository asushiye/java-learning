# Java Generics Tutorial

		Java Generics Example
		Generic List in Java
		Generic Set in Java
		Generic Map in Java


The Java Generics features were added to the Java language from Java 5.
Generics add a way to specify concrete types to general purpose classes and methods that operated on Object before

## Java Generics Example

The List interface represents a list of Object instances. This means that we could put any object into a List. 
```
List list = new ArrayList();
list.add(new Integer(2));
list.add("a String");

Integer integer = (Integer) list.get(0);
String string   = (String) list.get(1);
```

you only keep String's or something else in the collection

```
List<String> strings = new ArrayList<String>();
strings.add("a String");
String aString = strings.get(0);
```

## Generic List in Java

The Java generics features were updated in Java 7. From Java 7 the Java compiler can infer the type of the collection instantiated from the variable the collection is assigned to. 
Here is a Java 7 generics example:

`List<String> strings = new ArrayList<>();`


请注意，ArrayList的泛型类型已被省略。 相反只有<>写了。 这也有时被称为钻石操作符。 
当你只是将钻石操作符编写为泛型类型时，Java编译器将假定实例化的类与它所分配的变量具有相同的类型




### List Generics for Loop

Java's List interface (java.util.List) can be generified

```
List<String> strings = new ArrayList<String>();

//... add String instances to the strings list...

for(String aString : strings){
  System.out.println(aString);
}
```

### Iterating a Generic List

```
List<String> list = new ArrayList<String>;

Iterator<String> iterator = list.iterator();

while(iterator.hasNext()){
  String aString = iterator.next();
}
```

## Generic Set in Java
Java's Set interface (java.util.Set) can be generified

`Set<String> set = new HashSet<String>;`

### Adding Elements to a Generic Set

Adding elements to a generic Set is done using the add() method, just like you have always done:

```
Set<String> set = new HashSet<String>;
String string1 = "a string";
set.add(string1);
```

### Iterating a Generic Set
```
Set<String> set = new HashSet<String>;
    
Iterator<String> iterator = set.iterator();

while(iterator.hasNext()){
  String aString = iterator.next();
}
```

### Set Generics for Loop
You can also use the new for-loop, like this:

```
Set<String> set = new HashSet<String>;

for(String aString : set) {
    System.out.println(aString);
}
```


## Generic Map in Java

Java's Map interface (java.util.Map) can be generified

`Map<Integer, String> set = new HashMap<Integer, String>;`

### Map Generics for Loop
```
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "value1");
        map.put(2, "value2");
        for(Integer key: map.keySet()){
            System.out.println(key+": "+map.get(key));
        }
        for (String value: map.values()) {
            System.out.println(value);
        }
```
### Iterating a Generic map

```
        Iterator<Integer> keys =  map.keySet().iterator();
        while (keys.hasNext()){
            System.out.println(keys.next()+"= "+map.get(keys.next()));
        }

        Iterator<String> values = map.values().iterator();
        while (values.hasNext()){
            System.out.println(values.next());
        }
```

