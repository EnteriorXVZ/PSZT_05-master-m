package go.gui;

import go.core.*;

import java.util.List;

/**
 * Created by Rados�aw on 2016-01-10.
 */
public class UIHipervisor {
    private static UIHipervisor uiHipervisor;

    private UIHipervisor() {
    }

    public static UIHipervisor getInstance() {
        if (uiHipervisor == null) {
            uiHipervisor = new UIHipervisor();
        }
        return uiHipervisor;
    }

    public void blackMove(Point point){
        GameHypervisor.getInstance().playBlack(point); // wstawia kamien do tablicy
//        ListOfButtonsSingleton.getInstance().changeButtonCollor(point, StoneType.BLACK);// zmienia kolor wcisnietego guzika na czarny
        updateUI();
    }

    private void updateUI() {
        GameState state = MainPlate.getInstance().getActualGameState();
        Stone p;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                p = state.getStones()[i][j];
                ListOfButtonsSingleton.getInstance().changeButtonCollor(p.getPoint(),p.getStoneType());
            }
        }
    }



    public void setDisabeButtons(List<Point> disablePoints){
//        ListOfButtonsSingleton.getInstance().setDisableButtons(disablePoints);
    }
}
