package bytes;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;

public class MyStream {

    private static final String FILE_FULL_NAME="E:\\6_Java\\4_workdemo\\Java-language-learning\\IO\\chapter-10-01\\src\\bytes\\data.txt";
    private static final String FILE_DATA_NAME="E:\\6_Java\\4_workdemo\\Java-language-learning\\IO\\chapter-10-01\\src\\bytes\\brnaly.txt";
    private static final String FILE_OBJECT_NAME="E:\\6_Java\\4_workdemo\\Java-language-learning\\IO\\chapter-10-01\\src\\bytes\\object.bin";
    private static final String FILE_PRINT_NAME="E:\\6_Java\\4_workdemo\\Java-language-learning\\IO\\chapter-10-01\\src\\bytes\\PRINT.txt";
    private static final String FILE_DIR="E:\\6_Java\\4_workdemo\\Java-language-learning\\IO\\chapter-10-01\\src\\bytes\\file\\";
    private static final String FILE_NAME="attribute.txt";
    private static final String FILE_NEW_NAME="attribute-1.txt";

    public static void main(String[] args) {

        try{
            MyStream.writeFileFromFileOutputStream("zhenyun.su".getBytes());
            MyStream.readFileFromFileInputStream();
            MyStream.readFileAttributeFromFile();
            MyStream.randomAccessFileUtil();
        //    MyStream.pipedInputAndOutputStream();
            MyStream.bufferInputAndOutputStream();

            MyStream.dataInputAndOutputStream();
            MyStream.objectInputAndOutputStream();
            MyStream.printStreamUtil();
            MyStream.sequenceInputStreamUtil();
        }catch (IOException e) {
            System.err.println(e.getMessage());
        }catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

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

    public static void readFileFromFileInputStream() throws Exception{

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

            boolean isDelete = file.delete();
            System.out.println("file.delete: "+isDelete);

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

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
            byte[] bdata = new byte[100];
            int aByte1 = file.read(bdata);
            System.out.println(new String(bdata));

        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            if (file != null) {
                file.close();
            };
        }
    }

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
    }

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
}
