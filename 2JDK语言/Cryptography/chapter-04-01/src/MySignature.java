import java.security.*;


public class MySignature {

    public static void main(String[] args)
    {
        try{
            //create signature instance
            Signature signature = Signature.getInstance("SHA256WithDSA");

            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            // init signature from key and securerandom
            signature.initSign(keyPair.getPrivate(), secureRandom);
            byte[]  context = "zhenyun.su".getBytes("UTF-8");

            // add data and sign
            signature.update(context);
            byte[] digitalSignature= signature.sign();


            //verified si right
            Signature signature1 = Signature.getInstance("SHA256WithDSA");
            signature1.initVerify(keyPair.getPublic());
            signature1.update(context);
            boolean verified = signature1.verify(digitalSignature);
            System.out.println(verified);

        }catch (NoSuchAlgorithmException e){
            System.out.print(e.getMessage());
        }catch (Exception e1){
            System.out.print(e1.getMessage());
        }
    }
}
