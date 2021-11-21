package base.general.rpc.rpc02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import base.general.rpc.user.IUserService;
import base.general.rpc.user.User;

public class Server {
    // 远程服务的底层实现 
    private static boolean running  = true;
    public static void main(String[] args) throws IOException {
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
    private static void process(Socket s) throws IOException {
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        DataInputStream dis = new DataInputStream(in);
        DataOutputStream dos = new DataOutputStream(out);
        //! 服务端接受的id
        int id = dis.readInt();
        IUserService service = new UserServiceImpl();
        User user = service.findUserById(id);
        System.out.println("获得用户名---> " + user);
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());    
        dos.flush();      
    }
}
// ! 运行, 带本地工程 --classpath
/* 
java --enable-preview '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rpc.rpc02.Server 
*/