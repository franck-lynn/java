package base.general.rpc.rpc02;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import base.general.rpc.user.User;

public class Stub {
    public User findUserById(Integer identity) throws UnknownHostException, IOException {
        // 客户端套接字, 相当于货轮
        Socket s = new Socket("localhost", 8888);
        // 货物(二进制), 相当于集装箱
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 相当于一个船
        DataOutputStream dos = new DataOutputStream(baos);
        // 集装箱装船
        dos.writeInt(identity);
        // 集装箱里的货物装成二进制装船
        s.getOutputStream().write(baos.toByteArray()); // 转成二进制
        // 运出去
        s.getOutputStream().flush();

        // 返回的数据在船里(船里有集装箱)
        DataInputStream dis = new DataInputStream(s.getInputStream());
        // 读取集装箱里的数据
        //! 服务端发送过来的是两段数据, 接收的时候还是要分别接受
        int id = dis.readInt();
        String name = dis.readUTF();
        System.out.println("获得用户名---> " + name);
        // 组合成一个对象
        User user = new User(id, name);
        
        dos.close();
        s.close();
        return user;
    }
}
