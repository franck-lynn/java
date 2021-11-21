package base.general.rpc.rpc07;


import java.io.ObjectOutputStream;

import java.io.InputStream;
import java.io.ObjectInputStream;

import java.io.OutputStream;

import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    // 远程服务的底层实现 
    private static boolean running  = true;
    // private static HashMap<String, Class<?>> resgisterTable = new HashMap<String, Class<?>>();
    
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
        
        // 从服务注册表找具体的类
        // 看起来像没有使用, 实际上从客户端按顺序读取, 即使要加上用以占位
        @SuppressWarnings("unused")
        String clazzName = ois.readUTF();
        
        String methodName = ois.readUTF();
        Class<?>[] parameterTypes = (Class[]) ois.readObject();
        Object[] args = (Object[]) ois.readObject();
        
        // 服务端通过传过来的类型找对应的类
        Class<?> clazz = null;
        clazz = UserServiceImpl.class;
        
        System.out.println("找到的类的类名-----> " + clazz.getName());
        
        
        Method method = clazz.getMethod(methodName, parameterTypes);
        Object o = (Object) method.invoke(clazz.getDeclaredConstructor().newInstance(), args);
        
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(o);;
        
        oos.flush();    
        
        
    }
}
// ! 运行, 带本地工程 --classpath
/* 
java --enable-preview '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rpc.rpc07.Server 
*/