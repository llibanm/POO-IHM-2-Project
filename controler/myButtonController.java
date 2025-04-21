package controler;

import view.MyButtonView;
import javafx.scene.Cursor;

public class myButtonController extends abstractControler {
    public myButtonController() {
    }

    public void setAction(MyButtonView v) {
        if (v.getAnimation()) {
            v.getMyButton().setOnMouseEntered(e -> v.getMyButton().setCursor(Cursor.HAND));
            v.getMyButton().setOnMouseExited(e -> v.getMyButton().setCursor(Cursor.DEFAULT));
        }
    }
}