package view;
import controler.abstractControler;

public class QuitButtonView extends MyButtonView {


    public QuitButtonView() {
        super("Quit",null,true);


    }


    @Override
    public void setControler(abstractControler c) {
        super.setControler(c);
    }


}
