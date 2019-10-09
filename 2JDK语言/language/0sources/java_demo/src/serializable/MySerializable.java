package serializable;

import java.io.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-10-09
 */

public class MySerializable {
    private static final String FILE_DATA_NAME = "src\\serializable\\duck.txt";

    public static void main(String[] args) {
        try {
            saveObject();
            instanceObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveObject() throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            Duck duck = new Duck();
            duck.setName("duck");
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_DATA_NAME));
            objectOutputStream.writeObject(duck);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
    }

    public static void instanceObject() throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(FILE_DATA_NAME));
            Duck duck = (Duck) objectInputStream.readObject();
            duck.print();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }
}
