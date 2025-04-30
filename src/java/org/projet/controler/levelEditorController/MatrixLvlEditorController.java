package src.java.org.projet.controler.levelEditorController;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.view.levelEditorView.HeroStateView;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

public class MatrixLvlEditorController implements PropertyChangeListener {
    private final MyLogger logger = new MyLogger(MatrixLvlEditorController.class);
    private final MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    HeroStateView heroStateView;
    private CaseMatrix currentSelectedCaseMatrix;
    private GameLogic gameLogic;
    GameModel gameModel;

    public MatrixLvlEditorController(MatrixLvlEditorModel model, MatrixLvLEditorView view, HeroStateView heroStateView) {
        this.model = model;
        this.view = view;
        addGridListenersOnView();
        gameLogic = new GameLogic(model, view);
        this.heroStateView = heroStateView;

    }

    public MatrixLvlEditorController(GameModel game, MatrixLvLEditorView view, HeroStateView heroStateView, SelectItemSectionController selectItemSectionController) {
       // this.model = model;
        this.heroStateView = heroStateView;
        this.view = view;
        this.gameModel = game;
        this.model = gameModel.getCurrentLevel();

        //selectItemSectionController.addPropertyChangeListener(this.getPropertyChangeListener());
        this.view.setBackground(model.getUrlBackground());
        addGridListenersOnView();
        selectItemSectionController.addPropertyChangeListener(this);
        getFocusOnMatrixView();
        model.addPropertyChangeListener(this);
        this.view.setOnKeyPressed(this::handleKeyPressed);
        gameLogic = new GameLogic(model, view);


    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        logger.severe("Changement " + evt.getPropertyName() + " propertyChange");

        if ("selectedItem".equals(evt.getPropertyName())) {
            currentSelectedCaseMatrix = (CaseMatrix) evt.getNewValue();
            logger.info("Current selectedCaseMatrix is " + currentSelectedCaseMatrix);
        } else if ("move".equals(evt.getPropertyName())) {
            logger.severe("Movement " + evt.getPropertyName() + " propertyChange");
            Coord old = (Coord) evt.getOldValue();
            Movable ennemy = (Movable) evt.getNewValue();
            logger.info(ennemy+" old coord is " + old);
            logger.info("Entity coord is " + ennemy);

            int rowX = ennemy.getCoord().getRow() - old.getRow();
            int rowY = ennemy.getCoord().getCol() - old.getCol();
            ImageView url = ennemy.nextImage(rowX, rowY);
            view.updateHeroPositionViewBis(old.getRow(), old.getCol(), url, ennemy.getCoord().getRow(), ennemy.getCoord().getCol());
            setNodeListener(view.getNodeAt(old.getRow(), old.getCol()));
            logger.info("Current selectedCaseMatrix is " + currentSelectedCaseMatrix);
        } else if ("fireHero".equals(evt.getPropertyName())) {
            logger.severe("Reception du signal de tir");
            CaseMatrix bullet = (CaseMatrix) evt.getNewValue();
            view.placeItemImg(bullet.getUrlImgToShow(), bullet.getCoordRow(), bullet.getCoordCol());
            logger.info("CaseMatrix  Flèche " + bullet);

            logger.info("Current selectedCaseMatrix is " + currentSelectedCaseMatrix);
        }
        else if ("removeItem".equals(evt.getPropertyName())) {
            logger.severe("Reception du signal remove item");
            Coord old = (Coord) evt.getOldValue();
            view.resetCell(old.getRow(), old.getCol());
                }
        else if("UpdateHeroState".equals(evt.getPropertyName())) { // TODO
            Hero hero = (Hero) evt.getNewValue();
            logger.info("Maj des stats du héros " + hero);
            heroStateView.updateHeroState(hero);

        }
        else {
            logger.info("Erreur propertyChange");
        }
    }

    public void movelogic(int rowX, int colY) {
        int oldRowHero = model.getHero().getCoord().getRow();
        int oldColHero = model.getHero().getCoord().getCol();
        int newRow = model.getHero().getCoord().getRow() + rowX;
        int newCol = model.getHero().getCoord().getCol() + colY;

        gameLogic.moveHero(rowX, colY);
        //model.getHero().setCoord(new Coord(rowX + oldRowHero, colY + oldColHero));
        /*
        ImageView url = model.getHero().nextImage(rowX,colY);
        Rectangle addedRec = view.updateHeroPositionViewBis(oldRowHero, oldColHero, url, newRow, newCol);
        setNodeListener(addedRec);
        logger.info(url.toString());7

         */

    }

    public void handleKeyPressed(KeyEvent keyEvent) {


        String code = keyEvent.getCode().toString();

        switch (code) {
            case "Z" -> {
                logger.info("Appui sur Z");
                movelogic(-1, 0);
            }
            case "D" -> {
                logger.info("Appui sur D");
                movelogic(0, 1);
            }
            case "S" -> {
                logger.info("Appui sur S");
                movelogic(1, 0);
            }
            case "Q" -> {
                logger.info("Appui sur Q");
                movelogic(0, -1);
            }
            case "F" -> {
                logger.info("Héro tire!!");
                gameLogic.heroShot();
            }
        }
    }

    public void getFocusOnMatrixView() {
        view.setFocusTraversable(true);
        view.requestFocus();
    }

    public void setNodeListener(Node node) {
        node.setOnMouseClicked(e -> {
            handleGridClick(node);
        });
    }

    public void addGridListenersOnView() {
        view.getChildren().forEach(node -> {
            node.setOnMouseClicked(e -> {
                handleGridClick(node);
            });
        });
    }

    private void handleGridClick(Node node) {
        logger.info("clicked on grid");
        getFocusOnMatrixView();
        if (currentSelectedCaseMatrix != null) {
            Integer col = GridPane.getColumnIndex(node);
            Integer row = GridPane.getRowIndex(node);

            if (col != null && row != null) {
                model.addItemMatrice(row, col, currentSelectedCaseMatrix);
                view.removeItemFromGridPane(row, col);
                view.placeItemImg(currentSelectedCaseMatrix.getUrlImgToShow(), row, col);
            }
        } else {
            logger.info("Erreur addGridListenersOnView currentSelectedCaseMatrix == null");
        }
    }

}