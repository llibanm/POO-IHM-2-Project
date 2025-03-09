package View;

import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class backgroundView extends BorderPane {

    private Image backgroundImage;

    public backgroundView(){

        backgroundImage = new Image("spaceImagesProject/Space_Background.png");

        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1200,1000,true,true,true,true)
        );

        this.setBackground(new Background(background));

    }


}
