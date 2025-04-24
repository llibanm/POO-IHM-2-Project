package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.controler.levelEditorController.SpriteService;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

import java.util.List;
import java.util.logging.Logger;



public abstract class Views4OrientationImgCharacter  {

    private Direction lastDirection;
    private int currentFrameIndex = 0;
    private List<ImageView> moveRightSequences;
    private List<ImageView> moveLeftSequences;
    private List<ImageView> moveUpSequences;
    private List<ImageView> moveDownSequences;
    private List<ImageView> currentMoveSequence;
    private  SpriteService spriteService;


    private final Logger logger = Logger.getLogger(Views4OrientationImgCharacter.class.getName());

    public Views4OrientationImgCharacter(
            SpriteService spriteService,
        MoveRangeOnSprite moveRangeOnSprite
    ) {
        this.spriteService = spriteService;
        this.lastDirection = Direction.DOWN;
        this.moveLeftSequences = spriteService.getRangeSprite(moveRangeOnSprite.spriteMoveLeftRange);
        this.moveRightSequences = spriteService.getRangeSprite(moveRangeOnSprite.spriteMoveRightRange);
        this.moveUpSequences = spriteService.getRangeSprite(moveRangeOnSprite.spriteMoveUpRange);
        this.moveDownSequences = spriteService.getRangeSprite(moveRangeOnSprite.spriteMoveDownRange);

    }



    public Direction coordToDirection(int row, int col) {
        if (row == -1 && col == 0) return Direction.UP;
        if (row == 1 && col == 0) return Direction.DOWN;
        if (row == 0 && col == 1) return Direction.RIGHT;
        if (row == 0 && col == -1) return Direction.LEFT;
        logger.warning("Invalid direction input.");
        return lastDirection;
    }

    public void updateCurrentSequence(Direction direction) {
        switch (direction) {
            case RIGHT -> currentMoveSequence = moveRightSequences;
            case LEFT -> currentMoveSequence = moveLeftSequences;
            case UP -> currentMoveSequence = moveUpSequences;
            case DOWN -> currentMoveSequence = moveDownSequences;
        }
    }

    public ImageView nextImage(int row, int col) {
        Direction direction = coordToDirection(row, col);

        // Si direction change → reset frame
        if (direction != lastDirection) {
            updateCurrentSequence(direction);
            currentFrameIndex = 0;
            lastDirection = direction;
        }

        ImageView frame = currentMoveSequence.get(currentFrameIndex);
        currentFrameIndex = (currentFrameIndex + 1) % currentMoveSequence.size();
        return frame;
    }
}