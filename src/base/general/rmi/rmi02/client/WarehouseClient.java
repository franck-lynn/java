package base.general.rmi.rmi02.client;

// https://www.bilibili.com/video/BV1u7411A7sv?p=45
import java.rmi.RemoteException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

import base.general.rmi.rmi02.common.WareHouse;

public class WarehouseClient {
    public static void main(String[] args) throws NamingException, RemoteException {
        // 上下文
        Context namingContext = new InitialContext();
        // 开始查找 RMI 注册表上有哪些绑定的服务
        System.out.println("RMI 注册表绑定列表: ");
        Enumeration<NameClassPair> e = namingContext.list("rmi://localhost:8001");
        while (e.hasMoreElements()) {
            System.out.println("列表--> " + e.nextElement().getName());
        }

        // 获取某一个地址上的服务类
        String url = "rmi://localhost:8001/warehouse1";

        WareHouse centralWarehouse = (WareHouse) namingContext.lookup(url);
        System.out.println(centralWarehouse.getClass().getName());

        String descr = "面包机";
        // 客户端发送参数
        double price = centralWarehouse.getPrice(descr);
        System.out.println("descr=" + descr + ": price=" + price);
    }
}
/*
 java --enable-preview '-Dfile.encoding=UTF-8'  --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\  nx1980\\NXBIN\\*;H:\\java_lib\\json.jar'  base.general.rmi.rmi02.client.WarehouseClient
 */