import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class MyMac {
    public static void main(String[] args)
    {
        try{
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] keyBytes   = new byte[]{0,1,2,3,4,5,6,7,8 ,9,10,11,12,13,14,15};
            String algorithm  = "RawBytes";
            SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);
            mac.init(key);

            byte[] data  = "zhenyun.su".getBytes("UTF-8");
            byte[] macBytes = mac.doFinal(data);

            System.out.println(new String(macBytes));

        }catch (NoSuchAlgorithmException e){
            System.out.print(e.getMessage());
        }catch (Exception e1){
            System.out.print(e1.getMessage());
        }
    }
}
