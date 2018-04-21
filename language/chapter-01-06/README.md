# chapter-01-06

# Java Enums

  Java Enum is a special Java type used to define collections of constants

  a Java enum type is a special kind of Java class. An enum can contain constants, methods etc

define
```
public enum Level {
    HIGH,
    MEDIUM,
    LOW
}
```

Enums in if Statements

```
Level level = ...  //assign some Level constant to it

if( level == Level.HIGH) {

} else if( level == Level.MEDIUM) {

} else if( level == Level.LOW) {

}
```

Enums in switch Statements

```
Level level = ...  //assign some Level constant to it

switch (level) {
    case HIGH   : ...; break;
    case MEDIUM : ...; break;
    case LOW    : ...; break;
}

```
Enum Iteration

```
for (Level level : Level.values()) {
    System.out.println(level);
}
```

Enum Fields and method
```
public enum Level {
    HIGH  (3),  //calls constructor with value 3
    MEDIUM(2),  //calls constructor with value 2
    LOW   (1)   //calls constructor with value 1
    ; // semicolon needed when fields / methods follow


    private final int levelCode;

    Level(int levelCode) {
        this.levelCode = levelCode;
    }
    
    public int getLevelCode() {
        return this.levelCode;
    }
    
}
```
```
Level level = Level.HIGH;
System.out.println(level.getLevelCode());
```
package
java.lang.Enum

JDK½ðµä°¸Àý

java.util.concurrent.TimeUntil

