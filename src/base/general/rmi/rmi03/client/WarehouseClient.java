package base.general.rmi.rmi03.client;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;

import base.general.rmi.rmi03.common.Product;
import base.general.rmi.rmi03.common.Warehouse;

public class WarehouseClient {
    public static void main(String[] args) throws Exception {
        // 设置安全策略
        // System.setProperty("java.security.policy", "../common/server.policy");
        // System.setSecurityManager((new SecurityManager()));
        
        Context namingContext = new InitialContext();
         // 开始查找 RMI 注册表上有哪些绑定的服务
         System.out.println("RMI 注册表绑定列表: ");
         
         
         NamingEnumeration<NameClassPair> e = namingContext.list("rmi://localhost:8001");
         while (e.hasMore()) {
             System.out.println("列表--> " + e.next().getName());
         }
 
         // 获取某一个地址上的服务类
         String url = "rmi://localhost:8001/warehouse1";
        // 客户端获取服务器上的服务类
         Warehouse centralWarehouse = (Warehouse) namingContext.lookup(url);
         
        Scanner in = new Scanner(System.in);
        System.out.println("输入关键字:");
        List<String> keywords = Arrays.asList(in.nextLine().split("\\s+"));
        Product prod = centralWarehouse.getProduct(keywords);
        
        System.out.println(prod.getDescription() + ": " + prod.getPrice());
        System.out.println(prod.getLocation());
        
        in.close();
    }
}
/*
 java --enable-preview '-Dfile.encoding=UTF-8'  --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\  nx1980\\NXBIN\\*;H:\\java_lib\\json.jar'  base.general.rmi.rmi03.client.WarehouseClient
 */