package go.gui;

import go.core.Point;
import go.core.StoneType;
import javafx.scene.control.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Rados³aw on 2016-01-10.
 */
public class ListOfButtonsSingleton {
    private static ListOfButtonsSingleton listOfButtonsSingleton;
    private List<GameButton> buttons;

    private ListOfButtonsSingleton(){
        buttons = new ArrayList<>();
    }

    public static ListOfButtonsSingleton getInstance() {
        if(listOfButtonsSingleton==null){
            listOfButtonsSingleton = new ListOfButtonsSingleton();
        }
        return listOfButtonsSingleton;
    }

    public List<GameButton> getButtons() {
        return buttons;
    }

    public void addGameButton(GameButton button){
        this.buttons.add(button);
    }

    public void changeButtonCollor(Point point, StoneType type){
        for(GameButton button: buttons){
            if(button.getPoint().equals(point)){
                changeCollor(button,type);
                break;
            }
        }
    }

    private void changeCollor(GameButton button, StoneType type) {
        button.setButtonCollor(type);
        switch (type){
            case BLACK:
                button.setBackground(Color.BLACK);
//                button.setEnabled(false);
                break;
            case WHITE:
                button.setBackground(Color.WHITE);
//                button.setEnabled(false);
                break;
            default:
                button.setBackground(Color.ORANGE);
                break;
        }

//        button.setEnabled(false);
    }

//    private void setDisableEnebleButtons(List<Point> disablePoints, boolean val) {
//        for(Point point: disablePoints){
//            for (GameButton button: buttons){
//                if(button.getPoint().equals(point)){
//                    button.setEnabled(val);
//                }
//            }
//        }
//    }
//
//    public void setDisableButtons(List<Point> points){
//        setDisableEnebleButtons(points,false);
//    }
//    public void setEnableButtons(List<Point> points){
//        setDisableEnebleButtons(points,true);
//    }
}
