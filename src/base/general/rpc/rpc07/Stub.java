package base.general.rpc.rpc07;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
    // 返回任意对象接口
    public static Object getStub(Class<?> clazz) {
        InvocationHandler h = new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket s = new Socket("localhost", 8888);
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());

                // 把接口的名字给服务端
                String clazzName = clazz.getName();

                String methodName = method.getName();
                Class<?>[] parametersTypes = method.getParameterTypes();
                
                oos.writeUTF(clazzName);
                oos.writeUTF(methodName);
                oos.writeObject(parametersTypes);
                oos.writeObject(args);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

                // 组合成一个对象
                // User user = new User(id, name);
                Object o = ois.readObject();
                oos.close();
                s.close();

                return o;
            }

        };

        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, h);
        System.out.println("客户端调用的类名---> " +  o.getClass().getName());
        System.out.println("调用的接口---> " + o.getClass().getInterfaces()[0]);
        return o;
    }
}
