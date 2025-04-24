package src.java.org.projet.controler.levelEditorController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.geometry.Rectangle2D;
import javafx.animation.*;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class SpriteDemo extends Application {


    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 350);

        //SpriteService sheet = new SpriteService("file:src/java/org/projet/assets/sprite.png", 32, 48, 4);
        SpriteService sheet = new SpriteService("file:src/java/org/projet/assets/sprite.png", 32, 48, 4,4);

        sheet.AnimedSprite(sheet, root);

        ImageView single = sheet.getSprite(sheet, root);

        sheet.removeBackGroundSprite(sheet, single, root);

        sheet.showAllSPrite(sheet, root);

        sheet.getAllSprite(sheet, root);

        stage.setScene(scene);
        stage.setTitle("Sprite Demo");
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
