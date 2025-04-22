package src.java.org.projet;


import com.fasterxml.jackson.core.JsonProcessingException;
import src.java.org.projet.controler.SceneControler;
import src.java.org.projet.model.AbstractModel;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.java.org.projet.unitTest.ObjetToJson;
import src.java.org.projet.view.StartView;
import src.java.org.projet.view.StartingBackground;
import src.java.org.projet.view.levelEditorView.LvlEditorView;

import static java.lang.System.exit;

public class Main extends Application {

    private StartView root;
    private SceneControler sceneControler;
    private AbstractModel model;

    public  void launchDefautView(Stage primaryStage){
        StartingBackground s = new StartingBackground(1000,1000);
        root = new StartView(primaryStage,s);

        Scene scene = new Scene(root,1200,1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public  void LvlEditorView(Stage primaryStage){
        var s = new LvlEditorView(800,800,50,50);
        Scene scene = new Scene(s,800,800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    //Pour tester l'éditeur de niveau
    public void launchLevelEditorView(Stage primaryStage) {

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        boolean test = false;
        if(test){
            modeTest();
            exit(0);
        }
        LvlEditorView(primaryStage);
        //launchDefautView(primaryStage);

    }



    public static void main(String[] args) {
        launch(args);
    }

    public void modeTest() throws JsonProcessingException {
        //Teste du chargement json
       ObjetToJson.objetToJsonTest();

    }


}


