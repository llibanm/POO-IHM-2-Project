package src.java.org.projet.view.quickUnitTestView;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.java.org.projet.controler.levelEditorController.SelectItemSectionController;
import src.java.org.projet.model.modelCharacter.Agressor;
import src.java.org.projet.model.modelLevelEditor.SelectItemSectionModel;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;
import src.java.org.projet.view.levelEditorView.SelectItemView;

public class SelectItemViewTest extends Application {


    public  void start(Stage primaryStage){
        SelectItemSectionModel model = new SelectItemSectionModel();
        SelectItemView selectItemView = new SelectItemView();
        SelectItemSectionController controller = new SelectItemSectionController(model, selectItemView);
        selectItemView.setController(controller);

        for (int i = 0; i < 50; i++) {
            CaseMatrix caseMatrix = new CaseMatrix("src/java/org/projet/assets/character/ennemy/img.png", Agressor.class,0,0,1,1);
            selectItemView.addItem(caseMatrix );
        }
        Scene scene = new Scene(selectItemView,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
