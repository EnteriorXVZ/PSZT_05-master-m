package go.gui;

import go.core.*;
import go.core.Point;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class JPanelGenerator {
    private JPanel panel;

    public JPanelGenerator(){
        panel = new JPanel(new GridLayout(5,5,20,20));
        init();
    }

    private void init() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                GameButton button = new GameButton(new Point(i,j),StoneType.EMPTY);
                ListOfButtonsSingleton.getInstance().addGameButton(button);
                panel.add(button);
            }
        }
    }

    public JPanel getPanel(){
        return panel;
    }
}
