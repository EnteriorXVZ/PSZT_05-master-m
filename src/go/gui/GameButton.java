package go.gui;

import go.core.Point;
import go.core.StoneType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class GameButton extends JButton {
    private Point point;
    private StoneType buttonCollor;

    public GameButton(Point point, StoneType buttonCollor) {
        this.point = point;
        this.buttonCollor = buttonCollor;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("guzik "+point);
                UIHipervisor.getInstance().blackMove(point);
            }
        });
    }

    public GameButton(Icon icon, Point point, StoneType buttonCollor) {
        super(icon);
        this.point = point;
        this.buttonCollor = buttonCollor;
    }

    public GameButton(String text, Point point, StoneType buttonCollor) {
        super(text);
        this.point = point;
        this.buttonCollor = buttonCollor;
    }

    public GameButton(Action a, Point point, StoneType buttonCollor) {
        super(a);
        this.point = point;
        this.buttonCollor = buttonCollor;
    }

    public GameButton(String text, Icon icon, Point point, StoneType buttonCollor) {
        super(text, icon);
        this.point = point;
        this.buttonCollor = buttonCollor;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public StoneType getButtonCollor() {
        return buttonCollor;
    }

    public void setButtonCollor(StoneType buttonCollor) {
        this.buttonCollor = buttonCollor;
    }
}
