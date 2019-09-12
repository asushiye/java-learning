# 6枚举类型
    what is java enums
    define
    Enums in if Statements
    Enums in switch Statements
    Enum Iteration
    Enum Fields and method
    语法最齐全实例

Java Enum is a special Java type used to define collections of constants

a Java enum type is a special kind of Java class. An enum can contain constants, methods etc

## define
```java
public enum Level {
    HIGH,
    MEDIUM,
    LOW
}
```

## Enums in if Statements

```java
Level level = Level.HIGH;  //assign some Level constant to it

if( level == Level.HIGH) {

} else if( level == Level.MEDIUM) {

} else if( level == Level.LOW) {

}
```

## Enums in switch Statements

```java
Level level = Level.HIGH  //assign some Level constant to it

switch (level) {
    case HIGH   : ...; break;
    case MEDIUM : ...; break;
    case LOW    : ...; break;
}

```

## Enum Iteration

```java
for (Level level : Level.values()) {
    System.out.println(level);
}
```

## Enum Fields and method
```java
public enum Level {
    HIGH (3),
    MEDIUM (2) ,
    LOW (1);

    private int level;

    Level(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
```

```java
Level level = Level.HIGH;
System.out.println(level); //结果为 HIGH
System.out.println(level.getLevel());  //结果为3
```

## 语法最齐全实例
package
java.lang.Enum

java.util.concurrent.TimeUntil
