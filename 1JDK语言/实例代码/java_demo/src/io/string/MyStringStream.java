package io.string;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-09
 */

public class MyStringStream {
    private static final String FILE1_NAME="src\\io\\string\\file1.txt";
    private static final String FILE2_NAME="src\\io\\string\\file2.txt";
    public static void main(String[] args) {
        //readStringFromFile();
        writeStringToFile();
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
