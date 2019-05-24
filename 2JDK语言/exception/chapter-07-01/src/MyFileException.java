import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class MyFileException {

    private static final String FILE_FULL_NAME= "E:\\6_Java\\4_workdemo\\Java-language-learning\\exception\\chapter-07-01\\src\\file.txt";
    private static final String FILE_NAME= "file.txt";

    public static void main(String[] args) {
        try {
            MyFileException.pringFileFromJava();
            MyFileException.myClose();
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

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

    private static void pringFileFromJava() throws IOException{
        try (InputStream  input = new FileInputStream(FILE_FULL_NAME)) {
            int data = input.read();
            while(data != -1){
                System.out.println((char) data);
                data= input.read();
            }
        }
    }

    private static void myClose() throws Exception{
        try(MyAutoClosable myAutoClosable =  new MyAutoClosable()){
            myAutoClosable.doIt();
        }
    }
}
