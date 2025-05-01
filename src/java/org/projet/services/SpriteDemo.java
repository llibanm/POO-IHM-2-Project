package src.java.org.projet.services;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SpriteDemo extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Pane root = new Pane();
        Scene scene = new Scene(root, 500, 350);

        //SpriteService sheet = new SpriteService("file:src/java/org/projet/assets/sprite.png", 32, 48, 4);
        String imgPath = "file:src/java/org/projet/assets/Hero.png";
        SpriteService sheet = new SpriteService(imgPath, 32, 48, 4,4);

        HashMap<String, ImageView> images = SpriteService.loadSprites("src/java/org/projet/assets/sheet.png", "src/java/org/projet/assets/sheet.xml");
        root.getChildren().addAll(images.get("laserGreen15.png"));
        //root.getChildren().add(SpriteService.getSpriteVariousSize("file:src/java/org/projet/assets/sheet.png", 293, 0, 143, 119));
        sheet.AnimedSprite(sheet, root);

        SpriteService.saveSprite(new Image(imgPath), 9, 4, 64, 64, "src/java/org/projet/assets/Agresso.png");
        ImageView single = sheet.getSprite(sheet,0,0, root);
        saveImageViewAsPNG(single, "src/java/org/projet/assets/defaultHero.png");


        sheet.removeBackGroundSprite(sheet, single, root);

        sheet.showAllSPrite(sheet, root);

        sheet.getAllSprite(sheet, root);

        stage.setScene(scene);
        stage.setTitle("Sprite Demo");
        stage.show();
    }

    public  void saveImageViewAsPNG(ImageView imageView, String outputPath) {
        // Récupérer le contenu de l'ImageView sous forme de snapshot (image capturée)
        WritableImage snapshot = imageView.snapshot(null, null);

        try {
            // Convertir le snapshot en RenderedImage via SwingFXUtils
            java.awt.image.RenderedImage renderedImage = SwingFXUtils.fromFXImage(snapshot, null);

            // Sauvegarder le RenderedImage en fichier PNG
            ImageIO.write(renderedImage, "png", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}