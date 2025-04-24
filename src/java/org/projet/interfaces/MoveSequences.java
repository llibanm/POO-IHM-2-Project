package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.controler.levelEditorController.SpriteService;

import java.util.List;

public class MoveSequences {
    private List<ImageView> moveRightSequences;
    private List<ImageView> moveLeftSequences;
    private List<ImageView> moveUpSequences;
    private List<ImageView> moveDownSequences;
    private List<ImageView> currentMoveSequence;
    private  SpriteService spriteService;

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



    public List<ImageView> getMoveRightSequences() {
        return moveRightSequences;
    }

    public void setMoveRightSequences(List<ImageView> moveRightSequences) {
        this.moveRightSequences = moveRightSequences;
    }

    public List<ImageView> getMoveLeftSequences() {
        return moveLeftSequences;
    }

    public void setMoveLeftSequences(List<ImageView> moveLeftSequences) {
        this.moveLeftSequences = moveLeftSequences;
    }

    public List<ImageView> getMoveUpSequences() {
        return moveUpSequences;
    }

    public void setMoveUpSequences(List<ImageView> moveUpSequences) {
        this.moveUpSequences = moveUpSequences;
    }

    public List<ImageView> getMoveDownSequences() {
        return moveDownSequences;
    }

    public void setMoveDownSequences(List<ImageView> moveDownSequences) {
        this.moveDownSequences = moveDownSequences;
    }
}