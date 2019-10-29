# 2文件操作

		文件读写一览表
		文件内容操作
			基于字节操作文件内容 - FileInputStream, FileOutputStream
			基于字符操作文件内容 - FileReader, FileWriter
			随机访问文件 - RandomAccessFile
		文件属性操作
			常见属性操作
			实例

## 文件读写一览表

| -	|Input(Byte Based)|Output(Byte Based)|Input(Char Based)|Output(Char Based)|
| - | - | - | - | - |
|Basic	|InputStream	|OutputStream	|Reader InputStreamReader	|Writer OutputStreamWriter|
|Files	|FileInputStream RandomAccessFile	|FileOutputStream RandomAccessFile	|FileReader	|FileWriter|


## 文件内容操作

### 基于字节操作文件内容 - FileInputStream, FileOutputStream

1. 将字节数组写入文件
2. 从文件中读取字节数组

#### 将字节数组写入文件
```java
 //第一个\为转义字符
 private static final String FILE_FULL_NAME="src\\io\\bytes\\data.txt";

 public static void writeFileFromFileOutputStream(byte[] b) throws IOException {
        FileOutputStream fileOutputStream = null;
        try{
						//FILE_FULL_NAME 文件不存在，则创建
            fileOutputStream = new FileOutputStream(FILE_FULL_NAME, false);
						//在文件末尾写内容
            fileOutputStream.write(b);
        }finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
```

运行后，将在当前类路径下，生成data.txt文件

FileOutputStream构建函数，包含两个参数name and append.

name表示文件名，文件不存在则自动创建

append表示写入方式，调用write()方法时，为true表示追加，为false表示覆盖已存在的内容

#### 从文件中读取字节数组
```java
    public static void readFileFromFileInputStream() throws IOException{

        //Reads a byte of data from this input stream
        FileInputStream fileInputStream = null;
        try{
            fileInputStream = new FileInputStream(FILE_FULL_NAME);
            int data = fileInputStream.read();
            while (data != -1){
                char cData = (char)data;
                System.out.println(cData);
                data = fileInputStream.read();
            }
        }finally {
            if (fileInputStream != null){
                fileInputStream.close();
            }
        }

        //Reads up to byte.length bytes of data from this input stream
        InputStream InputStream = null;
        try{
            InputStream = new FileInputStream(FILE_FULL_NAME);

            byte[] byteData =  new byte[6];
            int bData = InputStream.read(byteData);
            while (bData != -1){
                System.out.println(new String(byteData));
                java.util.Arrays.fill(byteData, (byte) 0);
                bData = InputStream.read(byteData);
            }
        }finally {
            if (InputStream != null){
                InputStream.close();
            }
        }

    }
```


#### 测试代码
```java
    public static void main(String[] args) {
        try{
            MyStream.writeFileFromFileOutputStream("zhenyun.su".getBytes());
            MyStream.readFileFromFileInputStream();
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
```


method read() is return a byte from file content, other method read(byte[] b) is return byte.length from file content


### 基于字符操作文件内容 - FileReader, FileWriter

1. 将字符数组写入文件
2. 从文件中读取字符数组

#### 将字符数组写入文件

```java
public class MyStringStream {
    private static final String FILE1_NAME="src\\io\\string\\file1.txt";
    private static final String FILE2_NAME="src\\io\\string\\file2.txt";
    public static void main(String[] args) {
        readStringFromFile();
    }

    public static void readStringFromFile() {
        try{
            FileWriter fileWriter = new FileWriter(FILE1_NAME);
            fileWriter.write("zhenyun.su|cuiyun.shi");
            fileWriter.close();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE2_NAME));
            bufferedWriter.write("zhenyun.su1|cuiyun.shi1\n");
            bufferedWriter.write("zhenyun.su2|cuiyun.shi2\n");
            bufferedWriter.flush();
            bufferedWriter.write("zhenyun.su3|cuiyun.shi3\n");
            bufferedWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
```

BufferedWriter利用缓冲机制来实现写入，目的提升性能，如果想立即写入文件调用
bufferedWriter.flush();

#### 从文件中读取字符数组

```java
public class MyStringStream {
    private static final String FILE2_NAME="src\\io\\string\\file2.txt";
    public static void main(String[] args) {
        writeStringToFile();
    }

    public static void writeStringToFile() {
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE2_NAME));
            String line = null;
            line = bufferedReader.readLine();
            while (line != null){
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
```

readLine用于读取一行字符串，我们可以利用String.split()函数，

例如 line.split("|")来分隔字符串，读取每个名字

### 随机访问文件 - RandomAccessFile

The RandomAccessFile class in the Java IO API allows you to move around a file and read from it or write to it as you please.

you can read data or write data from anywhere position what you want.

You can replace existing parts of a file too.

This is not possible with the FileInputStream or FileOutputStream.

```java
    public static void randomAccessFileUtil() throws IOException{
        RandomAccessFile file = null;
        try{
            file = new RandomAccessFile(FILE_FULL_NAME,"rw");
            file.seek(2);
            long pointer = file.getFilePointer();
            System.out.println("file.seek:"+pointer);
            int aByte = file.read();
            while (aByte != -1){
                System.out.println((char)aByte);
                aByte = file.read();
            }

            file.write(" is good".getBytes());

            file.seek(0);
            int aByte1 = file.read();
            while (aByte1 != -1){
                System.out.println((char)aByte1);
                aByte1 = file.read();
            }

        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (file != null) {
                file.close();
            };
        }
    }
```


## 文件属性操作

有时你需要访问文件属性，例如文件或文件夹大小，创建日期，修改日期，文件夹中文件列表

java io为我们提供 **File** class.

### 常见属性操作

* Instantiating a java.io.File
> file =  new File(FILE_DIR+FILE_NAME);
* Check if File Exists
> boolean fileExists = file.exists();
* Create a Directory if it Does Not Exist
> boolean dirCreated = file.mkdir();
> boolean dirCreatedAll = file.mkdirs();
* File Length
> long length = file.length();
* Rename or Move File
> boolean renameTo = file.renameTo(new File("FILE_DIR+FILE_NEW_NAME"));
* Delete File
> boolean isDelete = file.delete();
* Check if Path is File or Directory

* Read List of Files in Directory
>String[] fileNames = file.list();
>File[] files = file.listFiles();

```java
            String[] fileNames = file.list();
            if (fileNames != null){
                for (String fileName: fileNames){
                    System.out.println("file.list: "+fileName);
                }
            }

            File[] files = file.listFiles();
            if (files != null) {
                for (File fileItem : files) {
                    System.out.println("file.listFiles: " + fileItem.getName());
                }
            }
```

### 实例
```java
   public static void readFileAttributeFromFile() throws IOException {
        File file = null;
        try{
            file =  new File(FILE_DIR+FILE_NAME);
            boolean fileExists = file.exists();
            System.out.println("file.exists: "+fileExists);

            boolean isDirectory = file.isDirectory();
            System.out.println("file.isDirectory: "+isDirectory);
            boolean dirCreated = file.mkdir();
            System.out.println("file.mkdir: "+dirCreated);

            boolean dirCreatedAll = file.mkdirs();
            System.out.println("file.mkdirs: "+dirCreatedAll);

            long length = file.length();
            System.out.println("file.length: "+length);

            System.out.println("file.Name: "+file.getName());
            System.out.println("file.Path: "+file.getPath());
            System.out.println("file.canWrite: "+file.canWrite());

            boolean renameTo = file.renameTo(new File("FILE_DIR+FILE_NEW_NAME"));
            System.out.println("file.renameTo: "+renameTo);

            String[] fileNames = file.list();
            if (fileNames != null){
                for (String fileName: fileNames){
                    System.out.println("file.list: "+fileName);
                }
            }

            File[] files = file.listFiles();
            if (files != null) {
                for (File fileItem : files) {
                    System.out.println("file.listFiles: " + fileItem.getName());
                }
            }

           // boolean isDelete = file.delete();
           // System.out.println("file.delete: "+isDelete);

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
```
