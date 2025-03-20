package View;
import Controler.abstractControler;
import Controler.quitButtonController;
import javafx.scene.control.Button;

public class quitButtonView extends abstractView {

    private Button quitButton;
    private quitButtonController qbc;
    public quitButtonView() {
        super("Exit",null);

        quitButton = new Button(getTitle());
    }

    public Button getQuitButton() {
        return quitButton;
    }

    @Override
    public void setControler(abstractControler c) {
        super.setControler(c);
    }
}
