# Java ByteArray, Piped,  Buffered
chapter-10-03

		ByteArrayInputStream, ByteArrayOutputStream
		PipedInputStream, PipedOutputStream
		BufferedInputStream, BufferedOutputStream
		DataInputStream, DataOutputStream
		ObjectInputStream	ObjectOutputStream
		PrintStream (only use to output byte stream)
		SequenceInputStream(only use to input byte stream)

## ByteArrayInputStream, ByteArrayOutputStream

The ByteArrayInputStream class of the Java IO API allows you to read data from byte arrays as streams. 


The ByteArrayOutputStream class of the Java IO API allows you to capture data written to a stream in an array

Here is a simple example:

```
    public static void byteArrayInputAndOutputStream() throws Exception{

        ByteArrayInputStream byteArrayInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try{
            byte[] bdata = {1, 2,3,4,5 };
            byteArrayInputStream = new ByteArrayInputStream(bdata);
            int data = byteArrayInputStream.read();
            while (data != -1){
                System.out.println(data);
                data = byteArrayInputStream.read();
            }
            byteArrayOutputStream =  new ByteArrayOutputStream();
            byteArrayOutputStream.write("zhenyun.su".getBytes());
            byte[] bytes = byteArrayOutputStream.toByteArray();
            System.out.println(new String(bytes));

        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            byteArrayInputStream.close();
            byteArrayOutputStream.close();
        }
    }

```

## PipedInputStream, PipedOutputStream

The PipedInputStream class makes it possible to read the contents of a pipe as a stream of bytes. 

Pipes are communication channels between threads inside the same JVM

```
    public static void pipedInputAndOutputStream() throws IOException{

        PipedInputStream pipedInputStream = null;
        PipedOutputStream pipedOutputStream = null;
        try{
            pipedInputStream = new PipedInputStream();
            pipedOutputStream = new PipedOutputStream(pipedInputStream);
            byte[] bData= {1,23,4};
            pipedOutputStream.write(bData);

            int data = pipedInputStream.read();
            while (data != -1) {
                System.out.println(data);
                data = pipedInputStream.read();
            };
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (pipedOutputStream != null) {
                pipedOutputStream.close();
            };
            if (pipedInputStream != null) {
                pipedInputStream.close();
            };
        }
```


## BufferedInputStream, BufferedOutputStream


BufferedInputStream类为输入流提供缓冲。 缓冲可以加快IO的速度。 

BufferedInputStream并不是一次从网络或磁盘读取一个字节，而是一次将更大的数据块读入内部缓冲区。 

当你从BufferedInputStream中读取一个字节时，你可以从它的内部缓冲区读取它。 

当缓冲区被完全读取时，BufferedInputStream将另一个更大的数据块读入缓冲区。 

这通常比从InputStream一次读取单个字节快得多，特别是对于磁盘访问和更大的数据量。

```
            int bufferSize= 8 * 1024;
            bufferedInputStream = new BufferedInputStream(new FileInputStream(FILE_FULL_NAME), bufferSize);
            byte[] bdata = new byte[1024];
            int data = bufferedInputStream.read(bdata);
            System.out.println(new String(bdata));
```

BufferedOutputStream类为输出流提供缓冲。 

缓冲可以加快IO的速度。 不是一次只写一个字节到网络或磁盘，而是每次写入一个更大的块。 

这通常要快得多，特别是对于磁盘访问和更大的数据量。

```
            int bufferSize= 8 * 1024;
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(FILE_FULL_NAME), bufferSize);
            bufferedOutputStream.write("zhenyun.su is great".getBytes());
            bufferedOutputStream.flush();
```

example

```
    public static void bufferInputAndOutputStream() throws IOException{

        BufferedOutputStream bufferedOutputStream = null;
        BufferedInputStream bufferedInputStream = null;
        try{
            int bufferSize= 8 * 1024;
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(FILE_FULL_NAME), bufferSize);
            bufferedOutputStream.write("zhenyun.su is great".getBytes());
            bufferedOutputStream.flush();

            bufferedInputStream = new BufferedInputStream(new FileInputStream(FILE_FULL_NAME), bufferSize);
            byte[] bdata = new byte[1024];
            int data = bufferedInputStream.read(bdata);
            System.out.println(new String(bdata));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            };
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            };
        }
    }
```

## DataInputStream, DataOutputStream

The Java DataInputStream class enables you to read Java primitives (int, float, long etc.) from an InputStream instead of only raw bytes. 

You wrap an InputStream in a DataInputStream and then you can read Java primitives from the DataInputStream. That is why it is called DataInputStream - because it reads data (numbers) instead of just bytes.



```
    public static void dataInputAndOutputStream() throws IOException{
        DataOutputStream dataOutputStream = null;
        try{
            dataOutputStream =  new DataOutputStream(new FileOutputStream(FILE_DATA_NAME));
            dataOutputStream.writeInt(2);
            dataOutputStream.writeFloat(1.2F);
            dataOutputStream.writeDouble(2.3);
            dataOutputStream.writeUTF("zhenyun.su");
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (dataOutputStream != null) {
                dataOutputStream.close();
            };
        }

        DataInputStream dataInputStream = null;
        try{
            dataInputStream = new DataInputStream(new FileInputStream(FILE_DATA_NAME));
            System.out.println(dataInputStream.readInt());
            System.out.println(dataInputStream.readFloat());
            System.out.println(dataInputStream.readDouble());
            System.out.println(dataInputStream.readUTF());
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (dataInputStream != null) {
                dataInputStream.close();
            };
        }
    }
```

## ObjectInputStream	ObjectOutputStream

The Java ObjectInputStream class (java.io.ObjectInputStream) enables you to read Java objects from an InputStream instead of just raw bytes. 

The Java ObjectOutputStream class (java.io.ObjectOutputStream) enables you to write Java objects to an OutputStream instead of just raw bytes. 

You wrap an OutputStream in a ObjectOutputStream and then you can write objects to it.

```

public class MyClass implements Serializable {
    String Name;
    Integer age;
}


    public static void objectInputAndOutputStream() throws IOException{
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_OBJECT_NAME));
            MyClass myClass = new MyClass();
            myClass.Name = "myclass";
            myClass.age = 1;
            objectOutputStream.writeObject(myClass);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }

        ObjectInputStream objectInputStream = null;
        try{
            objectInputStream = new ObjectInputStream(new FileInputStream(FILE_OBJECT_NAME));
            MyClass myClass1 = (MyClass)objectInputStream.readObject();;

            if (myClass1 != null) {
                System.out.println(myClass1.Name);
                System.out.println(myClass1.age);
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }
```

## PrintStream

The Java PrintStream class (java.io.PrintStream) enables you to write formatted data to an underlying OutputStream. 

The PrintStream class can format primitive types like int, long etc. formatted as text, rather than as their byte values

```
    public static void printStreamUtil() throws  IOException{
        PrintStream printStream =null;
        try {
            printStream = new PrintStream(new FileOutputStream(FILE_PRINT_NAME));
            printStream.println('A');
            printStream.println(12.3F);
            printStream.println(24L);
            printStream.println("zhenyun.su");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (printStream != null) {
                printStream.close();
            }
        }

    }
```


## SequenceInputStream

The Java SequenceInputStream combines two or more other InputStream's into one. 

First the SequenceInputStream will read all bytes from the first InputStream, then all bytes from the second InputStream. 

That is the reason it is called a SequenceInputStream, since the InputStream instances are read in sequence

> InputStream + InputStream = SequenceInputStream

```
    public static void sequenceInputStreamUtil() throws  IOException{

        SequenceInputStream sequenceInputStream = null;
        InputStream InputStream1 = null;
        InputStream InputStream2 = null;
        try {
            InputStream1 =  new FileInputStream(FILE_FULL_NAME);
            InputStream2 =  new FileInputStream(FILE_PRINT_NAME);

            sequenceInputStream = new SequenceInputStream(InputStream1, InputStream2);
            byte[] bdata = new byte[1024];
            sequenceInputStream.read(bdata);
            System.out.println(new String(bdata));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (InputStream1 != null) {
                InputStream1.close();
            }
            if (InputStream2 != null) {
                InputStream2.close();
            }
            if (sequenceInputStream != null) {
                sequenceInputStream.close();
            }
        }
    }
```

Combining More Than Two InputStreams



```
InputStream input1 = new FileInputStream("c:\\data\\file1.txt");
InputStream input2 = new FileInputStream("c:\\data\\file2.txt");
InputStream input3 = new FileInputStream("c:\\data\\file3.txt");

Vector<InputStream> streams = new Vector<>();
streams.add(input1);
streams.add(input2);
streams.add(input3);

SequenceInputStream sequenceInputStream =
    new SequenceInputStream(streams.elements()))
    
int data = sequenceInputStream.read();
while(data != -1){
    System.out.println(data);
    data = sequenceInputStream.read();
}
sequenceInputStream.close();
```