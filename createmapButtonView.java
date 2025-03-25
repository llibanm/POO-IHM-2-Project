package View;

import Controler.abstractControler;
import Controler.createmapButtonController;

public class createmapButtonView extends myButtonView {
    public createmapButtonView() {
        super("Map creation", null, true);
    }

    @Override
    public void setControler(abstractControler controler) {
        super.setControler(controler);
    }
}
