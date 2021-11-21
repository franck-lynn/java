package base.general.rmi.rmi01;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;


// 主方法, 创建一个服务实现, 提供服务, 并注册到 Registry 上
// RMI的Registry 在创建时, 会自动启动一个子线程, 并升级为守护线程
public class MainClass {
    public static void main(String[] args) {
        try {
            System.out.println("服务器启动中...");
            // 创建服务对象
            MyRemoteServerInterface session = new MyRemoteServerImpl();
            // 注册到 Registry(注册中心) 上
            LocateRegistry.createRegistry(9999);
            // 绑定一个服务到注册中心, 提供命名格式为:
            // rmi://ip:port/别名
            // 如果服务重复, 抛出异常, 重复定义是命名冲突
            // Naming.bind("rmi://localhost:3001", session);
            // 重新绑定一个服务到注册中心, 和 bind 区别是命名重复, 直接覆盖
            // ! 这句要放在 rebind() 上面, 不然, 执行不到这里
            System.out.println("服务器启动完成: rmi://localhost:9999/first");
            Naming.rebind("rmi://localhost:9999/first", session);

        } catch (Exception e) {
            // System.out.println(e);
            // e.printStackTrace();
        }
    }
}
// ! 服务器端编译
// ! 进入 src 目录编译, bin 目录运行
// javac -encoding utf-8 --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base/general/rmi/MainClass.java -d ../bin
// ! 运行, 带本地工程 --classpath
// java '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rmi.MainClass
// !  打成 jar 包运行, 在 bin 目录下运行, 要打包 rmi 下所有文件
// jar --create --file=../open/application/rmi.jar --main-class=base.general.rmi.MainClass --create base/general/rmi  base/general/swing