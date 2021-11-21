package base.general.hello;

import org.json.JSONObject;

//! 没带编码, 进入文件路径下, 没带编码, 不能编译, 在各种命令行窗口都不能编译
// PS F:\working\study\yolanda\java\src\base\general\hello>
// javac HelloWorld.java -d ../../../../bin   
//! 带了编码, 进入文件路径下, 可以编译
// PS F:\working\study\yolanda\java\src\base\general\hello>
// javac -encoding utf-8 HelloWorld.java -d ../../../../bin  
//! 运行 , 进入文件目录运行, 下面2种都不能运行
// PS F:\working\study\yolanda\java\bin\base\general\hello> 
// java HelloWorld
// java base.general.hello.HelloWorld
//! 进入 bin 目录, 在 powerShell ISE 可以, cmd和powerShell乱码
// PS F:\working\study\yolanda\java\bin> 
// java base.general.hello.HelloWorld
//! cmd和powerShell乱码, 带编码运行, 可以运行, 但在 powerShell ISE 出现乱码
// java '-Dfile.encoding=UTF-8' base.general.hello.HelloWorld

//! 进入文件路径编译,带外部jar包, 可以编译, 
// PS F:\working\study\yolanda\java\src\base\general\hello>
// javac -encoding utf-8  --class-path='.;D:/ugs/nx1980/nx1980/NXBIN/*' HelloWorld.java -d ../../../../bin 
// javac -encoding utf-8  --class-path='.;D:/ugs/nx1980/nx1980/NXBIN/*;F:\working\study\yolanda\java\src' HelloWorld.java -d ../../../../bin 
//! 如果不加外部 jar 包, 则报 HelloWorld.java:3: 错误: 程序包nxopen不存在 
// javac -encoding utf-8  --class-path=. HelloWorld.java -d ../../../../bin  
//! 运行时进入 bin 目录, PWSH ISE 不用带编码, vscode 带不带没关系, cmmd 这些一定要带
//  java '-Dfile.encoding=UTF-8' base.general.hello.HelloWorld
//  java  base.general.hello.HelloWorld
//! 编译多个文件时, 要把本地的路径(是源文件的路径加入编译路径才可以编译)
// javac -encoding utf-8  --class-path='.;D:/ugs/nx1980/nx1980/NXBIN/*;F:\working\study\yolanda\java\src\*' HelloWorld.java -d ../../../../bin 
//! 执行时加与不加都是可以的
//  java '-Dfile.encoding=UTF-8' base.general.hello.HelloWorld
//  java  base.general.hello.HelloWorld
// java '-Dfile.encoding=UTF-8' --class-path='.;D:/ugs/nx1980/nx1980/NXBIN;F:\working\study\yolanda\java\bin' base.general.hello.HelloWorld

//! 进入源路径, 引用了第3方jar 包, 则该包的路径要加到 --class-path 里面来,
//! 而不仅仅是在 setting.json 中定义 java.project.referencedLibraries 就可以的
// PS F:\working\study\yolanda\java\src>
// javac -encoding utf-8 --class-path='.;D:/ugs/nx1980/nx1980/NXBIN/*;F:\working\study\yolanda\java\src\*;H:/java_lib/*' base/general/hello/HelloWorld.java -d ../bin 
//! 执行时也要带上 --class-path
// java '-Dfile.encoding=UTF-8' --class-path='.;D:/ugs/nx1980/nx1980/NXBIN/*;F:\working\study\yolanda\java\bin\*;H:/java_lib/*' base.general.hello.HelloWorld
//! 进入所在文件目录下进行编译, 要路径, 不要 \\* 
// javac -encoding UTF-8 --class-path='.;f:\\working\\study\\yolanda\\java\\src;D:\\ugs\\nx1980\\nx1980\\NXBIN;H:\\java_lib\\json.jar' HelloWorld.java -d ../../../../bin
//! 但只有1个jar时, 要把jar的名称和后缀一起带上
// javac -encoding UTF-8 --class-path='.;f:\working\study\yolanda\java\src;D:/ugs/nx1980/nx1980/NXBIN;H:/java_lib/json.jar' HelloWorld.java -d ../../../../bin
//! -d 输出到完整的路径名也是可以的
// javac -encoding UTF-8 --class-path='.;f:\working\study\yolanda\java\src;D:/ugs/nx1980/nx1980/NXBIN;H:/java_lib/json.jar' HelloWorld.java -d f:\working\study\yolanda\java\bin

public class HelloWorld {
    public static void main(String[] args) {
        User u = new User("张三", 20);
        System.out.println("hello world! 你好, 世界" + u.getName());
        
        String jsonContent = "{'hello': 'world', 'abc': 'xyz}"; 
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonContent);
            String str1 = jsonObject.getString("hello");
            String str2 = jsonObject.getString("abc");
            
            System.out.println(str1 + "----" + str2);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        

    }
}


/* 
java --enable-preview '-Dfile.encoding=UTF-8' --class-path='.;f:\\working\\study\\yolanda\\java\\bin;D:\\ugs\\nx1980\\nx1980\\NXBIN\\*;H:\\java_lib\\json.jar' base.general.hello.HelloWorld 
*/