package src.java.org.projet.controler.levelEditorController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveAction;
import src.java.org.projet.model.modelItems.Bow;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.util.List;
import java.util.logging.Logger;

public class GameLogic {
    private final MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    private final Logger logger = Logger.getLogger(GameLogic.class.getName());
    private Timeline enemyMovementLoop;
    private volatile boolean isProcessing = false;

    public GameLogic(MatrixLvlEditorModel model, MatrixLvLEditorView view) {
        this.model = model;
        this.view = view;

        startEnemyMovementLoop();
    }

    public void moveHero(int deltaRow, int deltaCol) {
        model.getMoveQueue().add(new MoveAction(model.getHero(), deltaRow, deltaCol));
        logger.info("ajout de l'action du Hero dans la queue");
        processMoveQueue();
    }

    public void startEnemyMovementLoop() {
       /* enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(1.0), e -> {
            moveEnemies();
            moveDynamicBows();
            processMoveQueue();
        }));*/
        Timeline enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(1.0), e -> {
            if (!isProcessing) {
                moveEnemies();
                moveDynamicBows();
                processMoveQueue();
            }
        }));
        enemyMovementLoop.setCycleCount(Animation.INDEFINITE);
        enemyMovementLoop.play();
    }

    public void moveDynamicBows() {
        List<Movable> ennemies = model.getEnnemies();
        for (Movable e : ennemies) {
            logger.severe("Dynamic bow " + e.toString()+" "+ennemies.size());
            if (e instanceof Bow) {
                model.getMoveQueue().add(new MoveAction(e, e.getMoveDirection().getRow(), e.getMoveDirection().getCol()));  // monte
            }
        }
    }
    public void moveEnemies() {
        if (isProcessing) return; // Évite les chevauchements
        isProcessing = true;

        try {
            List<Movable> enemies = model.getEnnemies();
            for (Movable enemy : enemies) {
                Coord newPos = aiComputeNextMove(enemy);
                model.getMoveQueue().add(new MoveAction(enemy, newPos.getRow() - enemy.getCoord().getRow(), newPos.getCol() - enemy.getCoord().getCol()));
            }
        } finally {
            isProcessing = false; // Réactive les ticks après traitement
        }
    }
    public void moveEnemiesE() {
        List<Movable> ennemies = model.getEnnemies();

        for (Movable ennemy : ennemies) {

            logger.info(ennemy.toString());
            Coord oldPos = ennemy.getCoord();
            logger.info("oldPos: " + oldPos);

            Coord newPos = aiComputeNextMove(
                    ennemy);
            logger.info("newPos: " + newPos);


            model.getMoveQueue().add(new MoveAction(
                    ennemy,
                    newPos.getRow() - oldPos.getRow(),
                    newPos.getCol() - oldPos.getCol()
            ));
        }
    }

    public void processMoveQueue() {
        var moveQueue = model.getMoveQueue();

        while (!moveQueue.isEmpty()) {
            MoveAction action = moveQueue.poll();
            model.moveItem(action.getEntity(), action.getRowX(), action.getColY());
        }
    }

    public Coord aiComputeNextMove(Movable ennemy) {

        if (ennemy instanceof Bow) {
            return ((Bow) ennemy).getMoveDirection(); // Direction déjà figée
        }
        Coord heroPos = model.getHero().getCoord();
        logger.info("heroPos: " + heroPos + "ennemy " + ennemy);
        Coord ennemyPos = ennemy.getCoord();

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
            logger.info("l'ennemy already occuped " + ennemyPos);
            return ennemyPos;
        }
    }

    public void heroShot() {
        if (model.getHero().getH_bow() != null) {
            if (model.getHero().getH_bow().getNbArrows() > 0) {
                model.getHero().getH_bow().remove_arrows();
                model.addDynamicBow();
                logger.info("Tir de la flèche");
            } else {
                logger.info("Pas de flèches restantes");
            }
        } else {
            logger.info("Pas d'arc");
        }
    }
}