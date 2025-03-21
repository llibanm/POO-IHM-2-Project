package View;

import Controler.abstractControler;

public class startButtonView extends myButtonView{
    public startButtonView() {
        super("Start", null, true);
    }

    @Override
    public void setControler(abstractControler controler) {
        super.setControler(controler);
    }
}
