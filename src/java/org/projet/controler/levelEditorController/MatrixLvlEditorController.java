package src.java.org.projet.controler.levelEditorController;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.model.modelMap.Location;
import src.java.org.projet.view.levelEditorView.HeroStateView;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MatrixLvlEditorController implements PropertyChangeListener {
    private final MyLogger logger = new MyLogger(MatrixLvlEditorController.class);
    private  MatrixLvlEditorModel model;
    private final MatrixLvLEditorView view;
    HeroStateView heroStateView;
    private CaseMatrix currentSelectedCaseMatrix;
    private SelectItemSectionController selectItemSectionController;
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
        this.view.setBackground(model.getUrlBackground());
        //selectItemSectionController.addPropertyChangeListener(this.getPropertyChangeListener());


        this.selectItemSectionController = selectItemSectionController;
        selectItemSectionController.addPropertyChangeListener(this);
        getFocusOnMatrixView();
        subscriptionToModel();

        initView();
        gameLogic = new GameLogic(model, view);


    }

    private void initView() {
        addGridListenersOnView();
        this.view.setOnKeyPressed(this::handleKeyPressed);
    }

    private void subscriptionToModel() {
        model.addPropertyChangeListener(this);
    }
    private void unSubscriptionToModel() {
        //model.addPropertyChangeListener(this);
        model.removePropertyChangeListener(this);
    }

    private void updateLocation(Location location) {
        Hero hero = model.getHero();
        logger.info("Update location " + location);
        unSubscriptionToModel();
        gameModel.setCurrentLevel(location.getIndexOnWorldMap());
        this.model = gameModel.getCurrentLevel();
        gameLogic.setModel(model);
        gameLogic.init();
        subscriptionToModel();

        hero.setCoord(new Coord(17,10));
        this.model.setHero(hero);
        this.view.setBackground(model.getUrlBackground());
        this.view.initializeReset();
        initView();
        model.showItemModel();



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
        else if("location".equals(evt.getPropertyName())) { // TODO
            Location newLocation = (Location) evt.getNewValue();
            logger.info("Maj de la location " + newLocation);
            updateLocation(newLocation);

        }
        else if("showModelCase".equals(evt.getPropertyName())) { // TODO
            CaseMatrix newLocation = (CaseMatrix) evt.getNewValue();
            logger.info("Affichage des cases du model  " + newLocation);
            view.placeItemImg(newLocation.getUrlImgToShow(), newLocation.getCoordRow(), newLocation.getCoordCol());

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
            case "R" -> {
                logger.info("Héro intéragit avec les objets autours");
                gameLogic.interactWithObjects();
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

    public void setupMenuListeners(Pane myPane) {
        // Récupérer le MenuBar depuis le Pane
        MenuBar menubar = null;

        // Parcourir les enfants du Pane pour trouver le MenuBar
        for (Node node : myPane.getChildren()) {
            if (node instanceof MenuBar) {
                menubar = (MenuBar) node;
                break;
            }
        }

        // Si aucun MenuBar n'est trouvé, on ne fait rien
        if (menubar == null) {
            System.out.println("Aucun MenuBar trouvé dans le Pane");
            return;
        }


        for (Menu menu : menubar.getMenus()) {
            for (MenuItem item : menu.getItems()) {
                item.setOnAction(event -> {
                    String itemName = item.getText();

                    switch (itemName) {
                        case "Jouer":
                            System.out.println("Lancement du jeu par défaut");
                            break;

                        case "Editeur de niveau":
                            System.out.println("Ouverture de l'éditeur de niveau");
                            break;

                        case "Voir le classement":
                            System.out.println("Affichage du classement");
                            break;

                        case "config":
                            System.out.println("Ouverture des configurations");

                            break;

                        default:
                            System.out.println("Action non définie pour: " + itemName);
                            break;
                    }
                });
            }
        }
    }

    public void setPaneView(Pane myPane) {
        setupMenuListeners(myPane);
    }
}