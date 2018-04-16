#chapter-01-02

java core concept

1、Variables (java variables and java data type )
2、Operations (Variables Operation、condition Operation、)
3、Class + Object
  3.1、Fields
  3.2、Constructor
  3.1、Method
4、interface
5、package
6、Java Access Modifiers



#1、Variables

#java variables type
In Java there are four types of variables:

Non-static fields  belong to an object, called instance variables
Static fields      belong to an  class, called class variables, static fields have same value for all object of a class
Local variables    a variable declared inside a method, local variables used inside a method
Parameters         A parameter is a variable that is passed to a method when the method is called

#base data types
Here are examples of how to declare variables of all the primitive data types in Java:
boolean myBoolean   true or falses
byte    myByte;      8bit   -128 ~ 127
short   myShort;     16bit
char    myChar;      16bit
int     myInt;       32bit
long    myLong;      64bit
float   myFloat;     32bit
double  myDouble;    64bit

#Object types
Here are examples of how to declare variables of the object types in Java:
Boolean myBoolean   true or falses
Byte    myByte;      8bit   -128 ~ 127
Short   myShort;     16bit
Char    myChar;      16bit
Int     myInt;       32bit
Long    myLong;      64bit
Float   myFloat;     32bit
Double  myDouble;    64bit
String  myString;

#Variable declarartion
type name ;
int myInt ;

#Variable assginment
myInt =1 ;

float myFloat =2.0 ;

#Variable reading
float myFloat =2.0 ;
myFloat = myFloat +1;

#Variable naming Conventions
1、Java variable names are case sensitive. The variable name money is not the same as Money or MONEY.
2、Java variable names must start with a letter, or the $ or _ character.
3、Variable names cannot be equal to reserved key words in Java. For instance, the words int or for
4、If variable names consist of multiple words, each word after the first word has its first letter written in uppercase. 
   For instance, ＾variableName￣ or ＾bigApple￣.
5、Static final fields (constants) are named in all uppercase, 、 For instance ＾EXCHANGE_RATE￣ or ＾COEFFICIENT￣.


#2、operations

#2.1、Java Math Operators and Math Class
Java Math Operators
	Addition            +
	Subtraction         -
	Multiplication      *
	Division	    /
	Remainder / Modulo  mod
Java Math Operator Precedence
	Java Integer Math
	Java Floating Point Math
	Floating Point Precision
The Java Math Class
	Basic Math Functions
	Math.abs()
	Math.ceil()
	Math.floor()
	Math.floorDiv()
	Math.min()
	Math.max()
	Math.round()
	Math.random()



#3、Class + Object

Define Java Class in Java File

A Java class can contain:
	Fields
	Constructors
	Methods


#3.1 Java Fields

Field Declaration Syntax

[access_modifier] [static] [final] type name [= initial value] ;


Java Field Access Modifiers

public class Customer {

    private   String email;      // the class itself can access this java field.
    [package] String position;  // no modifier = package access modifier  class in the same package can access
    protected String name;      // class itself and subclass can access modifier 
    public    String city;      // all class in same application can access

}

Static and Non-static Fields

Static Fields  // called class Variables 
Non-static Fields // called Object or install Variable

final Fields

A final field cannot have its value changed, once assigned

public class Customer {

    final String field1 = "Fixed Value";

}

Since static final fields are often used as constants
public class Customer {

    static final String CONSTANT_1 = "Fixed Value";

}



#3.2、Java Methods

A method parameter similar to a variable. You can read its value, and change its value too
public MyClass{

    public void writeText(String text1, String text2) {
        System.out.print(text1);    // read value of text1 parameter.
        System.out.print(text2);    // read value of text2 parameter.

        text1 = "new value 1";      // change value of text1
        text2 = "new value 2";      // change value of text2
    }
}

Final Parameters

The value of a final parameter cannot be changed. That is, if the parameter is a reference to an object, the reference cannot be changed, 

public void writeText(final String text1, final String text2) {
    System.out.print(text1);    // read value of text1 parameter.
    System.out.print(text2);    // read value of text2 parameter.
}

Method Access Modifiers
same java field access Modifiers  private, protected, package, public  

Exception Declarations
If an error occurs inside a method, the method may throw an exception. Exceptions have to be declared in the method declaration, like this (marked in bold):

public String concat(String string1, String string2) throws MyException {

    if(string1 == null) {
        throw new MyException("string1 was null");
    }
    if(string2 == null) {
        throw new MyException("string2 was null");
    }

    return string1 + string2;
}



#3.3 constructors

constructor name is same class name

constructor access modifies like public

Constructor Overloading

public class MyClass {

    private int number = 0;

    public MyClass() {
    }

    public MyClass(int theNumber) {
        this.number = theNumber;
    }
}

Default, no-arg Constructor


Calling a Constructor From a Constructor

public class Employee {

    private String firstName = null;
    private String lastName  = null;
    private int    birthYear = 0;

    public Employee(String first,
        String last,
        int    year   ) {

        firstName = first;
        lastName  = last;
        birthYear = year;
    }

    public Employee(String first, String last){
        this(first, last, -1);
    }
}


# 5.Java Packages

Java Package Naming Conventions
Java packages are always written in lowercase letters




# 6 Access Modifiers
Class Access Modifiers

The Java access modifiers private and protected cannot be assigned to a class. Only to constructors, methods and fields inside classes. Classes can only have the default (package) and public access modifier assigned to them.

Interface Access Modifiers

herefore you cannot use the private and protected access modifiers in interfaces




