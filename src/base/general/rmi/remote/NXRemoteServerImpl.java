package base.general.rmi.remote;
// 官方教程在这里

// 执行方法 ---> 远程进程
// https://docs.plm.automation.siemens.com/tdoc/nx/10/nx_api/#uid:index_nxopen_prog_guide:id1142076:executing_remote_processes
// PS F:\working\study\yolanda\java\src>
// javac -encoding UTF-8 --class-path ".;D:/ugs/nx1980/nx1980/NXBIN/NXOpen.jar;D:/ugs/nx1980/nx1980/NXBIN/NXOpenUF.jar" base/remote/NXRemoteServerImpl.java  -d ../bin
/*=============================================================================
                    Copyright (c) 2012 Siemens PLM Solutions
                    Unpublished - All rights reserved

===============================================================================
File description: Sample NX/Open Application
===============================================================================

=============================================================================
*/

import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import nxopen.*;

/** Implements the NXRemoteServer interface */
public class NXRemoteServerImpl extends UnicastRemoteObject implements NXRemoteServer {
    private static String host;
    private static String serverName;
    private static Session theSession = null;
    private static UFSession theUFSession = null;
    private boolean isShutdownAllowed;

    public NXRemoteServerImpl() throws Exception {
        super();
        // 构造函数 host和serverName 没有定义. 取默认值
        host = System.getProperty("nxexamples.remoting.host");
        serverName = System.getProperty("nxexamples.remoting.servername");
        if (host == null || host.equals(""))
            host = "localhost"; // 主机名的默认值
        if (serverName == null)
            serverName = "NXServer"; // 别名, 取默认值. 客户端搜索服务器时的字符串
        isShutdownAllowed = (System.getProperty("nxexamples.remoting.allowshutdown") != null);
    }

    /** Starts the server and binds it with the RMI registry */
    // 协议 java_library
    // https://blog.csdn.net/u013517229/article/details/79530140
    // public void startServer() throws Exception {
    // System.out.println("Starting");
    // //
    // https://docs.plm.automation.siemens.com/data_services/resources/nx/11/nx_api/custom/en_US/open_java_ref/nxopen/NXRemotableObject.html
    // // 远程调用协议, 创建一个匿名端口的协议, 这里指定端口为 9898
    // NXRemotableObject.RemotingProtocol remotingProtocol =
    // NXRemotableObject.RemotingProtocol.create();

    // // // 服务器端的会话
    // theSession = (Session) SessionFactory.get("Session", remotingProtocol);
    // theUFSession = (UFSession) SessionFactory.get("UFSession", remotingProtocol);
    // System.out.println("Got Session");
    // theSession.listingWindow().open();
    // theSession.listingWindow().writeLine("Binding Session");
    // // 增加:
    // ListingWindow lw = theSession.listingWindow();
    // // Naming.rebind("//" + host + "/" + serverName, this);
    // // 修改:
    // // theSession.listingWindow().writeLine("Session bound");
    // lw.writeLine("Session bound 会话绑定");
    // lw.writeLine("服务器已运行...: " + "//" + host + "/" + serverName);
    // System.out.println("ready");
    // // 这句要放到下面, 不然, 服务器运行后执行不到这里来?
    // Naming.rebind("//" + host + "/" + serverName, this);
    // }

    public void startServer() {
        try {
            System.out.println("Starting...");
            // https://docs.plm.automation.siemens.com/data_services/resources/nx/11/nx_api/custom/en_US/open_java_ref/nxopen/NXRemotableObject.html
            // 远程调用协议, 创建一个匿名端口的协议, 这里指定端口为 9898
            NXRemotableObject.RemotingProtocol remotingProtocol = NXRemotableObject.RemotingProtocol.create();

            // 服务器端的会话
            theSession = (Session) SessionFactory.get("Session", remotingProtocol);
            theUFSession = (UFSession) SessionFactory.get("UFSession", remotingProtocol);
            System.out.println("Got Session");
            LocateRegistry.createRegistry(9898);
            // 增加:
            // ListingWindow lw = theSession.listingWindow();
            // lw.writeLine("Binding Session");
            // lw.writeLine("Session bound 会话绑定");
            // lw.writeLine("服务器已运行...:  " + "//" + host + "/" + serverName);
            
            System.out.println("ready");
            // 这句要放到下面, 不然, 服务器运行后执行不到这里来?
            Naming.rebind("rmi://" + host + ":9898/" + serverName, this);

        } catch (Exception e) {
            System.out.println();
        }
    }

    public Session session() throws RemoteException, NXException {
        return theSession;
    }

    public UFSession ufSession() throws RemoteException, NXException {
        return theUFSession;
    }

    public boolean isShutdownAllowed() throws RemoteException {
        return isShutdownAllowed;
    }

    private class ShutdownThread extends Thread {
        public void run() {
            try {
                Thread.sleep(250);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                // If the server is run inside of NX, executing exit
                // will cause NX to shut down as well
                System.exit(0);
            }
        }
    }

    public void shutdown() throws RemoteException {
        if (!isShutdownAllowed)
            throw new RemoteException("Shutdown not allowed");

        try {
            Naming.unbind("//" + host + "/" + serverName);
        } catch (Exception e) {
            throw new RemoteException("Exception during unbind, 解绑异常C  " +"//" + host + "/" + serverName +"|" + e.toString());
        } finally {
            // We need to shut down the server after this method
            // has returned. If we shut down before this method has
            // returned, the client will receive an exception.
            // So, we create a separate thread that will wait
            // briefly and then shut down the server.
            (new ShutdownThread()).start();
        }
    }

    public static void main(String[] args) throws Exception {
        // ! 主函数, 启动服务器
        NXRemoteServerImpl self = new NXRemoteServerImpl();
        self.startServer();
    }

    public static int getUnloadOption() {
        return BaseSession.LibraryUnloadOption.EXPLICITLY;
    }

    public static void onUnload() {

        try {
            theSession.listingWindow().open();
            theSession.listingWindow().writeLine("UnBinding Session");
            try {
                Naming.unbind("//" + host + "/" + serverName);
                theSession.listingWindow().writeLine("Session unbound");
            } catch (Exception e) {
                theSession.listingWindow().writeLine("Exception during unbind, 解绑异常B  " +"//" + host + "/" + serverName +"|" + e.toString());
                throw e;
            }

        } catch (Exception e) {
            try {
                theSession.listingWindow().writeLine("Exception during unbind, 解绑异常A  " +"//" + host + "/" + serverName +"|" + e.toString());
            } catch (Exception ex) {
                // Unfortunately, if we get an exception here we cannot report it
            }
        }
    }
}
// ! 服务器端编译
// ! 进入 src 目录编译, bin 目录运行
/*  
javac -encoding utf-8  --class-path='.;f:\\working\\study\\yolanda\\java\\src\\*;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar'  base/remote/NXRemoteServerImpl.java -d ../bin 
*/
// ! 运行, 带本地工程 --classpath -Djava.library.path=D:\\ugs\\nx1980\\nx1980\\NXBIN
/* 
java '-Dfile.encoding=UTF-8' '-Djava.library.path=.;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar'  base.remote.NXRemoteServerImpl
*/
// 如何注册远程服务
// rmiregistry -J-Djava.class.path='-Djava.library.path=.;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar'