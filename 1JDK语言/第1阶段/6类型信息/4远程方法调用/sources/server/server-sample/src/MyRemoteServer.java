import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-11-08
 */

public class MyRemoteServer {
    public static void main(String[] args) {
        try{
            LocateRegistry.createRegistry(1099);
            MyRemote myRemote = new MyRemoteImp();
            Naming.rebind("RemoteHello",myRemote);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
