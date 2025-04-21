package view;

import javafx.scene.image.Image;

public class StartingBackground extends AbstractBackground {

    private Image image;
    public StartingBackground(double width, double height) {
        super(new Image("spaceImagesProject/Space_Background.png"), width, height);
    }
}
