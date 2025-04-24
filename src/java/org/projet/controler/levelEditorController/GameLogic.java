package src.java.org.projet.controler.levelEditorController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.util.List;
import java.util.logging.Logger;

public class GameLogic {
    private final MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;


    Timeline enemyMovementLoop;
    private  final Logger logger = Logger.getLogger(GameLogic.class.getName());

    public GameLogic(MatrixLvlEditorModel model, MatrixLvLEditorView view) {
        this.model = model;
        this.view = view;
        startEnemyMovementLoop();
    }

    public boolean moveHero(int deltaRow, int deltaCol) {
        if (model.moveHero(deltaRow, deltaCol)) {
            Coord coord = new Coord(
                    model.getHero().getCoord().getRow() + deltaRow,
                    model.getHero().getCoord().getCol() + deltaCol
            );
            model.getHero().setCoord(coord);
            model.setOccuped(coord, true);
            return true;
        }
        return false;
    }



    public void startEnemyMovementLoop() {
        enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(1.0), e -> moveEnemies()));
        enemyMovementLoop.setCycleCount(Animation.INDEFINITE);
        enemyMovementLoop.play();
    }

    public void moveEnemies() {
        List<Ennemy> ennemies = model.getEnnemies();
        int i = 0;

        while (i < ennemies.size()) {
            Ennemy ennemy = ennemies.get(i);
            logger.info(ennemy.toString());
            model.setActiveEnnemy(ennemy);
            Coord oldPos = ennemy.getPosition();
            logger.info("oldPos: " + oldPos);
            Coord newPos = aiComputeNextMove(ennemy);
            logger.info("newPos: " + newPos);

            if (!model.isOccupedCase(newPos.getRow(), newPos.getCol())) {
                ennemy.setPosition(newPos);
                model.freeLastCastOccupedNewCase(oldPos, newPos);
            }
            i++;
        }
    }

    public Coord aiComputeNextMove(Ennemy ennemy) {
        Coord heroPos = model.getHero().getCoord();
        logger.info("heroPos: " + heroPos);
        Coord ennemyPos = ennemy.getPosition();

        int newRow = ennemyPos.getRow();
        int newCol = ennemyPos.getCol();


        if (heroPos.getRow() < ennemyPos.getRow()) newRow--;
        else if (heroPos.getRow() > ennemyPos.getRow()) newRow++;
        else if (heroPos.getCol() < ennemyPos.getCol()) newCol--;
        else if (heroPos.getCol() > ennemyPos.getCol()) newCol++;
        logger.info("for ennemy newRow: " + newRow + " newCol: " + newCol);
        if (!model.isOccupedCase(newRow, newCol)) {

            return new Coord(newRow, newCol);
        } else {
            logger.info("l'ennemy already occuped "+ennemyPos.toString());
            return ennemyPos;
        }
    }


    public void heroShot() {

    }
}