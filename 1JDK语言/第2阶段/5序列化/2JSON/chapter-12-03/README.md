# fastjson

https://github.com/alibaba/fastjson


## Maven下引入Fastjson

```
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.47</version>
</dependency>
```

## 序列化一个对象成JSON字符串

定义Car类
```
public class Car {
    private String brand;
    private Integer doors;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime createDate;
    private Boolean canDrive;
    private String Field1;
    private String Field2;

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", doors=" + doors +
                ", createDate=" + DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s").format(createDate)+
                ", canDrive=" + canDrive +
                ", Field1='" + Field1 + '\'' +
                ", Field2='" + Field2 + '\'' +
                '}';
    }

```

```
public static void main(String[] args) {
        System.out.println(JSON.defaultLocale);
        System.out.println(JSON.defaultTimeZone);

        Car car = new Car();
        car.setBrand("daphne");
        car.setDoors(4);
        car.setCreateDate(ZonedDateTime.now());
        car.setCanDrive(true);
        car.setField1("");
        car.setField2(null);

        String carJson = JSON.toJSONString(car);
        // {"brand":"daphne","canDrive":true,"createDate":"2018-04-27 14:30:04","doors":4,"field1":""}
        System.out.println(carJson);

    }
```

## 常见序列化特性

fastjson的序列化特性定义在枚举类 **com\alibaba\fastjson\serializer\SerializerFeature.java**中，

可以通过设置多个特性到FastjsonConfig中全局使用，
也可以在某个具体的JSON.writeJSONString时作为参数使用。

1. QuoteFieldNames, //key使用引号
2. UseSingleQuotes, //使用单引号
3. WriteMapNullValue, //输出Map的null值
4. WriteEnumUsingToString, //枚举属性输出toString的结果
5. WriteEnumUsingName, //枚举数据输出name
6. UseISO8601DateFormat, //使用日期格式
7. WriteNullListAsEmpty, //List为空则输出[]
8. WriteNullStringAsEmpty, //String为空则输出””
9. WriteNullNumberAsZero, //Number类型为空则输出0
10. WriteNullBooleanAsFalse, //Boolean类型为空则输出false
11. SkipTransientField,
12. SortField, //排序字段
13. WriteTabAsSpecial,
14. PrettyFormat, // 格式化JSON缩进
15. WriteClassName, // 输出类名
16. DisableCircularReferenceDetect, // 禁止循环引用
17. WriteSlashAsSpecial, // 对斜杠’/’进行转义
18. BrowserCompatible,
19. WriteDateUseDateFormat, // 全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
20. PrettyFormat   //格式层Tree结构展示

```
public static void main(String[] args) {
        System.out.println(JSON.defaultLocale);
        System.out.println(JSON.defaultTimeZone);

        Car car = new Car();
        car.setBrand("daphne");
        car.setDoors(4);
        car.setCreateDate(ZonedDateTime.now());
        car.setCanDrive(true);
        car.setField1("");
        car.setField2(null);

        String carJson = JSON.toJSONString(car,
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteNullListAsEmpty
        );
        System.out.println(carJson);

    }
```
输出结果
```
{
	"brand":"daphne",
	"canDrive":true,
	"createDate":"2018-04-27 14:34:13",
	"doors":4,
	"field1":"",
	"field2":""
}
```

将对象成员按数组输出

```
        String carStringArray=  JSON.toJSONString(car, SerializerFeature.BeanToArray);
        System.out.println(carStringArray);

[true,"2018-04-27 17:41:01",null,4,"daphne",""]

```



##  反序列化一个JSON字符串成Java对象

```
    public static void readObjectFromJson(){
        String carJson = "{\"brand\":\"daphne\",\"canDrive\":true,\"createDate\":\"2018-04-27 14:34:13\",\"doors\":4,\"field1\":\"\"}";
        Car car = JSON.parseObject(carJson, Car.class);
        System.out.println(car.toString());

        String carJsons = "[{\"brand\":\"daphne\",\"canDrive\":true,\"createDate\":\"2018-04-27 14:34:13\",\"doors\":4,\"field1\":\"\"},{\"brand\":\"shoebox\",\"canDrive\":true,\"createDate\":\"2019-04-27 14:34:13\",\"doors\":6,\"field1\":\"\"}]";

        List<Car> cars = JSON.parseArray(carJsons, Car.class);
        System.out.println("car count: "+cars.size());
        for (Car item: cars){
            System.out.println(item.toString());
        }
    }

 ```

 ## Annotation

 JSONField 

注意：1、若属性是私有的，必须有set*方法。否则无法反序列化。

package com.alibaba.fastjson.annotation;

```
public @interface JSONField {
    // 配置序列化和反序列化的顺序，1.1.42版本之后才支持
    int ordinal() default 0;

     // 指定字段的名称
    String name() default "";

    // 指定字段的格式，对日期格式有用
    String format() default "";

    // 是否序列化
    boolean serialize() default true;

    // 是否反序列化
    boolean deserialize() default true;
}
```
