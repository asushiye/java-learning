import java.rmi.Naming;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/29
 */

public class MyRemoteClient {
    public static void main(String[] args) {
        try{
            MyRemote myRemote = (MyRemote) Naming.lookup("rmi://127.0.0.1:1099/RemoteHello");
            String s=myRemote.sayHello("zhenyun.su");
            System.out.println(s);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
