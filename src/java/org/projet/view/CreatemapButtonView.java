package src.java.org.projet.view;

import src.java.org.projet.controler.AbstractControler;
import src.java.org.projet.view.MyButtonView;

public class CreatemapButtonView extends MyButtonView {
    public CreatemapButtonView() {
        super("Map creation", null, true);
    }

    @Override
    public void setControler(AbstractControler controler) {
        super.setControler(controler);
    }
}
