package base.general.rmi.rmi01;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import base.general.swing.*;
import javax.swing.JFrame;
import java.awt.EventQueue;


// 实现远程服务接口, 所有的远程服务实现, 必须是
// Remote 接口直接或间接实现
public class MyRemoteServerImpl extends UnicastRemoteObject implements MyRemoteServerInterface{
    
    public MyRemoteServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String session(String name) throws RemoteException{
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new SimpleMessage(name);
                frame.setTitle("信息框");
                frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        System.out.println("客户端的请求参数: " + name);
        
        
        
        return "你好, " + name;
    }
}
