# Java Mac

 chapter-04-05

MAC Message Authentication Code

		Creating a Mac Instance
		Initializing the Mac
		Calculating the MAC


The Java **Mac (javax.crypto.Mac)** class can create a Message Authentication Code (MAC) from binary data. 
A MAC is a message digest which has been encrypted with a secret key. 
Only if you have the secret key can you verify the MAC

## Creating a Mac Instance

`Mac mac = Mac.getInstance("HmacSHA256");`



## Initializing the Mac

```
byte[] keyBytes   = new byte[]{0,1,2,3,4,5,6,7,8 ,9,10,11,12,13,14,15};
String algorithm  = "RawBytes";
SecretKeySpec key = new SecretKeySpec(keyBytes, algorithm);

mac.init(key);
```

## Calculating the MAC

```
byte[] data  = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
byte[] macBytes = mac.doFinal(data);
```


for example

```
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
            
        }catch (NoSuchAlgorithmException e){
            System.out.print(e.getMessage());
        }catch (Exception e1){
            System.out.print(e1.getMessage());
        }
    }
}


```

