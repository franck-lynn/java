package base.general.hello;
public class Welcome{
    public static void main(String[] args) {
        String[] greeting = new String[3];
        greeting[0] = "Welcome to Core Java, 如何添加第3方jar包";
        greeting[1] = "by Cay Horstman";
        greeting[2] = "and Gary Cornell";
        for(String g: greeting){
            System.out.println(g);
        }
    }
}