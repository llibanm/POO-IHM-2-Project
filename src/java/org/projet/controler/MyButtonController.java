package src.java.org.projet.controler;


import javafx.scene.Cursor;
import src.java.org.projet.view.MyButtonView;

public class MyButtonController extends AbstractControler {
    public MyButtonController() {
    }

    public void setAction(MyButtonView v) {
        if (v.getAnimation()) {
            v.getMyButton().setOnMouseEntered(e -> v.getMyButton().setCursor(Cursor.HAND));
            v.getMyButton().setOnMouseExited(e -> v.getMyButton().setCursor(Cursor.DEFAULT));
        }
    }
}