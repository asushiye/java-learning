# Try-with-resources in Java 7
chapter-07-02

		Resource Management With Try-Catch-Finally
		Try-with-resources
		Custom AutoClosable Implementations
		catching Multiple Exceptions in Java 7

Try-with-resources in Java 7 is a new exception handling mechanism that makes it easier to correctly close resources that are used within a **try-catch block**.



## Resource Management With Try-Catch-Finally

`private static final String FILE_FULL_NAME= "E:\\6_Java\\4_workdemo\\Java-language-learning\\exception\\chapter-07-01\\src\\file.txt";`

```
    private static void printFile() throws IOException {
        InputStream input = null;

        try {
            input = new FileInputStream(FILE_FULL_NAME);

            int data = input.read();
            while(data != -1){
                System.out.print((char) data);
                data = input.read();
            }
        } finally {
            if(input != null){
                input.close();
            }
        }
    }
```


## Try-with-resources

```
    private static void pringFileFromJava() throws IOException{
        try (InputStream  input = new FileInputStream(FILE_FULL_NAME)) {
            int data = input.read();
            while(data != -1){
                System.out.println((char) data);
                data= input.read();
            }
        }
    }
```


This is the try-with-resources construct. The FileInputStream variable is declared inside the parentheses after the try keyword. 

Additionally, a FileInputStream is instantiated and assigned to the variable.


When the try block finishes the FileInputStream will be closed automatically. 

This is possible because FileInputStream implements the Java interface **java.lang.AutoCloseable**. 

All classes implementing this interface can be used inside the try-with-resources construct.


## Custom AutoClosable Implementations

create class for implements the java interface AutoClosable

```
public class MyAutoClosable implements AutoCloseable {

    public void doIt(){
        System.out.println("MyAutoClosable doing it!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("MyAutoClosable closed!");
    }
}
```

try-with-resource
```
    private static void myClose() throws Exception{
        try(MyAutoClosable myAutoClosable =  new MyAutoClosable()){
            myAutoClosable.doIt();
        }
    }
```

`MyFileException.myClose();`

若想在对象在释放时，触发事件，则实现AutoClosable接口

## Catching Multiple Exceptions in Java 7

```
try {

    // execute code that may throw 1 of the 3 exceptions below.

} catch(SQLException e) {
    logger.log(e);

} catch(IOException e) {
    logger.log(e);

} catch(Exception e) {
    logger.severe(e);
}
```

In Java 7 you can catch multiple exceptions using the multi catch syntax:

```
try {

    // execute code that may throw 1 of the 3 exceptions below.

} catch(SQLException | IOException e) {
    logger.log(e);

} catch(Exception e) {
    logger.severe(e);
}
```