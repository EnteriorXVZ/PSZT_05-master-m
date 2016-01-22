package go.gui;

import javax.swing.*;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class MainFrame {
    private JFrame frame;
    private JPanel panel;

    public MainFrame(){
        prepareGUI();
    }

    private void prepareGUI() {
        frame = new JFrame(" PSZT - Gra w go ");
        panel = new JPanelGenerator().getPanel();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300,150,500,500);
        frame.setVisible(true);
    }
}
