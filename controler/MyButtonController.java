package controler;

import view.MyButtonView;
import javafx.scene.Cursor;

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