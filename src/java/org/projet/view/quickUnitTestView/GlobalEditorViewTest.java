package src.java.org.projet.view.quickUnitTestView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import src.java.org.projet.controler.levelEditorController.SelectItemSectionController;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;
import src.java.org.projet.view.levelEditorView.SelectItemView;

public class GlobalEditorViewTest extends Application {


    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        MatrixLvLEditorView mv = new MatrixLvLEditorView(50,50);



        SelectItemSectionModel model = new SelectItemSectionModel();

        SelectItemView selectItemView = new SelectItemView();

        SelectItemSectionController controller = new SelectItemSectionController(model, selectItemView);
        /*selectItemView.setController(controller); */

        for (int i = 0; i < 10; i++) {
            CaseMatrix caseMatrix = new CaseMatrix("src/java/org/projet/assets/character/ennemy/img.png", Agressor.class, 0, 0, 1, 1);
            model.addItem(caseMatrix);
            selectItemView.addItem(caseMatrix.getUrlImgToShow(), caseMatrix.getClassOfItems());
        }
        controller.addListenerToIems();
        root.setCenter(mv);
        root.setLeft(selectItemView);

        Scene scene = new Scene(root, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}