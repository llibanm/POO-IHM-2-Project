package src.java.org.projet.controler.levelEditorController;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

public class MatrixLvlEditorController implements PropertyChangeListener {
    private  final Logger logger = Logger.getLogger(MatrixLvlEditorController.class.getName());


    private final MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    private CaseMatrix currentSelectedCaseMatrix;
    private GameLogic gameLogic;

    public MatrixLvlEditorController(MatrixLvlEditorModel model, MatrixLvLEditorView view) {
        this.model = model;
        this.view = view;
        addGridListenersOnView();
        gameLogic = new GameLogic(model,view);

    }


    public MatrixLvlEditorController(MatrixLvlEditorModel model, MatrixLvLEditorView view, SelectItemSectionController selectItemSectionController) {
        this.model = model;
        this.view = view;

        //selectItemSectionController.addPropertyChangeListener(this.getPropertyChangeListener());
        addGridListenersOnView();
        selectItemSectionController.addPropertyChangeListener(this);
        getFocusOnMatrixView();
        model.addPropertyChangeListener(this);
        this.view.setOnKeyPressed(this::handleKeyPressed);
        gameLogic = new GameLogic(model,view);


    }

    public void movelogic(int rowX, int colY) {
        int oldRowHero = model.getHero().getCoord().getRow();
        int oldColHero = model.getHero().getCoord().getCol();
        int newRow = model.getHero().getCoord().getRow() + rowX;
        int newCol = model.getHero().getCoord().getCol() + colY;

        if(gameLogic.moveHero(rowX,colY)) {
            //model.getHero().setCoord(new Coord(rowX + oldRowHero, colY + oldColHero));
            ImageView url = model.getHero().nextImage(rowX,colY);
            Rectangle addedRec = view.updateHeroPositionViewBis(oldRowHero, oldColHero, url, newRow, newCol);
            setNodeListener(addedRec);
            logger.info(url.toString());
        }
    }



    public void handleKeyPressed(KeyEvent keyEvent) {


        String code = keyEvent.getCode().toString();

        switch (code) {
            case "Z" -> {logger.info("Appui sur Z"); movelogic(-1, 0);}
            case "D" -> {logger.info("Appui sur D"); movelogic(0, 1);}
            case "S" -> {logger.info("Appui sur S"); movelogic(1, 0);}
            case "Q" -> {logger.info("Appui sur Q"); movelogic(0, -1);}
            case "F" -> {logger.info("Héro tire!!"); gameLogic.heroShot();}
        }
    }


    public  void getFocusOnMatrixView(){
        view.setFocusTraversable(true);
        view.requestFocus();
    }

    public void setNodeListener(Node node){
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
    private  void handleGridClick(Node node) {
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
        }
        else{
            logger.info("Erreur addGridListenersOnView currentSelectedCaseMatrix == null");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        logger.severe("Changement "+evt.getPropertyName()+" propertyChange");

        if ("selectedItem".equals(evt.getPropertyName())) {
            currentSelectedCaseMatrix = (CaseMatrix) evt.getNewValue();
            logger.info("Current selectedCaseMatrix is "+currentSelectedCaseMatrix);
        }
        else if ("move".equals(evt.getPropertyName())) {
            Coord old = (Coord) evt.getOldValue();
            Movable newv = (Movable) evt.getNewValue();
            logger.info("old coord is "+old);
            logger.info("newv coord is "+newv);

            Movable ennemy = (Movable) evt.getNewValue();
            int rowX = ennemy.getCoord().getRow() - old.getRow();
            int rowY = ennemy.getCoord().getCol() - old.getCol();
            ImageView url = ennemy.nextImage(rowX, rowY);
            view.updateHeroPositionViewBis(old.getRow(),old.getCol(),url,ennemy.getCoord().getRow(),ennemy.getCoord().getCol());
            setNodeListener(view.getNodeAt(old.getRow(),old.getCol()));
            logger.info("Current selectedCaseMatrix is "+currentSelectedCaseMatrix);
        }

        else if ("fireHero".equals(evt.getPropertyName())) {
            Coord old = (Coord) evt.getOldValue();
            Ennemy newv = (Ennemy) evt.getNewValue();
            logger.info("old coord is "+old);
            logger.info("newv coord is "+newv);

            Ennemy ennemy = (Ennemy) evt.getNewValue();
            int rowX = ennemy.getCoord().getRow() - old.getRow();
            int rowY = ennemy.getCoord().getCol() - old.getCol();
            ImageView url = ennemy.nextImage(rowX, rowY);
            view.updateHeroPositionViewBis(old.getRow(),old.getCol(),url,ennemy.getCoord().getRow(),ennemy.getCoord().getCol());
            setNodeListener(view.getNodeAt(old.getRow(),old.getCol()));
            logger.info("Current selectedCaseMatrix is "+currentSelectedCaseMatrix);
        }

        else {
            logger.info("Erreur propertyChange");
        }
    }
}