# Java MessageDigest 
chapter-04-03

		Creating a MessageDigest Instance
			Message Digest Algorithms
		Calculate Message Digest

## Creating a MessageDigest Instance

`MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");`



Algorithm Name
* MD2
* MD5
* SHA-1
* SHA-256
* SHA-384
* SHA-512

## Calculate Message Digest

```
byte[] data1 = "0123456789".getBytes("UTF-8");

MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
byte[] digest = messageDigest.digest(data1);
```

If you have multiple blocks of data to include in the same message digest, call the update() method and finish off with a call to digest().

```
byte[] data1 = "0123456789".getBytes("UTF-8");
byte[] data2 = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");

MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
messageDigest.update(data1);
messageDigest.update(data2);

byte[] digest = messageDigest.digest();
```


ÊµÀý

```
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


```

