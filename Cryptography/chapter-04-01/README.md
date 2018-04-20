# Cryptography Overview


		Java Cryptography Extension
		Java Cryptography Architecture
		Core Classes and Interfaces
		Provider
		Cipher
			Initializing the Cipher
			Encrypting or Decrypting Data
		Keys
			Key Security
			Generating a Key
			Generating a Key Pair
		KeyStore
		Keytool
		MessageDigest
		Mac
		Signature
			Signing Data
			Verifying a Signature
			Full Signature and Verification Example


The Java Cryptography API enables you to encrypt and decrypt data in Java
 as well as manage keys, sign and authenticate messages, calculate cryptographic hashes and much more.

 crypto and Cryptography  The two terms refer to the same topic though.


 
The Java cryptography API is provided by what is officially called the Java Cryptography Extension. The Java Cryptography Extension is also sometimes referred to vi the abbreviation JCE.


Core Classes and Interfaces
The Java cryptography API is divided between the following Java packages:

		java.security
		java.security.cert
		java.security.spec
		java.security.interfaces
		javax.crypto
		javax.crypto.spec
		javax.crypto.interfaces
The core classes and interfaces of these packages are:

		Provider
		SecureRandom
		Cipher
		MessageDigest
		Signature
		Mac
		AlgorithmParameters
		AlgorithmParameterGenerator
		KeyFactory
		SecretKeyFactory
		KeyPairGenerator
		KeyGenerator
		KeyAgreement
		KeyStore
		CertificateFactory
		CertPathBuilder
		CertPathValidator
		CertStore


## Provider（java.security.Provider）类是Java加密API中的中心类。 
为了使用Java加密API，您需要提供程序集。 Java SDK带有自己的加密提供程序。 如果您未设置显式加密提供程序，则使用Java SDK默认提供程序。 
但是，此提供程序可能不支持您要使用的加密算法。 因此您可能必须设置您自己的加密提供程序。

用于Java加密API的最流行的加密提供者之一被称为Bouncy Castle。 这是一个设置BouncyCastleProvider的例子：

```
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class ProviderExample {
    public static void main(String[] args) {

        Security.addProvider(new BouncyCastleProvider());

    }
}
```

## Cipher

### create a Java Cipher instance
cipher 密码（javax.crypto.Cipher）类表示加密算法

`Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");`

### Initializing the Cipher

Before the Cipher instance can be used it must be initialized

two parameters:

* Encryption / Decryption cipher mode
* Key

```
byte[] keyBytes   = new byte[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
String algorithm  = "RawBytes";
SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);

cipher.init(Cipher.ENCRYPT_MODE, key);
```
 decrypt data you have to use the **Cipher.DECRYPT_MODE**,


### Encrypting or Decrypting Data

```
byte[] plainText  = "abcdefghijklmnopqrstuvwxyz".getBytes("UTF-8");
byte[] cipherText = cipher.doFinal(plainText);
```

### Keys

To encrypt or decrypt data you need a key. There are two types of keys - depending on which type of encryption algorithm you use:
 
Symmetric keys  对称性密钥
Asymmetric keys 非对称性密钥

Symmetric keys are used for symmetric encryption algorithms，Symmetric encryption algorithms use the same key for encryption and decryption.

Asymmetric keys are used for asymmetric encryption algorithms
Asymmetric encryption algorithms use one key for encryption, and another for decryption. 
The public key - private key encryption algorithms are examples of asymmetric encryption algorithms.

### Key Security

密钥应该难以猜测，因此攻击者不能轻易猜出加密密钥。如果他们的密钥很容易被猜出，攻击者很容易解密加密的数据，并可能自己创建假消息	

密钥应该由随机字节组成。 随机性越好，字节越多，猜测越难，因为有更多可能的组合

### Generating a Key

You can use the Java KeyGenerator class to generate more random encryption keys.

```
KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
SecureRandom secureRandom = new SecureRandom();
int keyBitSize = 256;
keyGenerator.init(keyBitSize, secureRandom);
SecretKey secretKey = keyGenerator.generateKey();

cipher.init(Cipher.ENCRYPT_MODE, secretKey);
```


### Asymmetric key
Generating a Key Pair
Asymmetric encryption algorithms use a key pair consisting of a public key and a private key to encrypt and decrypt data. 

To generate an asymmetric key pair you can use the **KeyPairGenerator (java.security.KeyPairGenerator)**. 

The KeyPairGenerator is covered in a bit more detail in Java KeyPairGenerator tutorial, 
but here is a simple Java KeyPairGenerator example:

```
SecureRandom secureRandom = new SecureRandom();
KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
KeyPair keyPair = keyPairGenerator.generateKeyPair();
```

### KeyStore
The Java KeyStore is a database that can contain keys. 
A Java KeyStore is represented by the KeyStore (java.security.KeyStore) class. 

A KeyStore can hold the following types of keys:

* Private keys
* Public keys + certificates
* Secret keys

Private and public keys are used in asymmetric encryption. A public key can have an associated certificate. 
A certificate is a document that verifies the identity of the person, organization or device claiming to own the public key. 
A certificate is typically digitally signed by the verifying party as proof

Secret keys are used in symmetric encryption.

### Keytool

The Java Keytool is a command line tool that can work with Java KeyStore files. 
The Keytool can generate key pairs into a KeyStore file, 
export certificates from, 
and import certificates into a KeyStore and several other functions.


### MessageDigest

When you receive some encrypted data from someone else, how do you know that no one has modified the encrypted data on the way to you?

A common solution is to calculate a message digest from the data before it is encrypted
 message digest is a hash value calculated from the message data

 When receiving encrypted data, you decrypt it and calculate the message digest from it, 
 and compare the calculated message digest to the message digest that was sent along with the encrypted data

 You can use the Java **MessageDigest (java.security.MessageDigest)** to calculate message digests. 
 You call the **MessageDigest.getInstance()** method to create a MessageDigest instance. 

`MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");`

```
MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

byte[] data1 = "0123456789".getBytes("UTF-8");
byte[] data2 = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");

messageDigest.update(data1);
messageDigest.update(data2);

byte[] digest = messageDigest.digest();
You can also call digest() a single time passing all the data to calculate the message digest from. Here is how that looks:

MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

byte[] data1 = "0123456789".getBytes("UTF-8");

byte[] digest = messageDigest.digest(data1);
```

### Signature
The encrypted message digest is called a digital signature.

The **Signature (java.security.Signature)** class is used to digital sign data

The encrypted message digest is called a digital signature.

数字签名是通过从数据创建消息摘要（散列）并使用要签署数据的设备，个人或组织的 **私钥对消息摘要进行加密** 来创建的。
加密的消息摘要被称为数字签名

`Signature signature = Signature.getInstance("SHA256WithDSA");`


### Signing Data

```
signature.initSign(keyPair.getPrivate(), secureRandom);

byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
signature.update(data);

byte[] digitalSignature = signature.sign();

```

### Verifying a Signature

```
Signature signature = Signature.getInstance("SHA256WithDSA");

signature.initVerify(keyPair.getPublic());

byte[] data2 = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
signature2.update(data2);

boolean verified = signature2.verify(digitalSignature);
```


## Full Signature and Verification Example


```
SecureRandom secureRandom = new SecureRandom();
KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
KeyPair keyPair = keyPairGenerator.generateKeyPair();

Signature signature = Signature.getInstance("SHA256WithDSA");

signature.initSign(keyPair.getPrivate(), secureRandom);

byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
signature.update(data);

byte[] digitalSignature = signature.sign();


Signature signature2 = Signature.getInstance("SHA256WithDSA");
signature2.initVerify(keyPair.getPublic());

signature2.update(data);

boolean verified = signature2.verify(digitalSignature);

System.out.println("verified = " + verified);

```


