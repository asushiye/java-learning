import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MyCipher {

    public static void main(String[] args)
    {
        try {
            //创建AES的加密算法的密码类
            Cipher cipher = Cipher.getInstance("AES");

            //生成随机数的密钥key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = new SecureRandom();
            keyGenerator.init(secureRandom);
            Key key = keyGenerator.generateKey();

            //初始化加密模式
            cipher.init(Cipher.ENCRYPT_MODE, key);

            String plainText1 = "zhenyun.su";

            System.out.println("plainText1: "+plainText1);
            byte[] plainByte = plainText1.getBytes("UTF-8");
            byte[] cipherByte = cipher.doFinal(plainByte);

            //初始化解密模式
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText2 = cipher.doFinal(cipherByte);

            System.out.println("plainText2: "+new String(plainText2));


        }catch (NoSuchAlgorithmException e){
            System.out.print(e.getMessage());
        }catch (Exception e){
            System.out.print(e.getMessage());
        }

    }
}
