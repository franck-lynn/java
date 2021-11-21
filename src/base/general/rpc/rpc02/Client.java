package base.general.rpc.rpc02;



public class Client{
    public static void main(String[] args) throws Exception {
       Stub stub = new Stub();
       System.out.println(stub.findUserById(123));
    }
}
// ! 运行, 带本地工程 --classpath
/* 
java --enable-preview '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rpc.rpc02.Client 
*/