package swing;

import javax.swing.*;
import java.awt.*;

/**
 * @author : zhenyun.su
 * @comment :
 * @since : 2019-09-29
 */

public class Test {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        JButton click_me = new JButton("click me");
        JButton press = new JButton("press here");
        JCheckBox checkBox = new JCheckBox("choose me");
        JTextField jTextField = new JTextField("please input name");
        jFrame.getContentPane().add(BorderLayout.CENTER, click_me);
        jFrame.getContentPane().add(BorderLayout.EAST, press);
        jFrame.getContentPane().add(BorderLayout.EAST, checkBox);
        jFrame.getContentPane().add(BorderLayout.EAST, jTextField);
        jFrame.setSize(300, 300);
        jFrame.setVisible(true);
    }
}
