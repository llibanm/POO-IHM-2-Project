package src.java.org.projet.view;


import javafx.scene.layout.BorderPane;
import src.java.org.projet.controler.AbstractControler;


public abstract class AbstractView extends BorderPane {


    private AbstractControler controler;
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

    public AbstractControler getControler() {
        return controler;
    }

    public void modifyTitle(String t){
        this.Title = t;
    }

    public String getTitle() {
        return Title;
    }

    public void setControler(AbstractControler controler) {
        this.controler = controler;
    }
}
