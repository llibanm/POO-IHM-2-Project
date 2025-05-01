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

/**
 * Vue principal englobant
 */
public class MainGameView extends Application {

    private Dataset dataset = Dataset.getInstance();
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        SelectItemSectionModel model = new SelectItemSectionModel();
        SelectItemView selectItemView = new SelectItemView();
        SelectItemSectionController selectController = new SelectItemSectionController(model, selectItemView);

        MatrixLvLEditorView matrixLvLEditorView = new MatrixLvLEditorView(20,20);
        HeroStateView heroStateView = new HeroStateView();

        GameModel gameModel = new GameModel(InitGame.generateAllsLevels());
        MatrixLvlEditorController matrixLvlEditorController = new MatrixLvlEditorController(gameModel,matrixLvLEditorView,heroStateView,
                selectController);

        StackPane stackPane = new StackPane();
       // Background imgBackground = createImgBackground("src/java/org/projet/assets/planet/planet01.png",100,100);
       // stackPane.setBackground(imgBackground);
        stackPane.getChildren().add(matrixLvLEditorView);
        matrixLvlEditorController.addGridListenersOnView();
        String commonPath = "src/java/org/projet/assets/";
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
            CaseMatrix cm = getCaseMatrix(commonPath+paths[i], objects[i]);
            model.addItem(cm);
            addItem(selectItemView, cm);
        }


        selectController.addListenerToIems();
        root.setCenter(stackPane);
        root.setLeft(selectItemView);

        //Section supérieur
        Pane myPane = new Pane();
        MenuBar menubar = new MenuBar();
        final Menu filemenu = new Menu("Jeu par défaut");
        //Item filemenu
        final MenuItem itemNew = new MenuItem(dataset.getString("JOUERMU"));
        filemenu.getItems().addAll(itemNew);

        //item affichage
        final Menu affichageMenu = new Menu("Créer Jeu");
        final MenuItem itemFscreen = new MenuItem(dataset.getString("TESTLVL"));
        final MenuItem exportMI = new MenuItem(dataset.getString("EXPORT"));
        affichageMenu.getItems().addAll(itemFscreen, exportMI);

        final Menu importExportMenu = new Menu("Importer");
        final MenuItem importMI = new MenuItem(dataset.getString("IMPORTER"));

        importExportMenu.getItems().addAll(importMI);

        final Menu hallOfFameMenu= new Menu("Rang");
        final MenuItem rankMI = new MenuItem(dataset.getString("LOOKRANK"));
        hallOfFameMenu.getItems().addAll(rankMI);


        final Menu configurationMenu= new Menu("Configuration");
        final MenuItem configMI = new MenuItem(dataset.getString("CONFIG"));
        configurationMenu.getItems().addAll(configMI);

        final Menu authorMenu= new Menu("Auteurs");
        final MenuItem authorMI = new MenuItem(dataset.getString("AUTHOR"));
        authorMenu.getItems().addAll(authorMI);

        menubar.getMenus().addAll(filemenu,affichageMenu, importExportMenu, hallOfFameMenu, configurationMenu,authorMenu);
        myPane.getChildren().addAll(menubar);


        matrixLvlEditorController.setPaneView(myPane);
        root.setTop(myPane);

        root.setRight(heroStateView);
        //root.setBottom(myPane);

        Scene scene = new Scene(root, dataset.getMesure("SCENE_V"), dataset.getMesure("SCENE_V1"));
        primaryStage.setScene(scene);
        primaryStage.show();
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