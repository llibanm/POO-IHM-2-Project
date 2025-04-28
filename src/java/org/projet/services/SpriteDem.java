package src.java.org.projet.services;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class SpriteDem extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier XML
        File xmlFile = new File("src/java/org/projet/assets/sheet.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        // Récupérer l'image principale
        Node textureAtlasNode = doc.getElementsByTagName("TextureAtlas").item(0);
        String imagePath = "src/java/org/projet/assets/sheet.png";
        Image spriteSheet = new Image(new FileInputStream(imagePath));

        // Créer un StackPane pour afficher les sous-textures
        StackPane root = new StackPane();

        // Parcourir les sous-textures
        NodeList subTextures = doc.getElementsByTagName("SubTexture");
        for (int i = 0; i < subTextures.getLength(); i++) {
            Element subTexture = (Element) subTextures.item(i);

            // Récupérer les informations de chaque sous-texture
            String name = subTexture.getAttribute("name");
            int x = Integer.parseInt(subTexture.getAttribute("x"));
            int y = Integer.parseInt(subTexture.getAttribute("y"));
            int width = Integer.parseInt(subTexture.getAttribute("width"));
            int height = Integer.parseInt(subTexture.getAttribute("height"));

            // Créer un ImageView pour chaque sous-texture
            ImageView view = new ImageView(spriteSheet);
            // Définir la fenêtre de visualisation sur la sous-texture avec un Rectangle2D
            view.setViewport(new Rectangle2D(x, y, width, height));
            // Ajouter l'ImageView au StackPane
            root.getChildren().add(view);
            break;
        }

        // Créer la scène et l'afficher
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Sprite Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}