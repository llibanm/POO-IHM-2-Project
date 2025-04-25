package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public interface Movable {

    public ImageView nextImage(int rowX, int rowY);
    public int getSpeed();
    public Coord getCoord();
    public void setCoord(Coord position);
}