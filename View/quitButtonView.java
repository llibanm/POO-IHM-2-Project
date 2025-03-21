package View;
import Controler.abstractControler;
import Controler.quitButtonController;
import javafx.scene.control.Button;

public class quitButtonView extends myButtonView {


    public quitButtonView() {
        super("Quit",null,true);


    }


    @Override
    public void setControler(abstractControler c) {
        super.setControler(c);
    }


}
