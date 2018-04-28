# Java Reflection - Generics,Arrays, Dynamic Proxies 

chapter-18-04


		Generics
		Arrays
		Dynamic Proxies 
		Dynamic Class Loading and Reloading
		Modules

## Reflection - Generics

Car add Generics attribute List<String>, getter and setter


```
    public List<String> stringList;

    public List<String> getStringList(){
        return this.stringList;
    }

    public void setStringList(List<String> value){
        this.stringList = value;
    }
```

### Generic Method Return Types

```
            Method getMethod =  DaphneCar.class.getMethod("getStringList");
            Type returnType = getMethod.getGenericReturnType();
            System.out.println(returnType.getClass());

            if (returnType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)returnType;
                System.out.println(parameterizedType);

                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                for (Type type: typeArguments){
                    System.out.println("retrunTypeArgClass = " + (Class)type);
                }
            }
```

### Generic Method Paramter Types
```

            Method setMethod =  DaphneCar.class.getMethod("setStringList", List.class);
            Type[] genericParameterTypes = setMethod.getGenericParameterTypes();
            System.out.println(genericParameterTypes.getClass());

            for (Type genericParameterType: genericParameterTypes) {
                if (genericParameterType instanceof ParameterizedType){
                    ParameterizedType aType = (ParameterizedType)genericParameterType;
                    System.out.println(aType);
                    Type[] typeArguments = aType.getActualTypeArguments();
                    for (Type type: typeArguments){
                        System.out.println("parameterTypeArgClass = " + (Class)type);
                    }
                }
            }
```

### Generic Field  Types
```
            
            Field field = DaphneCar.class.getField("stringList");
            Type genericType = field.getGenericType();
            if (genericType instanceof ParameterizedType){
                ParameterizedType aType = (ParameterizedType) genericType;
                System.out.println(aType);
                Type[] fieldArgTypes = aType.getActualTypeArguments();
                for(Type fieldArgType : fieldArgTypes){
                    Class fieldArgClass = (Class) fieldArgType;
                    System.out.println("fieldArgClass = " + fieldArgClass);
                }
            }
```

## Reflection - Array

### Creating And Access Arrays

```
int[] intArray = (int[]) Array.newInstance(int.class, 3);

Array.set(intArray, 0, 123);
Array.set(intArray, 1, 456);
Array.set(intArray, 2, 789);

System.out.println("intArray[0] = " + Array.get(intArray, 0));
System.out.println("intArray[1] = " + Array.get(intArray, 1));
System.out.println("intArray[2] = " + Array.get(intArray, 2));
```

### Obtaining the Class Object of an Array

```
Class intArray = Class.forName("[I");

Class stringArrayClass = Class.forName("[Ljava.lang.String;");
```

The JVM represents an int via the letter I. The [ on the left means it is the class of an int array I am interested in

the [L to the left of the class name, and the ; to the right. This means an array of objects with the given type.



### Dynamic Proxies

Using Java Reflection you create dynamic implementations of interfaces at runtime. You do so using the class **java.lang.reflect.Proxy**

 Dynamic proxies can be used for many different purposes, e.g. 
 
 database connection and transaction management,
 
 dynamic mock objects for unit testing, 
 
 and other AOP-like method intercepting purposes.


使用Java Reflection，您可以在运行时创建接口的动态实现。 您可以使用类java.lang.reflect.Proxy进行操作。 这个类的名称是我将这些动态接口实现称为动态代理的原因。 动态代理可以用于许多不同的目的，例如， 数据库连接和事务管理，用于单元测试的动态模拟对象，以及其他类AOP方法拦截目的。
### Creating Proxies


### 

