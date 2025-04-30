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
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.model.modelMap.SimpleDoor;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLogic {
    private  MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    private final MyLogger logger = new MyLogger(GameLogic.class);
    private Timeline enemyMovementLoop;
    private volatile boolean isProcessing = false;
    private final Map<Movable, Double> movementProgress = new HashMap<>();

    public GameLogic(MatrixLvlEditorModel model, MatrixLvLEditorView view) {
        this.model = model;
        this.view = view;
        init();
    }

    public void init() {
        initializeMovementProgress();
        startEnemyMovementLoop();
    }


    private void initializeMovementProgress() {
        model.getMovable().forEach(enemy -> movementProgress.put(enemy, 0.0));
    }

    public void moveHero(int deltaRow, int deltaCol) {
        model.getMoveQueue().add(new MoveAction(model.getHero(), deltaRow, deltaCol));
        logger.info("Ajout du déplacement du Hero dans la queue " + model.getHero());
        processMoveQueue();
    }

    public void startEnemyMovementLoop() {
        enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(0.05), e -> {
            if (!isProcessing) {
                updateMovables(0.05);
            }
        }));
        enemyMovementLoop.setCycleCount(Animation.INDEFINITE);
        enemyMovementLoop.play();
    }

    private void updateMovables(double deltaTime) {
        isProcessing = true;
        try {
            for (Movable entity : model.getMovable()) {
                if (!movementProgress.containsKey(entity)) {
                    movementProgress.put(entity, 0.0);
                }

                double progress = movementProgress.get(entity) + deltaTime * entity.getSpeed();

                if (progress >= 1.0) {
                    if (entity instanceof Bow) {
                        moveBow((Bow) entity);
                    } else if (entity instanceof Ennemy) {
                        moveEnemy((Ennemy) entity);
                    }
                    progress = 0.0;
                }

                movementProgress.put(entity, progress);
            }
            processMoveQueue();
        } finally {
            isProcessing = false;
        }
    }

    private void moveBow(Bow bow) {
        logger.severe("Ajout du mvt de la flèche " + bow + " Direction:" + bow.getMoveDirection());
        model.getMoveQueue().add(new MoveAction(
                bow,
                bow.getMoveDirection().getRow(),
                bow.getMoveDirection().getCol()
        ));
    }



    private void moveEnemy(Ennemy enemy) {
        logger.severe("Ajout du mvt de l'ennemi " + enemy);
        boolean enemyIsAttacking = enemy.attackHero(model.getHero());
        logger.info("enemyIsAttacking: " + enemyIsAttacking);

        if (!enemyIsAttacking) {
            Coord newPos = aiComputeNextMove(enemy);
            int rowX = newPos.getRow() - enemy.getCoord().getRow();
            int colY = newPos.getCol() - enemy.getCoord().getCol();
            enemy.setMoveDirection(rowX, colY);
            model.getMoveQueue().add(new MoveAction(enemy, rowX, colY));
        } else {
            logger.info("L'ennemi est en train d'attaquer le héros");
            if (enemy instanceof Agressor) {
                model.addDynamicBow((MyCharacter) enemy);
                movementProgress.put(enemy, 0.0); // Reset le compteur après attaque
            }
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
            return ((Bow) ennemy).getMoveDirection();
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

    public void addNewEnemy(Movable enemy) {
        movementProgress.put(enemy, 0.0);
    }

    public void removeEnemy(Movable enemy) {
        movementProgress.remove(enemy);
    }





    public void interactWithObjects() {
        logger.info("Analyse des objets à proximité");
        Coord heroPos = model.getHero().getCoord();
        List< CaseMatrix> adjacentCases = model.getNeighbors(heroPos.getRow(), heroPos.getCol());
        for (CaseMatrix caseMatrix : adjacentCases) {

            if (caseMatrix.getClassOfItems() != null) {
                logger.info("Objet trouvé: " + caseMatrix.getClassOfItems());
                if (caseMatrix.getClassOfItems() instanceof SimpleDoor) {
                    SimpleDoor simpleDoor = (SimpleDoor) caseMatrix.getClassOfItems();
                    model.changeLocation(simpleDoor.cross());
                    logger.info("traversé de la porte vers index "+ simpleDoor.cross().getIndexOnWorldMap());
                }
            }
        }

    }

    public void setModel(MatrixLvlEditorModel model) {
        this.model = model;
    }
}