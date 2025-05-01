package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.services.SpriteService;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

import java.util.List;


/**
 * Classe representant les caractéristique d'une entité pouvant se déplacer
 * dans les 4 directions
 */
public abstract class Views4OrientationImgCharacter  {

    /**
     * dernière orientation de l'entité(sens du mouvement)
     */
    private Direction lastDirection;



    private int currentFrameIndex = 0;
    private List<ImageView> moveRightSequences;
    private List<ImageView> moveLeftSequences;
    private List<ImageView> moveUpSequences;
    private List<ImageView> moveDownSequences;
    private List<ImageView> currentMoveSequence;
    private  SpriteService spriteService;


    private final MyLogger logger = new MyLogger(Views4OrientationImgCharacter.class);

    /**
     * Constructeur
     * @param spriteService service de manipulation des sprites
     * @param moveRangeOnSprite Coordonnées des images pour les séquences de mouvement
     *                          pour les 4 directions
     */
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
        this.currentMoveSequence = moveDownSequences;
    }

    public Views4OrientationImgCharacter(){

    }

    /**
     * Fixer la dernière orientation connue
     * @param lastDirection orientation
     */
    public void setLastDirection(Direction lastDirection) {
        this.lastDirection = lastDirection;
    }

    /**
     * Fixer la dernière orientation connue vie des Coord
     * @param lastDirection
     */
    public void setLastDirectionCoord(Coord lastDirection) {
        this.lastDirection = coordToDirection(lastDirection.getRow(), lastDirection.getCol());
    }

    /**
     *
     * @return Direction Obtenir la dernère orientation (up, down...)
     */
    public Direction getLastDirection() {
        return lastDirection;
    }

    /**
     * Convertir un déplacement en coordonnées en direction
     *
     * @param row décalage en Row
     * @param col décalage en Colonne
     * @return sens du déplacement haut, bas, gauche, droite
     */
    public Direction coordToDirection(int row, int col) {
        if (row == -1 && col == 0) return Direction.UP;
        if (row == 1 && col == 0) return Direction.DOWN;
        if (row == 0 && col == 1) return Direction.RIGHT;
        if (row == 0 && col == -1) return Direction.LEFT;
        logger.warning("Invalid direction input.");
        return lastDirection;
    }

    /**
     * Convertir une direction en décalage de coordonnées
     * @param direction Haut, bas, gauche, droite
     * @return  le décalage de coordonnées fournissant cette direction
     */
    public Coord directionToCoord(Direction direction) {
        return switch (direction) {
            case UP -> new Coord(-1, 0);
            case DOWN -> new Coord(1, 0);
            case RIGHT -> new Coord(0, 1);
            case LEFT -> new Coord(0, -1);
        };
    }


    /**
     * Fixer la bonne séquence de mouvements en fonction de l'orientation
     * de L'entité
     * @param direction
     */
    public void updateCurrentSequence(Direction direction) {
        switch (direction) {
            case RIGHT -> currentMoveSequence = moveRightSequences;
            case LEFT -> currentMoveSequence = moveLeftSequences;
            case UP -> currentMoveSequence = moveUpSequences;
            case DOWN -> currentMoveSequence = moveDownSequences;
        }
    }

    /**
     * Calculer la prochaine image à afficher
     * @param row déplacement en Row
     * @param col déplacement en Colonne
     * @return Image
     */
    public ImageView nextImage(int row, int col) {
        logger.info("Prochaine image row :"+ row+" col "+col);
        Direction direction = coordToDirection(row, col);
        //Si la direction change on reset la frame
        if (direction != lastDirection) {
            updateCurrentSequence(direction);
            logger.info("Changement de direction sequence courante  "+ direction);
            currentFrameIndex = 0;
            lastDirection = direction;
        }

        logger.info("Current frame index: "+ currentFrameIndex+" Current MoveSequence "+currentMoveSequence);
        ImageView frame = currentMoveSequence.get(currentFrameIndex);
        currentFrameIndex = (currentFrameIndex + 1) % currentMoveSequence.size();
        return frame;
    }
}