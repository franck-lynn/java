package base.general.rpc.rpc05;


import java.io.ObjectOutputStream;

import java.io.InputStream;
import java.io.ObjectInputStream;

import java.io.OutputStream;

import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import base.general.rpc.user.IUserService;
import base.general.rpc.user.User;

public class Server {
    // 远程服务的底层实现 
    private static boolean running  = true;
    public static void main(String[] args) throws Exception {
        // 服务端的套接字, 相当于服务端的一个港口
        ServerSocket ss = new ServerSocket(8888);
        while(running){
            // 港口在不停的吞吐货物, socket 相当于货轮
            Socket s = ss.accept();
            process(s);
            s.close();
        }
        ss.close();
    }
    // 货轮的处理
    private static void process(Socket s) throws Exception {
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        
        
        String methodName = ois.readUTF();
        Class<?>[] parameterTypes = (Class[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();
        
        
        IUserService service = new UserServiceImpl();
        Method method = service.getClass().getMethod(methodName, parameterTypes);
        User user = (User) method.invoke(service, args);
        
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(user);;
        
        oos.flush();    
        
        
    }
}
// ! 运行, 带本地工程 --classpath
/* 
java --enable-preview '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rpc.rpc05.Server 
*/