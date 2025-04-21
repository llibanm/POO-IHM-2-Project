package view;

import controler.AbstractControler;

public class CreatemapButtonView extends MyButtonView {
    public CreatemapButtonView() {
        super("Map creation", null, true);
    }

    @Override
    public void setControler(AbstractControler controler) {
        super.setControler(controler);
    }
}
