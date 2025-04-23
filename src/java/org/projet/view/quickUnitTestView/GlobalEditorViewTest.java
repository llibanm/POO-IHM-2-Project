package src.java.org.projet.view.quickUnitTestView;

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
import src.java.org.projet.model.modelItems.Item;
import src.java.org.projet.model.modelLevelEditor.MatrixLvlEditorModel;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;
import src.java.org.projet.view.levelEditorView.SelectItemView;

public class GlobalEditorViewTest extends Application {


    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();




        SelectItemSectionModel model = new SelectItemSectionModel();
        SelectItemView selectItemView = new SelectItemView();
        SelectItemSectionController selectController = new SelectItemSectionController(model, selectItemView);

        MatrixLvLEditorView mv = new MatrixLvLEditorView(50,50);
        MatrixLvlEditorModel mdl = new MatrixLvlEditorModel(50,50,"");
        MatrixLvlEditorController cmv = new MatrixLvlEditorController(mdl,mv, selectController);
        StackPane stackPane = new StackPane();
        ImageView img = createImg("src/java/org/projet/spaceImagesProject/Space_Background.png");
        Background imgBackground = createImgBackground("src/java/org/projet/spaceImagesProject/Space_Background.png",100,100);
        stackPane.setBackground(imgBackground);
        stackPane.getChildren().add(mv);
        cmv.addGridListenersOnView();
        /*selectItemView.setController(selectController); */


        CaseMatrix agressorcaseMatrix = getCaseMatrix("src/java/org/projet/assets/character/ennemy/img.png", new Agressor("",0));
        CaseMatrix herocaseMatrix = getCaseMatrix("src/java/org/projet/assets/character/hero/d.png", new Hero("",0));
        CaseMatrix brickcaseMatrix = getCaseMatrix("src/java/org/projet/assets/brick.png", Item.class);
        model.addItem(agressorcaseMatrix);
        model.addItem(herocaseMatrix);
        model.addItem(brickcaseMatrix);
        addItem(selectItemView, agressorcaseMatrix);
        addItem(selectItemView, herocaseMatrix);
        addItem(selectItemView, brickcaseMatrix);

        selectController.addListenerToIems();
        root.setCenter(stackPane);
        root.setLeft(selectItemView);

        Scene scene = new Scene(root, 800, 800);
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