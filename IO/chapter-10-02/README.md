# Java File Access
chapter-10-02

			File IO OverView
			File Content Access
				InputStream, OutputStream, FileInputStream, FileOutputStream
				RandomAccessFile



## File IO OverView

### File Content Access

| -	|Input(Byte Based)|Output(Byte Based)|Input(Char Based)|Output(Char Based)|
| - | - | - | - | - |
|Basic	|InputStream	|OutputStream	|Reader InputStreamReader	|Writer OutputStreamWriter|
|Files	|FileInputStream RandomAccessFile	|FileOutputStream RandomAccessFile	|FileReader	|FileWriter|

### File attributes Access

Sometimes you may need access to information about a file rather than its content. 
For instance, if you need to know the file size or the file attributes of a file. 
The same may be true for a directory. 
For instance, you may want to get a list of all files in a given directory. 
Both file and directory information is available via the **File** class.


## File Content Access

### InputStream, OutputStream, FileInputStream, FileOutputStream


write file 
```
 public static void writeFileFromFileOutputStream(byte[] b) throws IOException {
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(FILE_FULL_NAME, false);
            fileOutputStream.write(b);
        }finally {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        }
    }
```

There is a constructor that takes 2 parameters too: The file name and a boolean. 

The boolean indicates whether to append or overwrite an existing file. Here are two examples:

`fileOutputStream = new FileOutputStream(FILE_FULL_NAME, false);`

false overwrite ; ture append

read file

```
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



```
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

### RandomAccessFile
The RandomAccessFile class in the Java IO API allows you to move around a file and read from it or write to it as you please. 

you can read data or write data from anywhere position what you want.

You can replace existing parts of a file too. 

This is not possible with the FileInputStream or FileOutputStream.

```
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

file.seek(2);  读取文件指针在2字符的位置。





## File attributes Access

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
> 
* Read List of Files in Directory
```
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

example

```
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