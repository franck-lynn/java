package base.general.io;

import java.io.File;

public class FileDemo {
    public static void main(String[] args) {
        String separator = File.pathSeparator;
        System.out.println(separator);
        function_1();
    }
    
    public static void function_1(){
        File file = new File("./FileDemo.java");
        System.out.println(file);
    }
}