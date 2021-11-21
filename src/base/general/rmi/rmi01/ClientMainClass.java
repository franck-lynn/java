package base.general.rmi.rmi01;

import java.rmi.Naming;

// 客户端主方法
public class ClientMainClass {
    public static void main(String[] args) {
        // 代理对象的创建
        MyRemoteServerInterface proxy = null;
        try{
            // 找服务, 通过名字找服务, 并自动创建代理对象
            // 类型是 Object, 对象一定是 proxy 子类型, 并一定实现了服务接口
            proxy = (MyRemoteServerInterface) Naming.lookup("rmi://localhost:9999/first");
            System.out.println("客户端打印: " + proxy);
            String result = proxy.session("客户端发送的参数, 服务器收到后 MyRemoteServerImpl 负责运行AA");
            System.out.println("客户端打印: " + result);
            
        }catch(Exception e){
            System.out.println("客户端打印错误: " + e);
        }
    }
}
//! 客户端编译
//! 进入 src 目录编译, bin 目录运行
// javac -encoding utf-8 --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base/general/rmi/ClientMainClass.java -d ../bin 
//! 客户端运行
// java '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rmi.ClientMainClass 