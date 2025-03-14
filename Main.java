import Controler.SceneControler;
import Model.abstractModel;
import View.StartView;
import View.startingBackground;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private StartView root;
    private SceneControler sceneControler;
    private abstractModel model;

    @Override
    public void start(Stage primaryStage) throws Exception {

        startingBackground s = new startingBackground(1000,1000);
        root = new StartView(primaryStage,s);

        Scene scene = new Scene(root,1200,1000);
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}


