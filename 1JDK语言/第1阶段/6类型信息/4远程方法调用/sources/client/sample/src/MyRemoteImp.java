import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019/8/29
 */

public class MyRemoteImp extends UnicastRemoteObject implements MyRemote {
    @Override
    public String sayHello(String name) throws RemoteException {
        System.out.println("RMI Hello "+name);
        return "RMI Hello "+name;
    }

    public MyRemoteImp() throws RemoteException {
    }

    public static void main(String[] args) {
        try{
            MyRemote myRemote = new MyRemoteImp();
            Naming.rebind("RemoteHello",myRemote);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
