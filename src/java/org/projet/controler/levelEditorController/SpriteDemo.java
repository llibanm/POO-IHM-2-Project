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
import java.util.HashMap;
import java.util.List;

public class SpriteDemo extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 350);

        //SpriteService sheet = new SpriteService("file:src/java/org/projet/assets/sprite.png", 32, 48, 4);
        SpriteService sheet = new SpriteService("file:src/java/org/projet/assets/sprite.png", 32, 48, 4,4);

        HashMap<String, ImageView> images = SpriteService.loadSprites("src/java/org/projet/assets/sheet.png", "src/java/org/projet/assets/sheet.xml");
        root.getChildren().addAll(images.get("laserGreen15.png"));
        //root.getChildren().add(SpriteService.getSpriteVariousSize("file:src/java/org/projet/assets/sheet.png", 293, 0, 143, 119));
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