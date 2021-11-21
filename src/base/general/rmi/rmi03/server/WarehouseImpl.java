package base.general.rmi.rmi03.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base.general.rmi.rmi03.common.Product;
import base.general.rmi.rmi03.common.Warehouse;

public class WarehouseImpl extends UnicastRemoteObject implements Warehouse{
    private Map<String, Product> products;
    private Warehouse backup;
    public WarehouseImpl(Warehouse backup) throws RemoteException {
        products = new HashMap<>();
        this.backup = backup;
    }
    
    public void add(String keyword, Product product){
        product.setLocation(this);
        products.put(keyword, product);
    }
    @Override
    public double getPrice(String descr) throws RemoteException {
        for(Product p: products.values()){
            if(p.getDescription().equals(descr)){
                return p.getPrice();
            }
        }
        if(backup == null) return 0;
        else{
            return backup.getPrice(descr);
        }
    }

    @Override
    public Product getProduct(List<String> keywords) throws RemoteException {
        for(String keyword: keywords){
            Product p = products.get(keyword);
            if(p != null) return p;
        }
        if(backup != null) return backup.getProduct(keywords);
        else if(products.values().size() > 0) return products.values().iterator().next();
        else return null;
    }
    
}
