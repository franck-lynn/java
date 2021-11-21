package base.general.rmi.rmi01;

import java.rmi.Remote;
import java.rmi.RemoteException;
// 定义一个远程服务接口. RMI 强制要求, 必须实现 Remote接口
public interface MyRemoteServerInterface extends Remote{
    // RMI强制要求, 所有远程服务方法, 必须抛出 RemoteException
    String session(String name) throws RemoteException;
}
