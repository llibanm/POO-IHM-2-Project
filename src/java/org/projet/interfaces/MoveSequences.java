package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.services.SpriteService;

import java.util.List;

/**
 * Liste concrète des images des sprites pour chaque direction
 */
public class MoveSequences {
    private List<ImageView> moveRightSequences;
    private List<ImageView> moveLeftSequences;
    private List<ImageView> moveUpSequences;
    private List<ImageView> moveDownSequences;
    private List<ImageView> currentMoveSequence;
    private  SpriteService spriteService;

    /**
     *
     * @param spriteService Service permettant de manipuler les sprites
     * @param moveRight liste des images des sprites des mouvements vers la droite
     * @param moveLeft
     * @param moveUp
     * @param moveDown
     */
    public MoveSequences(SpriteService spriteService,
            List<ImageView> moveRight,
            List<ImageView> moveLeft,
            List<ImageView> moveUp,
            List<ImageView> moveDown
    ) {
        this.spriteService = spriteService;
        this.moveRightSequences = moveRight;
        this.moveLeftSequences = moveLeft;
        this.moveUpSequences = moveUp;
        this.moveDownSequences = moveDown;


    }




    /**
     * Liste concrète des images des sprites pour les déplacements à droite
     */
    public List<ImageView> getMoveRightSequences() {
        return moveRightSequences;
    }

    public void setMoveRightSequences(List<ImageView> moveRightSequences) {
        this.moveRightSequences = moveRightSequences;
    }

    /**
     * Liste concrète des images des sprites pour les déplacements à gauche
     */
    public List<ImageView> getMoveLeftSequences() {
        return moveLeftSequences;
    }

    public void setMoveLeftSequences(List<ImageView> moveLeftSequences) {
        this.moveLeftSequences = moveLeftSequences;
    }

    /**
     * Liste concrète des images des sprites pour les déplacements vers le haut
     */
    public List<ImageView> getMoveUpSequences() {
        return moveUpSequences;
    }

    public void setMoveUpSequences(List<ImageView> moveUpSequences) {
        this.moveUpSequences = moveUpSequences;
    }

    /**
     * Liste concrète des images des sprites pour les déplacements vers le bas
     */

    public List<ImageView> getMoveDownSequences() {
        return moveDownSequences;
    }

    public void setMoveDownSequences(List<ImageView> moveDownSequences) {
        this.moveDownSequences = moveDownSequences;
    }
}