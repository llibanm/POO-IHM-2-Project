package src.java.org.projet.controler.levelEditorController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveAction;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.MyCharacter;
import src.java.org.projet.model.modelItems.Bow;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.util.List;
import java.util.logging.Logger;

public class GameLogic {
    private final MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    private final MyLogger logger = new MyLogger(GameLogic.class) ;
    private Timeline enemyMovementLoop;
    private volatile boolean isProcessing = false;

    public GameLogic(MatrixLvlEditorModel model, MatrixLvLEditorView view) {
        this.model = model;
        this.view = view;

        startEnemyMovementLoop();
    }

    public void moveHero(int deltaRow, int deltaCol) {
        model.getMoveQueue().add(new MoveAction(model.getHero(), deltaRow, deltaCol));
        logger.info("Ajout  du déplacement du Hero dans la queue "+model.getHero());
        processMoveQueue();
    }

    public void startEnemyMovementLoop() {
       /* enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(1.0), e -> {
            moveEnemies();
            moveDynamicBows();
            processMoveQueue();
        }));*/
        Timeline enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> {
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

            if (e instanceof Bow) {
                logger.severe("Ajout des mvt de la flèche " + e +" Direction:"+e.getMoveDirection());
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
                if(enemy instanceof Ennemy) {
                    logger.severe("Ajout des mvt de l'ennemi " + enemy);
                    boolean enemyIsAttacking = ((Ennemy) enemy).attackHero(model.getHero());
                    logger.info("enemyIsAttacking: " + enemyIsAttacking);
                    if(!enemyIsAttacking) { //Déplacement
                        Coord newPos = aiComputeNextMove(enemy);

                        int rowX = newPos.getRow() - enemy.getCoord().getRow();
                        int colY = newPos.getCol() - enemy.getCoord().getCol();
                        enemy.setMoveDirection(rowX, colY);
                        model.getMoveQueue().add(new MoveAction(enemy, rowX, colY));
                    }
                    else {
                        logger.info("L'ennemi est en train d'attaquer le héros");
                       if(enemy instanceof Agressor) {
                           model.addDynamicBow((MyCharacter) enemy);
                       }
                    }
                }
            }
        } finally {
            isProcessing = false; // Réactive les ticks après traitement
        }
    }

    public void processMoveQueue() {
        var moveQueue = model.getMoveQueue();

        while (!moveQueue.isEmpty()) {
            MoveAction action = moveQueue.poll();
            logger.severe("Traitement des actions de la queue" + action.getEntity() + " " + action.getRowX() + " " + action.getColY());
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
                model.addDynamicBow(model.getHero());
                logger.info("Tir de la flèche");
            } else {
                logger.info("Pas de flèches restantes");
            }
        } else {
            logger.info("Pas d'arc");
        }
    }
}