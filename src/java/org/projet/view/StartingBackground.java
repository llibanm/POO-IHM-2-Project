package src.java.org.projet.view;

import javafx.scene.image.Image;
import src.java.org.projet.view.AbstractBackground;

public class StartingBackground extends AbstractBackground {

    private Image image;
    public StartingBackground(double width, double height) {
        super(new Image("src/java/org/projet/spaceImagesProject/Space_Background.png"), width, height);
    }
}
