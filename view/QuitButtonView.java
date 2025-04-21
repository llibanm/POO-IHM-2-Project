package view;
import controler.AbstractControler;

public class QuitButtonView extends MyButtonView {


    public QuitButtonView() {
        super("Quit",null,true);


    }


    @Override
    public void setControler(AbstractControler c) {
        super.setControler(c);
    }


}
