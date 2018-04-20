# Java KeyStore

 chapter-04-07


		Creating a KeyStore
		Loading the KeyStore
		Getting Keys
		Setting Keys
		Storing the KeyStore


The Java KeyStore is a database that can contain keys£¬A KeyStore can be written to disk and read again


The KeyStore as a whole can be protected with a password, and each key entry in the KeyStore can be protected with its own password. 
This makes the KeyStore class a useful mechanism to handle encryption keys securely.

 A Java KeyStore is represented by the KeyStore **(java.security.KeyStore)** class


A KeyStore can hold the following types of keys:

* Private keys
* Public keys + certificates
* Secret keys

Private and public keys are used in asymmetric encryption. 
A public key can have an associated certificate. 
A certificate is a document that verifies the identity of the person, organization or device claiming to own the public key. 
A certificate is typically digitally signed by the verifying party as proof.

Secret keys are used in symmetric encryption


## Creating a KeyStore

KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
KeyStore keyStore = KeyStore.getInstance("PKCS12");


ÊµÀý

```
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


```

