

import controler.SceneControler;
import model.AbstractModel;
import view.StartView;
import view.StartingBackground;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private StartView root;
    private SceneControler sceneControler;
    private AbstractModel model;

    @Override
    public void start(Stage primaryStage) throws Exception {

        StartingBackground s = new StartingBackground(1000,1000);
        root = new StartView(primaryStage,s);

        Scene scene = new Scene(root,1200,1000);
        primaryStage.setScene(scene);
        primaryStage.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

}


