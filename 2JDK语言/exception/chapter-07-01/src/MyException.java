import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyException {

    public static float divide(int numberToDivide, int numberToDivideBy)
            throws Exception {
        if(numberToDivideBy == 0){
            throw new Exception("Cannot divide by 0");
        }
        return numberToDivide / numberToDivideBy;
    }

    public static void main(String[] args){
        try {
            MyException.divide(2,0);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    // catch exception
    public void openFile(){
        try {
            // constructor may throw FileNotFoundException
            FileReader reader = new FileReader("someFile");
            int i=0;
            while(i != -1){
                //reader.read() may throw IOException
                i = reader.read();
                System.out.println((char) i );
            }
            reader.close();
            System.out.println("--- File End ---");
        } catch (FileNotFoundException e) {
            //do something clever with the exception
        } catch (IOException e) {
            //do something clever with the exception
        }
    }

    // exception Propagating
    public void openFile1() throws IOException {
        FileReader reader = null;
        try {
            reader = new FileReader("someFile");
            int i=0;
            while(i != -1){
                i = reader.read();
                System.out.println((char) i );
            }
        } finally {
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    //do something clever with the exception
                }
            }
            System.out.println("--- File End ---");
        }
    }

}
