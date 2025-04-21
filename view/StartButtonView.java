package view;

import controler.abstractControler;

public class StartButtonView extends MyButtonView {
    public StartButtonView() {
        super("Start", null, true);
    }

    @Override
    public void setControler(abstractControler controler) {
        super.setControler(controler);
    }
}
