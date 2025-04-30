package src.java.org.projet.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import src.java.org.projet.controler.levelEditorController.MatrixLvlEditorController;
import src.java.org.projet.controler.levelEditorController.SelectItemSectionController;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelItems.Item;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.model.modelMap.SimpleDoor;
import src.java.org.projet.util.InitGame;
import src.java.org.projet.view.levelEditorView.HeroStateView;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;
import src.java.org.projet.view.levelEditorView.SelectItemView;

import java.util.ArrayList;
import java.util.List;

public class MainGameView extends Application {


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
                "character/hero/d.png",
                "brick.png",
                "ufoGreen.png",
                "playerShip3_blue.png",
                "powerupGreen_bolt.png"

        };

        Object[] objects = {
                new Agressor("",0),
                new Hero("",0),
                Item.class,
                SimpleDoor.class,
                Item.class,
                Item.class,
        };

        for (int i = 0; i < paths.length; i++) {
            CaseMatrix cm = getCaseMatrix(commonPath+paths[i], objects[i]);
            model.addItem(cm);
            addItem(selectItemView, cm);
        }


        selectController.addListenerToIems();
        root.setCenter(stackPane);
        root.setLeft(selectItemView);
        root.setRight(heroStateView);

        Scene scene = new Scene(root, 1200, 800);
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


    public ImageView createImg(String imgUrl){
        Image image = new Image(imgUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        return imageView;
    }

    public Background createImgBackground(String imgUrl, int width, int height){
        Image image = new Image(imgUrl);
        BackgroundImage backgroundImg = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(width,height,true,true,true,true)
        );
        return new Background(backgroundImg);
    }

    public static void main(String[] args) {
        launch(args);
    }


}