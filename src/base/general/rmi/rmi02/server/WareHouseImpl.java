package base.general.rmi.rmi02.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import base.general.rmi.rmi02.common.WareHouse;

public class WareHouseImpl extends UnicastRemoteObject implements WareHouse{
    private Map<String, Double> prices;
    
    protected WareHouseImpl() throws RemoteException {
        prices = new HashMap<String, Double>();
        prices.put("面包机", 24.9);
        prices.put("微波炉", 49.95);
    }

    @Override
    public double getPrice(String descr) throws RemoteException {
        Double price = prices.get(descr);
        return price == null ? 0: price;
    }
}
