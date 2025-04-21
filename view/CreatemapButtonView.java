package view;

import controler.abstractControler;

public class CreatemapButtonView extends MyButtonView {
    public CreatemapButtonView() {
        super("Map creation", null, true);
    }

    @Override
    public void setControler(abstractControler controler) {
        super.setControler(controler);
    }
}
