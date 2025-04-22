package src.java.org.projet.view.quickUnitTestView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.java.org.projet.view.levelEditorView.MatrixLvLEditorView;

public class MatrixLvlEditorViewTest extends Application {


    public  void start(Stage primaryStage){
        MatrixLvLEditorView mv = new MatrixLvLEditorView(50,50);
        Scene scene = new Scene(mv,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
