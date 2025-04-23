package src.java.org.projet.controler.levelEditorController;

import javafx.scene.Node;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import src.java.org.projet.controler.AbstractControler;
import src.java.org.projet.interfaces.MyConcreteObservable;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MatrixLvlEditorController implements PropertyChangeListener {

    private final MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    private CaseMatrix currentSelectedCaseMatrix;
    private GameLogic gameLogic;

    public MatrixLvlEditorController(MatrixLvlEditorModel model, MatrixLvLEditorView view) {
        this.model = model;
        this.view = view;
        addGridListenersOnView();
        gameLogic = new GameLogic(model);

    }


    public MatrixLvlEditorController(MatrixLvlEditorModel model, MatrixLvLEditorView view, SelectItemSectionController selectItemSectionController) {
        this.model = model;
        this.view = view;
        gameLogic = new GameLogic(model);
        //selectItemSectionController.addPropertyChangeListener(this.getPropertyChangeListener());
        addGridListenersOnView();
        selectItemSectionController.addPropertyChangeListener(this);


        getFocusOnMatrixView();
        this.view.setOnKeyPressed(this::handleKeyPressed);



    }

    public void movelogic(int rowX, int colY) {
        int oldRowHero = model.getHero().getCoord().getRow();
        int oldColHero = model.getHero().getCoord().getCol();
        int newRow = model.getHero().getCoord().getRow() + rowX;
        int newCol = model.getHero().getCoord().getCol() + colY;

        if(gameLogic.moveHero(rowX,colY)) {
            model.getHero().setCoord(new Coord(rowX + oldRowHero, colY + oldColHero));
            String url = model.getHero().coordToImage(rowX, colY);
            Rectangle addedRec = view.updateHeroPositionView(oldRowHero, oldColHero, url, newRow, newCol);
            setNodeListener(addedRec);
            System.out.println(url);
        }
    }



    public void handleKeyPressed(KeyEvent keyEvent) {


        String code = keyEvent.getCode().toString();

        switch (code) {
            case "Z" -> {System.out.println("Appui sur Z"); movelogic(-1, 0);}
            case "D" -> {System.out.println("Appui sur D"); movelogic(0, 1);}
            case "S" -> {System.out.println("Appui sur S"); movelogic(1, 0);}
            case "Q" -> {System.out.println("Appui sur Q"); movelogic(0, -1);}
            case "T" -> System.out.println("Héro tire!!");
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
        System.out.println("clicked on grid");
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
            System.out.println("Erreur addGridListenersOnView currentSelectedCaseMatrix == null");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Changement "+evt.getPropertyName()+" propertyChange");
        if ("selectedItem".equals(evt.getPropertyName())) {
            currentSelectedCaseMatrix = (CaseMatrix) evt.getNewValue();
            System.out.println("Current selectedCaseMatrix is "+currentSelectedCaseMatrix);
        }
        else {
            System.out.println("Erreur propertyChange");
        }
    }
}
