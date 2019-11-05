# 1快速入门
		何为类型信息
		类型信息如何产生
		类对象 - Class对象
			类对象产生
			  通过创建对象来产生Class对象
			  通过Class.forName()来产生Class对象
			  通过类字面常量来产生Class对象
			  Class对象，如何触发初始化
			类对象操作
				 获取类的类型信息
				 获取父类的类型信息
			泛型类对象
				使用通配符定义类对象
				使用使用通配符与extends关键字定义类对象
				使用使用通配符与super关键字定义类对象
		实例深入理解
			宠物对象统计实例
			使用class.isInstance判断类型
			通过类字面常量来获取
			使用isAssignableFrom判断类型


## 何为类型信息

java程序运行时由对象组成，那么我们是如何知道运行时对象的类型信息的呢？

java提供了运行时类型信息。这些类型信息，通过 **类的类对象(Class对象)** 这种特殊对象来表示

Class对象包含类有关的信息，比如 所有方法，接口，成员信息。

## 类型信息如何产生

每当编写并且编译一个新类时，就会产生一个Class对象，被保存在.class文件中

当需要运行并创建类的对象时，Java虚拟机通过使用**类加载器子系统**来加载class文件，

并验证class文件是否完整，验证通过后就可创建对象，

也就说再编译时，就会生成Class对象，并保存在.class文件中，如果我们想获取Class对象，必须通过类加载器加载到JVM中


关于 **类加载器子系统** 将在深入Java虚拟机 详解

## 类对象 - Class对象

### 类对象产生

关于Class对象产生，我们通过下面实例来理解

* 通过创建对象来产生Class对象
* 通过Class.forName()来产生Class对象
* 通过类字面常量来产生Class对象
* Class对象，如何触发初始化

#### 通过创建对象来产生Class对象
```java
public class Candy {
    static {
        System.out.println("Loading Candy");
    }

    public Candy() {
        System.out.println("Construct Candy");
    }
}
```

JVM加载类成功后，就会自动调用static {} 方法，通过这个方法来确定什么时候产生类对象的

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        new Candy();
        System.out.println("after new Candy()");
    }
}
```

输出
```
before new Candy()
Loading Candy    先输出，说明类先被加载
Construct Candy
after new Candy()
```

#### 通过Class.forName()来产生Class对象

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        try{
            Class clazz = Class.forName("rtti.Candy");
        }catch(ClassNotFoundException e){
            System.out.println("Candy ClassNotFoundException");
        }
        System.out.println("after new Candy()");
    }
}
```

```
before new Candy()
Loading Candy
after new Candy()
```

通过`Class.forName(rtti.Candy)` 方法获取Candy的Class对象，参数必须是完整的包名

#### 通过类字面常量来产生Class对象

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        Class clazz= Candy.class;
        System.out.println(clazz.getName());
        System.out.println("after new Candy()");
    }
}
```

```
before new Candy()
rtti.Candy
after new Candy()
```

通过类字面常量来获取Class对象，虽然没有触发static的执行,但是使用更加简单和安全，因此它会在编译时就做检查

类字面常量不仅可以用于普通的类，也可以用于基本数据类型，接口及数组。

对应基本类型的包装类是没有.class，但是提供.TYPE

```java
        Class intClass = int.class;
        Class IntegerClass = Integer.TYPE;
        intClass = IntegerClass;   //基本类型和对应包装类型等价的
```

所有基本类型和对应包装类型，对应获取类字面常量是等价的

我们推荐使用类字面常量来获取类对象，但是.class 它不会自动地初始化该Class对象，

这样设计有什么好处呢？，初始化有效的实现了**惰性**

#### Class对象，如何触发初始化

调整Candy代码，新增常量，静态变量，静态方法
```java
public class Candy {
    static final int id= 0;
    static int count= 0;
    static void print(){
        System.out.println("Print Candy");
    }
    static {
        System.out.println("Loading Candy");
    }

    public Candy() {
        System.out.println("Construct Candy");
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        System.out.println("before new Candy()");
        System.out.println(Candy.id);
        // System.out.println(Candy.count);
        // Candy.print();
        System.out.println("after new Candy()");
    }
}
```

```
before new Candy()
0
after new Candy()
```

成功输出 id常量值，但是并没有打印出Loading Candy，说明对应编译器常量，是不会进行类加载的

当我们调用类的静态变量Candy.count 和静态方法 Candy.print()时，都打印出Loading Candy，

说明这时已经进行类加载，并进行初始化工作

### 类对象操作

* 获取类的类型信息
* 获取父类的类型信息

#### 获取类的类型信息

给Candy新增游泳和开车的能力接口

```java
public class Candy implements Swimable, Driverable {
	  private String name = "suzhenyun";
    static final int id= 0;
    static int count= 0;
    static void print(){
        System.out.println("Print Candy");
    }
    static {
        System.out.println("Loading Candy");
    }

    public Candy() {
        System.out.println("Construct Candy");
    }

    @Override
    public void drive() {
        System.out.println("Candy can drivering");
    }

    @Override
    public void swim() {
        System.out.println("Candy can swimming");
    }
}
```

```java
    public static void getRtti() {
        Class clazz = Candy.class;
        System.out.println("Class clazz = Candy.class");
        System.out.println("clazz.getName(): "+clazz.getName());
        System.out.println("clazz.getSimpleName(): "+clazz.getSimpleName());
        System.out.println("clazz.toString(): "+clazz.toString());
        System.out.println("clazz.getSuperclass(): "+clazz.getSuperclass());
        for (Class inter: clazz.getInterfaces()){
            System.out.println("Interface: "+inter.getName());
        }
        for (Constructor con: clazz.getConstructors()){
            System.out.println("Constructor: "+con.getName());
        }

        for(Field field: clazz.getDeclaredFields()){
            System.out.println("Field: "+field.getName());
        }
        for(Method method: clazz.getMethods()){
            System.out.println("Method: "+method.getName());
        }
    }
```

输出
```
Class clazz = Candy.class
clazz.getName(): rtti.Candy
clazz.getSimpleName(): Candy
clazz.toString(): class rtti.Candy
clazz.getSuperclass(): class java.lang.Object  默认父类为Object
Interface: rtti.Swimable
Interface: rtti.Driverable
Constructor: rtti.Candy
Field: name
Field: id
Field: count
Method: swim
Method: drive
Method: wait
Method: wait
Method: wait
Method: equals
Method: toString
Method: hashCode
Method: getClass
Method: notify
Method: notifyAll
```


#### 获取父类的类型信息

```java
public class SuperCandy {
    void call(){
        System.out.println("SuperCandy call");
    };
}

public class Candy extends SuperCandy implements Swimable, Driverable {}
```


```java
public static void getSuperRtti() {
        System.out.println("Class clazz = Candy.class");
        Class clazz = Candy.class;
        Class superClazz = clazz.getSuperclass();
        try{
            SuperCandy candy = (SuperCandy)superClazz.newInstance();
            candy.call();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
```

```
Class clazz = Candy.class
SuperCandy call
```

从clazz.getSuperclass() 获取Candy父类的类对象，赋值给定义Class变量,

newInstance()返回Object类型，需要强制转换为SuperCandy

单纯使用Class `Class clazz = Candy.class;` 是不能够在编译时检查出类型错误

通过定义泛型类变量Class<SuperCandy>

### 泛型类对象

* 使用通配符定义类对象
* 使用使用通配符与extends关键字定义类对象
* 使用使用通配符与super关键字定义类对象

#### 使用通配符定义类对象

使用通配符 **?** 来接收任何类的类对象
```java
    public static void getRttiDD() {
        System.out.println("Class clazz = Candy.class");
        Class<?> clazz = Candy.class;
        clazz = clazz.getSuperclass();
        try{
            SuperCandy candy = (SuperCandy)clazz.newInstance();
            candy.call();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
```

#### 使用使用通配符与extends关键字定义类对象

```java
		Class<? extends SuperCandy> clazz = Candy.class;
```
类对象变量 clazz 可以引用从SuperCandy继承下所有的Class对象

#### 使用使用通配符与super关键字定义类对象

```java
    Class<Candy> clazz = Candy.class;
		Class<SuperCandy> clazzSuper =  clazz.getSuperclass(); // 报错
    Class<? super Candy> clazzSuper = clazz.getSuperclass();
```

若已经指定泛型 Class<Candy>， 则在获取父类的类对象时，不同使用Class<SuperCandy>接收，否则报错

则是因为编译已经知道是Candy的父类，但是并不知道Candy父类是谁，所以使用Class<? super Candy> 来获取


## 实例深入理解

下面通过类型信息来创建宠物对象，并统计宠物类型数量

* 宠物对象统计实例
* 使用class.isInstance判断类型
* 通过类字面常量来获取
* 使用isAssignableFrom判断类型

### 宠物对象统计实例

* 定义宠物类继承结构
* 定义宠物创建器
* 通过Class.forName来实例化
* 实现宠物计数器
* 使用instanceof判断类型
* 编写测试代码

#### 定义宠物类继承结构

```java
public class Animal { }
public class Person extends Animal{}
public class Pet extends Animal { }
public abstract class Cat extends Pet { }
public class Garfield extends Cat { }  // 加菲猫
public abstract class Dog extends Pet {}
public class MuttDog extends Dog {} //杂种狗
public class PugDog extends Dog {}  //哈巴狗
```

#### 定义宠物创建器
```java
public abstract class AbstractPetCreator {
    private Random random = new Random(47);
    public abstract List<Class<? extends Pet>> allTypes();  //包含所有宠物类型
    public abstract List<Class<? extends Pet>> types();  //包含可以被实例化的宠物类型

    public Pet randomPet(){
        int n = random.nextInt(types().size());
        try{
            return types().get(n).newInstance();
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public Pet[] createArray(int size){
        Pet[]  pets = new Pet[size];
        for (int i = 0; i < size; i++) {
            pets[i] = randomPet();
        }
        return pets;
    }
}
```

#### 通过Class.forName来实例化
```java
public class ForNamePetCreator extends AbstractPetCreator {
    private static List<Class<? extends Pet>> allTypes = new ArrayList<>();
    private static List<Class<? extends Pet>> types = new ArrayList<>();
    private static String[] typeNames={
            "rtti.pet.Garfield", "rtti.pet.MuttDog", "rtti.pet.PugDog",
            "rtti.pet.Cat","rtti.pet.Dog",
            "rtti.pet.Pet"
    };

    @SuppressWarnings("unchecked")
    private static void loader(){
        try{
            for (int i = 0; i < typeNames.length ; i++) {
                Class<? extends Pet> clazz = (Class<? extends Pet>)Class.forName(typeNames[i]);
                allTypes.add(clazz);
                if (!Modifier.isAbstract(clazz.getModifiers())){
                    types.add(clazz);
                }
            }
        }catch(Exception e){
            throw new RuntimeException("load class of pet");
        }
    }
    static {
        loader();
    }
    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }

    @Override
    public List<Class<? extends Pet>> allTypes() {
        return allTypes;
    }
}
```

使用`static{}`在类加载时,通过`Class.forName()`来获取宠物类对象，并保存到类对象列表中allTypes

将不是抽象类的宠物类对象保存到types列表中

#### 实现宠物计数器
```java
public class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
	  //初始化Map，将宠物类对象和初始计数0保存，到Map中
    public PetCounter(AbstractPetCreator abstractPetCreator){
        for (Class<? extends Pet> clazz : abstractPetCreator.allTypes()) {
            this.put(clazz, 0);
        }
    }

    //按类对象计数
    public void count(Class<? extends Pet> type) {
        put(type, get(type) + 1);
    }

		//按类名称=计数格式输出
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for(Class<? extends Pet> key: this.keySet()){
            result.append(key.getSimpleName());
            result.append("=");
            result.append(this.get(key));
            result.append(", ");
        }
        result.delete(result.length()-2, result.length());
        result.append("}");
        return result.toString();
    }
}
```

#### 使用instanceof判断类型

```java
public class PetUtils {

    public static void forNameCountPet(AbstractPetCreator abstractPetCreator, int size) {
        PetCounter counter = new PetCounter(abstractPetCreator);

        for (Pet pet : abstractPetCreator.createArray(size)) {
            System.out.println(pet.getClass().getName());
            if (pet instanceof MuttDog) {
                counter.count(MuttDog.class);
            };
            if (pet instanceof PugDog) {
                counter.count(PugDog.class);
            };
            if (pet instanceof Garfield) {
                counter.count(Garfield.class);
            };
            if (pet instanceof Cat) {
                counter.count(Cat.class);
            };
            if (pet instanceof Dog) {
                counter.count(Dog.class);
            };
            if (pet instanceof Pet) {
                counter.count(Pet.class);
            };
        }
        System.out.println(counter);
    }
}
```

在工具类中，使用 `instanceof` 方法来检查 对象是某个类的实例化，或某个类的派生类的实例化

#### 编写测试代码
```java
public class Test {
    public static void main(String[] args) {
       forNamePetCreat();
    }
    public static void forNamePetCreat() {
        PetUtils.forNameCountPet(new ForNamePetCreator(), 10);
        System.out.println("---------------");
        PetUtils.classCountPet(new ForNamePetCreator(), 10);
    }
}
```
输出
```
rtti.pet.PugDog
rtti.pet.MuttDog
rtti.pet.PugDog
rtti.pet.Garfield
rtti.pet.Garfield
rtti.pet.PugDog
rtti.pet.Garfield
rtti.pet.MuttDog
rtti.pet.PugDog
rtti.pet.PugDog
{Garfield=3, MuttDog=2, PugDog=5, Cat=3, Dog=7, Pet=10}
```

使用 `instanceof`方法来判断类，需要硬编码，太不灵活

下面我们使用

### 使用class.isInstance判断类型

```java
public class PetUtils {

    public static void classCountPet(AbstractPetCreator abstractPetCreator, int size) {
        PetCounter counter = new PetCounter(abstractPetCreator);

        for (Pet pet : abstractPetCreator.createArray(size)) {
            System.out.println(pet.getClass().getName());
            for(Map.Entry<Class<? extends Pet>, Integer> pair: counter.entrySet()){
                if (pair.getKey().isInstance(pet)){
                    counter.put(pair.getKey(), pair.getValue()+1);
                }
            }
        }
        System.out.println(counter);
    }
}
```

针对每一个宠物对象pet，都进行循环PetCounter中所有类对象，来判断宠物对象是否为对应类或派生类


### 通过类字面常量来获取
```java
public class LiteralPetCreator extends AbstractPetCreator{
    private static List<Class<? extends Pet>> allTypes = new ArrayList<>();
    private static List<Class<? extends Pet>> types = new ArrayList<>();

    @SuppressWarnings("unchecked")
    private static void loader(){
        allTypes.addAll(Collections.unmodifiableCollection(Arrays.asList(
                Garfield.class, MuttDog.class, PugDog.class,
                Cat.class, Dog.class,
                Pet.class
        )));
        for (Class clazz: allTypes){
            if (!Modifier.isAbstract(clazz.getModifiers())){
                types.add(clazz);
            }
        }
    }
    static {
        loader();
    }
    @Override
    public List<Class<? extends Pet>> allTypes() {
        return allTypes;
    }

    @Override
    public List<Class<? extends Pet>> types() {
        return types;
    }
}
```

```java
public class Test {
    public static void main(String[] args) {
        // forNamePetCreat();
       LiteralPetCreat();
    }

    public static void LiteralPetCreat() {
        PetUtils.classCountPet(new LiteralPetCreator(), 10);
    }
}
```

输出结果一样的

### 使用isAssignableFrom判断类型

利用类对象的isAssignableFrom来判断类型 类对象是否为类对象，或派生类的类对象


```java
public class TypeCounter extends HashMap<Class<?>, Integer> {
    private Class<?> baseType;  //定义基类，这里Pet.class

    public TypeCounter(Class<?> baseType) {
        super();
        this.baseType = baseType;
    }
    // 通过对象获取类对象，并判断是否基类的类对象或派生类的类对象
    public void count(Object o) {
        Class<?> clazz = o.getClass();
        if (baseType.isAssignableFrom(clazz)) {
            addClass(clazz);
        } else {
            throw new RuntimeException("error type");
        }
    }

		//通过递归来方式来计数，如果判断父类不是基类的类对象，或为null时，不在递归
    public void addClass(Class<?> c) {
        Integer value = get(c);
        this.put(c, value == null ? 1 : value + 1);
        Class<?> superClazz = c.getSuperclass();
        if (superClazz != null&& baseType.isAssignableFrom(superClazz)) {
            addClass(superClazz);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("{");
        for (Class<?> key : this.keySet()) {
            result.append(key.getSimpleName());
            result.append("=");
            result.append(this.get(key));
            result.append(", ");
        }
        result.delete(result.length() - 2, result.length());
        result.append("}");
        return result.toString();
    }
}
```

添加类型计数器的工具方法
```java
public class PetUtils {

    public static void typeCounter(AbstractPetCreator abstractPetCreator, int size) {
        TypeCounter counter = new TypeCounter(Pet.class);

        for (Pet pet : abstractPetCreator.createArray(size)) {
            System.out.println(pet.getClass().getName());
            counter.count(pet);
        }
        System.out.println(counter);
    }
}
```
```java
public class Test {
    public static void main(String[] args) {
        // forNamePetCreat();
       // LiteralPetCreat();
       typeCounter();
    }
    public static void typeCounter(){
        PetUtils.typeCounter(new LiteralPetCreator(), 5);
    }
}
```

输出
```
rtti.pet.PugDog
rtti.pet.MuttDog
rtti.pet.PugDog
rtti.pet.Garfield
rtti.pet.Garfield
{PugDog=2, Pet=5, Cat=2, Garfield=2, Dog=3, MuttDog=1}
```
