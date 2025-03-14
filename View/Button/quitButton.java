package View.Button;
import javafx.application.Platform;
import javafx.scene.control.Button;

public class quitButton extends Button {


    public quitButton(double w, double h) {

        super("EXIT");
        this.setWidth(w);
        this.setHeight(h);
       this.setOnAction(event -> Platform.exit());
    }

}
