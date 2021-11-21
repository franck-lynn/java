package base.general.swing;

import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.*;

public class SimpleMessage extends JFrame {
    public SimpleMessage(String msg) {
        SimpleFrameComponent sfc = new SimpleFrameComponent(msg);
        add(sfc);
        pack();
    }
}

class SimpleFrameComponent extends JComponent {
    // 一个简单的信息框
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 100;
    public static final int DEFAULT_WIDTH = 600;
    public static final int DEFAULT_HEIGHT = 200;
    private String msg;

    public SimpleFrameComponent() {
        super();
    }

    public SimpleFrameComponent(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return msg;
    }

    public void setMessage(String value) {
        msg = value;
    }

    @Override
    public void paint(Graphics g) {
        // 绘制文字
        g.drawString(msg, MESSAGE_X, MESSAGE_Y);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
