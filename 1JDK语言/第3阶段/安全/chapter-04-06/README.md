# Java Key

 chapter-04-06


		Key concept
		Key Security
		KeyPair
		KeyGenerator and KeyPairGenerator

## key concept

key use encrypting or signing data.

There are two types of keys - depending on which type of encryption algorithm you use:
 
1. Symmetric keys  对称性密钥
2. Asymmetric keys 非对称性密钥

Symmetric keys are used for symmetric encryption algorithms，
Symmetric encryption algorithms use the same key for encryption and decryption.

Asymmetric keys are used for asymmetric encryption algorithms
Asymmetric encryption algorithms use one key for encryption, and another for decryption. 

The public key - private key encryption algorithms are examples of asymmetric encryption algorithms.


## Key Security

密钥应该难以猜测，因此攻击者不能轻易猜出加密密钥。如果他们的密钥很容易被猜出，攻击者很容易解密加密的数据，并可能自己创建假消息	

密钥应该由随机字节组成。 随机性越好，字节越多，猜测越难，因为有更多可能的组合



## KeyPair

1. Accessing KeyPair Public Key
2. Accessing the KeyPair Private Key

The Java KeyPair class **(java.security.KeyPair)** represents an asymmetric key pair.
In other words, a public key + private key pair

Obtaining a KeyPair Instance
You will normally obtain a KeyPair instance from a Java keystore or a Java KeyPairGenerator.


### Accessing KeyPair Public Key
You can access the PublicKey of a KeyPair by calling its getPublic() method. 
Her is an example of obtaining the PublicKey from a KeyPair instance:

`PublicKey publicKey = keyPair.getPublic();`

### Accessing the KeyPair Private Key
You can access the PrivateKey of a KeyPair by calling the getPrivate() method. 
Here is an example of obtaining the PrivateKey from a KeyPair instance:

`PrivateKey privateKey = keyPair.getPrivate();`

## KeyGenerator and KeyPairGenerator


		Creating a KeyGenerator Instance
		Initializing the KeyGenerator
		Generating a Key


### KeyGenerator
The Java KeyGenerator class (javax.crypto.KeyGenerator) is used to generate symmetric encryption keys

```
KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
SecureRandom secureRandom = new SecureRandom();
keyGenerator.init(secureRandom);
Key key = keyGenerator.generateKey();
```

```
KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
SecureRandom secureRandom = new SecureRandom();
int keyBitSize = 256;
keyGenerator.init(keyBitSize, secureRandom);
SecretKey secretKey = keyGenerator.generateKey();
```


### KeyPairGenerator

he Java KeyPairGenerator class (java.security.KeyPairGenerator) is used to generate asymmetric encryption / decryption key pairs. 
An asymmetric key pair consists of two keys，Public Key, Private Key

The most commonly known type of asymmetric key pair is the public key, private key type of key pair. 
The private key is used to encrypt data, and the public key can be used to decrypt the data again. 

>Actually, you could also encrypt data using the public key and decrypt it using the private key.

The private key is normally kept secret, and the public key can be made publicly available. 


```
KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
keyPairGenerator.initialize(2048);
KeyPair keyPair = keyPairGenerator.generateKeyPair();
```
This example initializes the KeyPairGenerator to generate keys of 2048 bits in size.




实例

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

