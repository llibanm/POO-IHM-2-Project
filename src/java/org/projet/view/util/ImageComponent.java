package src.java.org.projet.view.util;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class ImageComponent {

    public static ImageView createImg(String imgUrl){
        Image image = new Image(imgUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        return imageView;
    }

    public  static  Background createImgBackground(String imgUrl, int width, int height){
        Image image = new Image(imgUrl);
        BackgroundImage backgroundImg = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(width,height,true,true,true,true)
        );
        return new Background(backgroundImg);
    }

}
