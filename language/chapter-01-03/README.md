# chapter-01-03
Java Files Syntax

		Java Files
		Java Syntax
		  Package Declaration
		  Import Statements
		  Type Declaration
		  Field Declarations
		  Class Initializers
		  Constructors
		  Methods
		  Type Declaration End
		comments and annotations

# 1. Java Files
java code inside a java files£¬type for  class, abstract class, interface, enum, annotation

# 2. Java Syntax from java Files 

```
package javacore;

import java.util.HashMap;

// type Declaration for class, abstract class, interface, enum, annotation
public class MyClass {

    protected final String name = "John";

    {
        //class initializer
    }

    public MyClass() {
    }

    public String getName() {
        return this.name;
    }

    public static void main(String[] args) {
    }
}
```


# Package Declaration
The first line in the code example shown earlier is the package declaration. This part, to be more specific:

package javacore;
The package declaration consists of the word package, a space, and then the name of the package the type is to be located in. The .java file should be located in a directory structure that matches the package name. Packages and package declarations are covered in more detail in the text on Java packages.

# Import Statements
The second line in the code example above is an import statement. To be more specific, this part:

`import java.util.HashMap;`
This example only has a single import statement, but multiple import statements can be used, each on its own line.

An import statement tells the Java compiler which other Java files this Java file is using. A Java file only needs to import Java files which are not located in the same Java package as the Java file .

# Type Declaration
The third line in the code example above is the type declaration. In Java a type is either a class, an abstract class an interface, an enum or an annotation.

In this example it type declared is a class. The type declaration is delimited by a { and a }. As you can see, the beginning { is listed on the same line as the type declaration. The finishing } is on the last line of the example.

`public class MyClass {`

# Field Declarations
The fourth line in the example above is a field declaration. The field declaration ends with a semicolon ;. Fields are covered in more detail in the text on Java fields. A type (class / interface / enum) can have more than one field. This example just has one field:


`protected final String name = "John";`
# Class Initializers
The fifth line (or block of lines) is a class initializer block. It begins with a { and ends with a }. Inside this block you can put initialization code that is to be executed a instance of the class is created. In this example the block is empty. The text inside the block is just a comment. The Java compiler ignores it.

```
{
    //class initializer
}
```
Class initializers can also be static. Then they are executed already when the class is loaded, and only once because the class is only loaded in the Java Virtual Machine once. Here is an example of a static initializer block:

```
static {
    //static class initializer
}
```
Notice the keyword static before the block. This makes the class initializer block static.

# Constructors
The sixth block is a constructor. Constructors are similar to class initializers, except they can take parameters. Constructors are covered in more detail in the text on Java constructors. A class can have more than one constructor, although this example just shows one.
```
public MyClass() {
}
```
# Methods
The seventh element (or block) is a method. When you create an instance of a class (an object) the object can have methods you can execute. These are also sometimes called "instance methods" because they require an instance of the object before you can call the method. Methods are covered in more detail in the text on Java methods . Here is the method declaration from the example above:
```
public String getName() {
    return this.name;
}
```
The eigth block is a static method. A static method belongs to the class, not objects of the class. That means that you can call a static method without having an object of the class the static method belongs to.
```
public static void main(String[] args) {
}
```
# Type Declaration End
The final line in the example is the end of the type declaration, as mentioned earlier.

}
This means that there will come no more Java code which are part of the declared type.