package base.general.swing;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class SimpleMessageTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                JFrame frame = new SimpleMessage("设置信息");
                frame.setTitle("信息框");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
            
        });
    }
}
