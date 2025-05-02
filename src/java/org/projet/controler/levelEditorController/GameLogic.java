package src.java.org.projet.controler.levelEditorController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import src.java.org.projet.interfaces.*;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.Boss;
import src.java.org.projet.model.modelCharacter.MyCharacter;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelItems.Bow;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe gérant la logique des déplacements des items non statiques (héros, balles...)
 * */
public class GameLogic {
    private MatrixLvlEditorModel model;
    //private final MatrixLvLEditorView view;
    private final MyLogger logger = new MyLogger(GameLogic.class);
    private Timeline enemyMovementLoop;
    private volatile boolean isProcessing = false;
    private final Map<Movable, Double> movementProgress = new HashMap<>();
    private GameModel gameModel;
    public Dataset dataset = Dataset.getInstance();

    public GameLogic(MatrixLvlEditorModel model) {
        this.model = model;
       // this.view = view;
    }

    /**
     * Initialisations des déplacements
     */
    public void init() {
        initializeMovementProgress();
        startMovableMovementLoop();
    }

    public void stopMovementLoop() {
        isProcessing = false;
        if(enemyMovementLoop != null) {

            enemyMovementLoop.stop();
        }

    }


    private void initializeMovementProgress() {
        model.getMovable().forEach(movable -> movementProgress.put(movable, 0.0));
    }

    /**
     * Déplacement du héro
     * @param deltaRow coordonnées à ajouter à l'actuelle pour la nouvelle row
     * @param deltaCol coordonnées à ajouter à l'actuelle pour la nouvelle row
     */
    public void moveHero(int deltaRow, int deltaCol) {
        model.getMoveQueue().add(new MoveAction(model.getHero(), deltaRow, deltaCol));
        logger.info("Ajout du déplacement du Hero dans la queue " + model.getHero());
        processMoveQueue();
    }

    /**Lancer le cycle de déplacement des items non statiques */
    public void startMovableMovementLoop() {
        enemyMovementLoop = new Timeline(new KeyFrame(Duration.seconds(dataset.getMesured("deltaTimeGL")), e -> {
            if (!isProcessing) {
                updateMovables(dataset.getMesured("deltaTimeGL"));
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

    /**
     * Ajout des actions de déplacement du laser
     * @param bow laser
     */
    private void moveBow(Bow bow) {
        logger.severe("Ajout du mvt de la flèche " + bow + " Direction:" + bow.getMoveDirection());
        model.getMoveQueue().add(new MoveAction(
                bow,
                bow.getMoveDirection().getRow(),
                bow.getMoveDirection().getCol()
        ));
    }


    /**
     * Gestion du mouvement des ennemis par accumulation des déplacement
     * dans une file.
     * @param enemy
     */
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
            if (enemy instanceof Agressor || enemy instanceof Boss) {
                model.addDynamicBow((MyCharacter) enemy);
                movementProgress.put(enemy, 0.0); // Reset le compteur après attaque
            }
        }
    }

    /**
     * Exécutions effective du mouvement de l'entité
     */
    public void processMoveQueue() {
        var moveQueue = model.getMoveQueue();
        while (!moveQueue.isEmpty()) {
            MoveAction action = moveQueue.poll();
            logger.severe("Traitement des actions de la queue" + action.getEntity() + " " + action.getRowX() + " " + action.getColY());
            model.moveItem(action.getEntity(), action.getRowX(), action.getColY());
        }
    }

    /**
     * Logique de déplacement spécifique des ennemis
     * @param ennemy
     * @return Coord la nouvelle position de(x,y) l'ennemi
     */
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

    /**
     * Gestion du tir du héro
     */
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


    /**
     * Gestion des intéractions avec avec les objets autour du héro
     */
    public void interactWithObjects() {
        logger.info("Analyse des objets à proximité");
        Coord heroPos = model.getHero().getCoord();
        List<CaseMatrix> adjacentCases = model.getNeighbors(heroPos.getRow(), heroPos.getCol());
        for (CaseMatrix caseMatrix : adjacentCases) {
            Object classItem = caseMatrix.getClassOfItems();
            if (classItem instanceof EffectOnHero ) {
                ((EffectOnHero)classItem).use(getGameModel());

            }
        }

    }


    public GameModel getGameModel() {
        return this.gameModel;
    }

    public void setGameModel(final GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setModel(MatrixLvlEditorModel model) {
        this.model = model;
    }
}