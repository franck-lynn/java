package base.general.swing;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class SimpleFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class SimpleFrame extends JFrame{
    
    public SimpleFrame(){
        // 获取屏幕尺寸
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int sreenHeight = screenSize.height;
        
        setSize(screenWidth / 3, sreenHeight / 3);
        setTitle("信息框");
        setLocationByPlatform(true);
    }
    
}
