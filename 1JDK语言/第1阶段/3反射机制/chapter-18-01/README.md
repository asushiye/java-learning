# Java Reflection

chapter-18-01

		JavaReflection Tutorial
		JavaReflection class


Java反射使得可以在运行时检查类，接口，字段和方法，而无需在编译时知道类名，方法等。
也可以实例化新对象，调用方法, 并使用反射来获取/设置字段值。

Java Reflection is quite powerful and can be very useful.

For instance, Java Reflection can be used to map properties in JSON files to getter / setter methods in Java objects, like Jackson, GSON, Boon etc.

does. Or, Reflection can be used to map the column names of a JDBC ResultSet to getter / setter methods in a Java object.

## JavaReflection Tutorial

Java Reflection Example

Here is a quick Java Reflection example to show you what using reflection looks like:

```
Method[] methods = MyObject.class.getMethods();

for(Method method : methods){
    System.out.println("method = " + method.getName());
}

```


Java Class Object

`Class myObjectClass = MyObject.class;`

Methods and Fields

```
Class myObjectClass = MyObject.class;

Method[] methods = myObjectClass.getMethods();

Field[] fields   = myObjectClass.getFields();
```

## JavaReflection class

Using Java Reflection you can inspect Java classes at runtime.

Inspecting classes is often the first thing you do when using Reflection. From the classes you can obtain information about

		Class Name
		Class Modifies (public, private, synchronized etc.)
		Package Info
		Superclass
		Implemented Interfaces
		Constructors
		Methods
		Fields
		Annotations


The Class Object
`Class myObjectClass = MyObject.class`

Class Name
`String className = myObjectClass.getName();`   //packagename+classname
`String simpleClassName = myObjectClass.getSimpleName();`  // only classname without the pacakge name

Modifiers
You can access the modifiers of a class via the Class object. The class modifiers are the keywords "public", "private", "static" etc. You obtain the class modifiers like this:

```
    int modifiers = myObjectClass.getModifiers();
    Modifier.isAbstract(int modifiers)
    Modifier.isFinal(int modifiers)
    Modifier.isInterface(int modifiers)
    Modifier.isNative(int modifiers)
    Modifier.isPrivate(int modifiers)
    Modifier.isProtected(int modifiers)
    Modifier.isPublic(int modifiers)
    Modifier.isStatic(int modifiers)
    Modifier.isStrict(int modifiers)
    Modifier.isSynchronized(int modifiers)
    Modifier.isTransient(int modifiers)
    Modifier.isVolatile(int modifiers)
```

Package Info
`Package package = myObjectClass.getPackage();`

Superclass
`Class superclass = myObjectClass.getSuperclass();`

Implemented Interfaces
`Class[] interfaces = myObjectClass.getInterfaces();`

Constructors
`Constructor[] constructors = myObjectClass.getConstructors();`

Methods
 `Method[] method = myObjectClass.getMethods();`

Fields
 `Field[] method = myObjectClass.getFields();`

Annotations
 `Annotation[] annotations = myObjectClass.getAnnotations();`



instance:

Interface
```
public interface Drivable {
    public void drive();
}


```

Car
```
public class Car {
    private String name;
    private String brand;
    private Integer door;
    private ZonedDateTime create_date;
}
```

DaphneCar
```
public class DaphneCar extends Car implements Drivable {

    public String address;

    public DaphneCar(String address) {
        this.address = address;
    }

    @Override
    public void drive() {
        System.out.println("daphne car drive");
    }

    public DaphneCar(String name, Integer door, String address) {
        this.address = address;
        super.setBrand("daphne");
        super.setCreate_date(ZonedDateTime.now());
        super.setDoor(door);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

get Class.Name, Constructor, Method, Field, Inteface, superClass, Annotation and Modifier

```
    public static void classMainInfo(){
        Class myClass = DaphneCar.class;

        System.out.println(myClass.getName());
        System.out.println(myClass.getSimpleName());
        Package myPackage = myClass.getPackage();
        System.out.println(myPackage.getName());

        Class superClass = myClass.getSuperclass();
        System.out.println(superClass.getName());

        Constructor[] myConstructors = myClass.getConstructors();
        Class[] interfaces = myClass.getInterfaces();
        Method[] methods = myClass.getMethods();
        Field[] fields = myClass.getFields();
        Annotation[]  annotations = myClass.getAnnotations();

        System.out.println("Field count: "+fields.length);
        for (Field ann: fields) {
            System.out.println(ann.toString());
        }

        for (Method ann: methods) {
            System.out.println(ann.toString());
        }

        int modifiers = myClass.getModifiers();
        System.out.println("isAbstract: "+ Modifier.isAbstract(modifiers));
        System.out.println("isFinal: "+ Modifier.isFinal(modifiers));
        System.out.println("isInterface: "+ Modifier.isInterface(modifiers));
        System.out.println("isNative: "+ Modifier.isNative(modifiers));
        System.out.println("isPrivate: "+ Modifier.isPrivate(modifiers));
        System.out.println("isProtected: "+ Modifier.isProtected(modifiers));
        System.out.println("isPublic: "+ Modifier.isPublic(modifiers));
        System.out.println("isStatic: "+ Modifier.isStatic(modifiers));
        System.out.println("isStrict: "+ Modifier.isStrict(modifiers));
        System.out.println("isSynchronized: "+ Modifier.isSynchronized(modifiers));
        System.out.println("isTransient: "+ Modifier.isTransient(modifiers));
        System.out.println("isVolatile: "+ Modifier.isVolatile(modifiers));
}
```
