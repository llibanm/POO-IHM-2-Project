package view;

import controler.AbstractControler;

public class StartButtonView extends MyButtonView {
    public StartButtonView() {
        super("Start", null, true);
    }

    @Override
    public void setControler(AbstractControler controler) {
        super.setControler(controler);
    }
}
