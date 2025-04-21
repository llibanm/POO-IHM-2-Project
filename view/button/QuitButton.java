package view.button;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class QuitButton extends Button {


    public QuitButton(double w, double h) {

        super("EXIT");
        this.setWidth(w);
        this.setHeight(h);
       this.setOnAction(event -> Platform.exit());
    }

}
