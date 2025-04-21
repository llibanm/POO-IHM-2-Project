package view;

import controler.abstractControler;
import javafx.scene.layout.BorderPane;


public abstract class AbstractView extends BorderPane {


    private abstractControler controler;
    private AbstractBackground background;

    private String Title;

    public AbstractView(String t, AbstractBackground b) {
        this.background = b;
        this.Title = t;



        if(b != null) {
            this.setBackground(background.getBackground());
        }


    }

    public void modifyBackground(AbstractBackground b){
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
