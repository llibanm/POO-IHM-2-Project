package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

/**
 * Caractéristiques propres aux entités mouvables
 */
public interface Movable {

    /**
     * Obtenir la prochaine image representant une séquence précise d'un
     * mouvement dans une direction
     * @param rowX déplacement en Row
     * @param rowY déplacement en Col
     * @return prochaine image de la sequence du sprite
     */
    public ImageView nextImage(int rowX, int rowY);
    public int getSpeed();
    public Coord getCoord();
    public void setCoord(Coord position);

    /**
     * Obtenir l'orientation de son déplacement (décalage row, colonne)
     * @return orientation du déplacement
     */
    public Coord getMoveDirection();

    /**
     * Fixer l'orientation de son déplacement (décalage row, colonne)
     * @return orientation du déplacement
     */
    public void setMoveDirection(int deltaRow, int deltaCol);
}