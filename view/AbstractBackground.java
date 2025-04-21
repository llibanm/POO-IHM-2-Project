package view;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public abstract class AbstractBackground {

    private  Image image;
    private  BackgroundImage backgroundImg;
    private  Background background;

    public AbstractBackground(Image i, double width, double height){


        this.image = i;
        this.backgroundImg = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(width,height,true,true,true,true)
                );

        this.background = new Background(backgroundImg);

    }


    public Image getImage() {
        return image;
    }

    public BackgroundImage getBackgroundImage() {
        return backgroundImg;
    }

    public Background getBackground(){
        return background;
    }
}
