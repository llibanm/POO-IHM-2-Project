package View;

import Controler.abstractControler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.List;


public abstract class abstractView extends BorderPane {


    private abstractControler controler;
    private abstractBackground background;
    private List<Button> ButtonList;

    public abstractView(abstractControler c, abstractBackground b){
        this.background = b;
        this.controler = c;

        this.setBackground(background.getBackground());
    }

    public void modifyBackground(abstractBackground b){
        this.background = b;
    }




    public abstractControler getControler() {
        return controler;
    }
}
