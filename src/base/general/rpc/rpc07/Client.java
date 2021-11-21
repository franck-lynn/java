package base.general.rpc.rpc07;

import base.general.rpc.user.IUserService;

public class Client{
    public static void main(String[] args) throws Exception {
       IUserService service = (IUserService) Stub.getStub(IUserService.class);
       System.out.println(service.findUserById(123));
    }
}
// ! 运行, 带本地工程 --classpath
/* 
java --enable-preview '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.rpc.rpc06.Client 
*/