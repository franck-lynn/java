package base.general.rpc.rpc04;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import base.general.rpc.user.IUserService;
import base.general.rpc.user.User;

public class Stub {
    public static IUserService getStub(){
        InvocationHandler h = new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket s = new Socket("localhost", 8888);
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                
                
                String methodName = method.getName();
                Class<?>[] parametersTypes = method.getParameterTypes();
                oos.writeUTF(methodName);
                oos.writeObject(parametersTypes);
                oos.writeObject(args);
                oos.flush();
                
               
               
                DataInputStream dis = new DataInputStream(s.getInputStream());
                int id = dis.readInt();
                String name = dis.readUTF();
                System.out.println("获得用户名---> " + name);
                // 组合成一个对象
                User user = new User(id, name);
                
                oos.close();
                s.close();
                return user;
            }
            
        };
        Object o = Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class[]{IUserService.class}, h);
        System.out.println(o.getClass().getName());
        return (IUserService) o;
    }
}
