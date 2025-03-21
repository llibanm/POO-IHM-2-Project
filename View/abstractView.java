package View;

import Controler.abstractControler;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;


public abstract class abstractView extends BorderPane {


    private abstractControler controler;
    private abstractBackground background;

    private String Title;

    public abstractView(String t, abstractBackground b) {
        this.background = b;
        this.Title = t;



        if(b != null) {
            this.setBackground(background.getBackground());
        }


    }

    public void modifyBackground(abstractBackground b){
        this.background = b;
        this.setBackground(background.getBackground());
    }

    public abstractControler getControler() {
        return controler;
    }

    public void modifyTitle(String t){
        this.Title = t;
    }

    public String getTitle() {
        return Title;
    }

    public void setControler(abstractControler controler) {
        this.controler = controler;
    }
}
