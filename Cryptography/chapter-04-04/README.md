# java signatrue

 chapter-04-04

		Creating a Signature Instance
		Initializing the Signature Instance
		Creating the Digital Signature
		Verifying the Digital Signature


## Creating a Signature Instance

`Signature signature = Signature.getInstance("SHA256WithDSA");`

## Initializing the Signature Instance

```
SecureRandom secureRandom = new SecureRandom();
KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
KeyPair keyPair = keyPairGenerator.generateKeyPair();
signature.initSign(keyPair.getPrivate(), secureRandom);
```

## Creating the Digital Signature

```
byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
signature.update(data);

byte[] digitalSignature = signature.sign();
```


## Verifying the Digital Signature

```
byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
signature.update(data);

byte[] digitalSignature = signature.sign();
```

```
byte[] data2 = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
signature2.update(data2);

boolean verified = signature2.verify(digitalSignature);
```

ÊµÀý

```
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
```