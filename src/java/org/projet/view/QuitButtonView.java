package src.java.org.projet.view;

import src.java.org.projet.controler.AbstractControler;
import src.java.org.projet.view.MyButtonView;

public class QuitButtonView extends MyButtonView {


    public QuitButtonView() {
        super("Quit",null,true);


    }


    @Override
    public void setControler(AbstractControler c) {
        super.setControler(c);
    }


}
