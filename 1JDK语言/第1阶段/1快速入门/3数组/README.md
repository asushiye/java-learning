# 3数组
    概念
    数组
      基本属性
      定义数组
      数组遍历
    数组间操作
      Arrays工具
      复制数组

## 概念

数组与其他容器的最大区别，数组是效率最高的存储和访问速度最快的一个线性序列结构的类型。

效率高是因为数组对象大小是固定的，并且在生命周期内不能改变，

也许你会建议使用ArrayList它是通过新建一个实例，

然后从旧的实例拷贝过来，从而实现更多空间的自动分配，因此它的效率比数组低很多。

由于数组在定义时就指定存储的固类型来存储，因此它在编译器就能自动检查不正确类型


## 数组

### 基本属性

length用于获取数组长度，使用下标来获取数组元素，使用Arrays.toString(数组)来输出字符串。

### 定义数组

```java
    public static void base() {
        Integer[] int1 = new Integer[3];
        int[] int2 = new int[3];
        Integer[] int3 = {0, 1 , 2};
        int1[1] = 3;
        int2[1] = 3;
        System.out.println(int1.length);
        System.out.println(Arrays.toString(int1));
        System.out.println(Arrays.toString(int2));
        System.out.println(Arrays.toString(int3));
    }
```

输出
```
3
[null, 3, null]
[0, 3, 0]
[0, 1, 2]
```

数组定义格式：
```
类型[]  数组名称=  new 类型[size];   //size表示数组长度，必须填写
类型[]  数组名称=  {value, value, value};  //如果类型是基本类型，value为常量值；如果为应用类型，value为对象实例
```

数组默认值，数组定义了，但是若没有赋值前的值
```
1. 如果数组为基本类型， int = 0, boolean = false, long = 0L, double =0.0D等
2. 如果数组为引用类型，所有值为null值
```

数组输出, 通过 `Arrays.toString(数组)` 格式 [value1, value2...]

### 数组遍历

数组支持下面两种方式遍历，不支持foreach(使用其他集合容器，需要实现迭代器)
```java
    public static void print() {
        Integer[] int3 = {0, 1 , 2};
        for(Integer i:int3){
            System.out.println(i);
        };
        for (int i = 0; i < int3.length; i++) {
            System.out.println(int3[i]);
        }
    }
```

## 数组间操作

### Arrays工具
```java
    public static void testArrays(){
        Integer[] int3 = {0, 2 , 1};
        Arrays.fill(int3, 1, 2, 5);
        System.out.println(Arrays.toString(int3));   // [0, 5, 1]
        Integer[] int4 = {0, 5 , 1};
        System.out.println(Arrays.toString(int4));   // [0, 5, 1]
        System.out.println(Arrays.equals(int3, int4)); // false

        Arrays.sort(int4);
        Arrays.sort(int3, 0, int3.length, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1>o2 ? -1: o1<o2 ? 1: 0;
            }
        });
        System.out.println(Arrays.toString(int3)); // [5, 1, 0]
        System.out.println(Arrays.toString(int4)); // [0, 1, 5]
        System.out.println(Arrays.binarySearch(int3, 5));  // -4  错误
        System.out.println(Arrays.binarySearch(int4, 5));  // 2  这个值正常的
        System.out.println(Arrays.hashCode(int3));  // 34627
        System.out.println(Arrays.hashCode(int4));  // 29827
        List<Integer> list = Arrays.asList(int3);
        System.out.println(list); // [5, 1, 0]
    }
```

常用六个函数
```
Arrays.toString 以[]格式输出字符串
Arrays.fill 使用一个固定常量值填充数组，特别说明 填充到参数toIndex，并不包含toIndex
Arrays.sort 默认排升序排序，可以添加比较器来实现降序
Arrays.binarySearch 按值搜索数组下标位置，只能应用升序排序的数组，否则返回值不对
Arrays.hashCode 获取数组hashCode值
Arrays.asList 根据数组返回List容器
Arrays.equals 数组比较，比较符合两个规则，数组长度一致，数组元素必须相等，如果多维数组，请使用deepEquals方法
```

### 复制数组

System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length);
```java
    public static void arrayCopy(){
        Integer[] int3 = {0, 2 , 1, 3};
        Integer[] int4 = new Integer[5];
        System.out.println(Arrays.toString(int3));   // [0, 2, 1, 3]
        System.out.println(Arrays.toString(int4));   // [null, null, null, null, null]
        System.arraycopy(int3, 1, int4, 2, int3.length-1);
        System.out.println(Arrays.toString(int4));   // [null, null, 2, 1, 3]
    }
```

特别需要注意，不管是从src读取，或向dest写入时，都不能数组越界，否则报错 `ArrayIndexOutOfBoundsException`

因此srcPos+length不能超过src.length长度 或 destPos+length 不能超过dest.length

由于System.arraycopy不能实现自动包装和拆箱，

因此不适用于基本类型和基本类型的包装类型，当然也不适用两个类型根本不一致的对象类型

如果针对对象类型拷贝，那只是拷贝的对象引用，因此也称为**浅拷贝**

### 数组比较
