package src.java.org.projet.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import src.java.org.projet.controler.levelEditorController.MatrixLvlEditorController;
import src.java.org.projet.controler.levelEditorController.SelectItemSectionController;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.Boss;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelItems.Food;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelMap.Decor;
import src.java.org.projet.model.modelMap.Location;
import src.java.org.projet.model.modelMap.SimpleDoor;
import src.java.org.projet.util.InitGame;
import src.java.org.projet.view.levelEditorView.HeroStateView;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;
import src.java.org.projet.view.levelEditorView.SelectItemView;

import static src.java.org.projet.util.InitGame.getTopPaneView;
import static src.java.org.projet.view.util.PopupMsg.demanderInfosConfig;

/**
 * Vue principal
 */
public class MainGameView extends Application {

    private Dataset dataset = Dataset.getInstance();
    public void start(Stage primaryStage) {
        dataset.setConfigMap(demanderInfosConfig());

        /**Vue Principal*/
        BorderPane root = new BorderPane();

        SelectItemSectionModel model = new SelectItemSectionModel();
        SelectItemView selectItemView = new SelectItemView();
        SelectItemSectionController selectController = new SelectItemSectionController(model, selectItemView);

        MatrixLvLEditorView matrixLvLEditorView = new MatrixLvLEditorView(dataset.getMesure("DEFAULT_NB_ROW_COL")
                ,dataset.getMesure("DEFAULT_NB_ROW_COL"));
        GridPane grid = matrixLvLEditorView;
        int defaultNbRowCol = dataset.getMesure("DEFAULT_NB_ROW_COL") * 40;
        grid.setPrefSize(defaultNbRowCol, defaultNbRowCol);
        grid.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        grid.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        HeroStateView heroStateView = new HeroStateView();

        GameModel gameModel = new GameModel(InitGame.generateAllsLevels());

        MatrixLvlEditorController matrixLvlEditorController = new MatrixLvlEditorController(gameModel,matrixLvLEditorView,heroStateView,
                selectController);

        StackPane centerMapView = new StackPane();
       // Background imgBackground = createImgBackground("src/java/org/projet/assets/planet/planet01.png",100,100);
       // stackPane.setBackground(imgBackground);
        centerMapView.getChildren().add(matrixLvLEditorView);

        matrixLvlEditorController.addGridListenersOnView();
        String commonPath = dataset.getString("DEFAULT_ASSET_PATH");
        initSelectItemView(commonPath, model, selectItemView);
        selectController.addListenerToIems();
/*
        centerMapView.prefWidthProperty().bind(matrixLvLEditorView.widthProperty());
        centerMapView.prefHeightProperty().bind(matrixLvLEditorView.heightProperty());

 */

        /**
         * Barre supérieur des options
         */
        Pane myPane = getTopPaneView();

        matrixLvlEditorController.setPaneView(myPane);

        root.setCenter(centerMapView);
        root.setLeft(selectItemView);
        root.setTop(myPane);
        root.setRight(heroStateView);
        //root.setBottom(myPane);

        Scene scene = new Scene(root, dataset.getMesure("SCENE_V"), dataset.getMesure("SCENE_V1"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void initSelectItemView(String commonPath, SelectItemSectionModel model, SelectItemView selectItemView) {
        String[] paths = {
                "Agressor.png",
                "boss.png",
                "defaultHero.png",
                "brick.png",
                "ufoGreen.png",
                "playerShip3_blue.png",
                "playerShip2_red.png",
                "powerupGreen_bolt.png"

        };

        Object[] objects = {
                new Agressor("",0),
                new Boss("", 0),
                new Hero("",0),
                new Decor("brick"),
                new SimpleDoor(new Location("PorteVersMars","",0),
                        new Location("Mars","",2), ""),
                new SimpleDoor(new Location("PorteVersNeptune","",0),
                        new Location("Neptune","",1), ""),
                new SimpleDoor(new Location("PorteFinale","",0),
                        new Location("End","",3), ""),
                new Food("energy"),
        };

        for (int i = 0; i < paths.length; i++) {
            CaseMatrix cm = getCaseMatrix(commonPath +paths[i], objects[i]);
            model.addItem(cm);
            addItem(selectItemView, cm);
        }
    }


    private static void addItem(SelectItemView selectItemView, CaseMatrix agressorcaseMatrix) {
        selectItemView.addItem(agressorcaseMatrix.getUrlImgToShow(), agressorcaseMatrix.getClassOfItems());
    }

    private static CaseMatrix getCaseMatrix(String imgUrl, Object classOfItems) {
        CaseMatrix agressorcaseMatrix = new CaseMatrix(imgUrl,classOfItems, 0, 0, 1, 1);
        return agressorcaseMatrix;
    }



    public static void main(String[] args) {
        launch(args);
    }


}