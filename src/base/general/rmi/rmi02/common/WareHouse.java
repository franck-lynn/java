package base.general.rmi.rmi02.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WareHouse extends Remote{

    double getPrice(String descr) throws RemoteException;
    
}
