import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyMessageDigest {

    public static void main(String[] args)
    {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] data1 = "zhenyun".getBytes("UTF-8");
            byte[] data2 = "su".getBytes("UTF-8");
            messageDigest.update(data1);
            messageDigest.update(data2);
            byte[] digest = messageDigest.digest();

        }catch (NoSuchAlgorithmException e){
            System.out.print(e.getMessage());
        }catch (Exception e1){
            System.out.print(e1.getMessage());
        }
    }
}
