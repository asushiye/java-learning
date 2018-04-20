import javax.crypto.KeyGenerator;
import java.security.*;

public class Mykey {

    public static void main(String[] args)
    {
        try{
            // key
            KeyGenerator keyGenerator= KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(secureRandom);
            Key key= keyGenerator.generateKey();

            // KeyPair
            KeyPairGenerator keyPairGenerator= KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair =  keyPairGenerator.generateKeyPair();

            System.out.println("test");

        }catch (NoSuchAlgorithmException e){
            System.out.print(e.getMessage());
        }catch (Exception e1){
            System.out.print(e1.getMessage());
        }
    }
}
