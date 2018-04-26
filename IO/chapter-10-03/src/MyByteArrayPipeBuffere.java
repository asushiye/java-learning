import com.sun.xml.internal.bind.v2.util.ByteArrayOutputStreamEx;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class MyByteArrayPipeBuffere {
    public static void main(String[] args) {
        try{
            MyByteArrayPipeBuffere.byteArrayInputAndOutputStream();
            MyByteArrayPipeBuffere.pipedInputAndOutputStream();
            MyByteArrayPipeBuffere.bufferInputAndOutputStream();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

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

    public static void pipedInputAndOutputStream() throws Exception{
        PipedInputStream pipedInputStream = null;
        PipedOutputStream pipedOutputStream = null;
        try{
            pipedInputStream = new PipedInputStream(pipedOutputStream);
            int data = pipedInputStream.read();
            while(data != -1) {
                //do something with data...
                //doSomethingWithData(data);
                data = pipedInputStream.read();
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {
            pipedInputStream.close();
        }
    }

    public static void bufferInputAndOutputStream() throws Exception{

        try{

        }catch (Exception e){
            System.err.println(e.getMessage());
        }finally {

        }
    }
}
