package View;

import Controler.abstractControler;
import View.Button.quitButton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.List;


public abstract class abstractView extends BorderPane {


    private abstractControler controler;
    private abstractBackground background;
    private List<Button> ButtonList;
    private String Title;

    public abstractView(String t, abstractBackground b){
        this.background = b;
        //this.controler = c;
        this.Title = t;

        this.ButtonList = new ArrayList<>();
        this.ButtonList.add(new quitButton(700,600));


        this.setBackground(background.getBackground());
        this.setBottom(ButtonList.getFirst());
        this.ButtonList.getFirst().setAlignment(Pos.BOTTOM_RIGHT);
    }

    public void modifyBackground(abstractBackground b){
        this.background = b;
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
