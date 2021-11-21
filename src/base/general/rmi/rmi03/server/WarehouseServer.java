package base.general.rmi.rmi03.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import base.general.rmi.rmi03.common.Product;

public class WarehouseServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        // 设置安全策略
        // System.setProperty("java.security.policy", "../common/client.policy");
        // System.setSecurityManager((new SecurityManager()));
        
        System.out.println("服务器构建实现中...");
        WarehouseImpl backupWarehouse = new WarehouseImpl(null);
        WarehouseImpl centralWarehouse = new WarehouseImpl(backupWarehouse);
        centralWarehouse.add("toaster", new Product("Blackwell Toaster", 23.95));
        
        System.out.println("将服务对象绑定在8001端口, 对外提供服务");
        LocateRegistry.createRegistry(8001);
        Naming.rebind("rmi://localhost:8001/warehouse1", centralWarehouse);
        System.out.println("等待客户端调用...");
    }
}
// ! 运行, 带本地工程 --classpath
/* 
java --enable-preview '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rmi.rmi03.server.WarehouseServer 
*/