package base.general.rpc.rpc01;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import base.general.rpc.user.User;

public class Client{
    public static void main(String[] args) throws UnknownHostException, IOException {
        // 客户端套接字, 相当于货轮
        Socket s = new Socket("localhost", 8888);
        // 货物(二进制), 相当于集装箱
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 相当于一个船
        DataOutputStream dos = new DataOutputStream(baos);
        // 集装箱装船
        dos.writeInt(123);
        // 集装箱里的货物装成二进制装船
        s.getOutputStream().write(baos.toByteArray()); // 转成二进制
        // 运出去
        s.getOutputStream().flush();
        
        // 返回的数据在船里(船里有集装箱)
        DataInputStream dis = new DataInputStream(s.getInputStream());
        // 读取集装箱里的数据
        int id = dis.readInt();
        String name =  dis.readUTF();
        // 组合成一个对象
        User user = new User(id, name);
        
        System.out.println(user);
        dos.close();
        s.close();
    }
}
// ! 运行, 带本地工程 --classpath
/* 
java '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rpc.rpc01.Client 
*/