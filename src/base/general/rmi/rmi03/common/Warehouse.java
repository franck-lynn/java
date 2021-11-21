package base.general.rmi.rmi03.common;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Warehouse extends Remote{

    double getPrice(String descr) throws RemoteException;
    Product getProduct(List<String> keywords) throws RemoteException;
}
