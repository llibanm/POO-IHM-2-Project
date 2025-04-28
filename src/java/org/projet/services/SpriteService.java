package src.java.org.projet.services;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class SpriteService {
    private Image spriteSheet;
    private int frameWidth, frameHeight;
    private int columns, rows;
    private final Logger logger = Logger.getLogger(SpriteService.class.getName());

    public SpriteService(String imagePath, int frameWidth, int frameHeight, int columns) {
        this.spriteSheet = new Image(imagePath);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.columns = columns;
        this.rows = (int)(spriteSheet.getHeight() / frameHeight);
    }

    public SpriteService(String imagePath, int frameWidth, int frameHeight, int columns, int rows) {
        this.spriteSheet = new Image(imagePath);
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.columns = columns;
        this.rows = rows;
    }

    public ImageView getSprite(int col, int row) {
        ImageView view = new ImageView(spriteSheet);
        view.setViewport(new Rectangle2D(col * frameWidth, row * frameHeight, frameWidth, frameHeight));
        return view;
    }

    // Méthode qui charge les sprites depuis le XML et retourne une HashMap
    public static  HashMap<String, ImageView> loadSprites(String imagePath, String xmlFilePath) throws Exception {
        HashMap<String, ImageView> spriteMap = new HashMap<>();

        // Charger le fichier XML
        File xmlFile = new File(xmlFilePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);

        // Charger l'image principale
        Image spriteSheet = new Image(new FileInputStream(imagePath));

        // Parcourir les sous-textures du fichier XML
        NodeList subTextures = doc.getElementsByTagName("SubTexture");
        for (int i = 0; i < subTextures.getLength(); i++) {
            Element subTexture = (Element) subTextures.item(i);

            // Récupérer les informations pour chaque sous-texture
            String name = subTexture.getAttribute("name");
            int x = Integer.parseInt(subTexture.getAttribute("x"));
            int y = Integer.parseInt(subTexture.getAttribute("y"));
            int width = Integer.parseInt(subTexture.getAttribute("width"));
            int height = Integer.parseInt(subTexture.getAttribute("height"));

            // Créer l'ImageView pour la sous-texture
            ImageView imageView = new ImageView(spriteSheet);
            imageView.setViewport(new Rectangle2D(x, y, width, height));

            // Ajouter l'ImageView à la HashMap avec le nom comme clé
            spriteMap.put(name, imageView);
        }

        // Retourner la HashMap
        return spriteMap;
    }


    public static ImageView getSpriteVariousSize(String urlSprite, int xcol, int yrow, int frameWidth, int frameHeight) {
        ImageView view = new ImageView(urlSprite);
        view.setViewport(new Rectangle2D(xcol* frameWidth , yrow* frameHeight, frameWidth, frameHeight));
        return view;
    }

    public List<ImageView> getSpriteCol(int col) {
        List<ImageView> sprites = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            sprites.add(getSprite(col, row));
        }
        return sprites;
    }

    public List<ImageView> getSpriteRow(int row) {
        List<ImageView> sprites = new ArrayList<>();
        for (int col = 0; col < columns; col++) {
            sprites.add(getSprite(col, row));
        }
        return sprites;
    }


    public static void saveSprite(Image spriteSheet, int col, int row, int frameWidth, int frameHeight, String outputPath) {
        ImageView view = new ImageView(spriteSheet);
        view.setViewport(new Rectangle2D(col * frameWidth, row * frameHeight, frameWidth, frameHeight));
        WritableImage snapshot = view.snapshot(null, null);

        try {
            RenderedImage renderedImage = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, null);
            ImageIO.write(renderedImage, "png", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ImageView> getRangeSprite(Coord getRowOrColRange) {
        int row = getRowOrColRange.getRow();
        int col = getRowOrColRange.getCol();
        if (row ==-1){
            return getSpriteCol(col);
        }
        else if (col ==-1){
            return getSpriteRow(row);
        }
        else {logger.warning("Error");
        return null;}
    }



    public List<ImageView> getAllSprites() {
        List<ImageView> sprites = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                sprites.add(getSprite(col, row));
            }
        }
        return sprites;
    }

    public ImageView createAnimatedSprite(double x, double y, double speed) {
        ImageView view = new ImageView(spriteSheet);
        view.setViewport(new Rectangle2D(0, 0, frameWidth, frameHeight));
        view.setX(x);
        view.setY(y);

        Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(speed), e -> {
                    Rectangle2D vp = view.getViewport();
                    double newX = (vp.getMinX() + frameWidth) % (columns * frameWidth);
                    view.setViewport(new Rectangle2D(newX, vp.getMinY(), frameWidth, frameHeight));
                })
        );
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        return view;
    }

    public Image removeBackground(ImageView sprite, Color targetColor) {
        Image original = sprite.getImage();
        int w = (int) original.getWidth();
        int h = (int) original.getHeight();
        WritableImage transparentImage = new WritableImage(w, h);
        PixelReader reader = original.getPixelReader();
        PixelWriter writer = transparentImage.getPixelWriter();

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Color color = reader.getColor(x, y);
                if (color.equals(targetColor)) {
                    writer.setColor(x, y, Color.TRANSPARENT);
                } else {
                    writer.setColor(x, y, color);
                }
            }
        }
        return transparentImage;
    }

    public   ImageView getSprite(SpriteService sheet,int col, int row, Pane root) {
        // Sprite à (1,0)
        ImageView single = sheet.getSprite(col, row);
        single.setX(200);
        single.setY(50);
        root.getChildren().add(single);
        return single;
    }

    public void AnimedSprite(SpriteService sheet, Pane root) {
        // Sprite animé
        ImageView animated = sheet.createAnimatedSprite(100, 50, 0.15);
        root.getChildren().add(animated);
    }

    public   void removeBackGroundSprite(SpriteService sheet, ImageView single, Pane root) {
        // Sprite sans fond
        Image transparentImg = sheet.removeBackground(single, Color.WHITE);
        ImageView transparentSprite = new ImageView(transparentImg);
        transparentSprite.setViewport(single.getViewport());
        transparentSprite.setX(300);
        transparentSprite.setY(50);
        root.getChildren().add(transparentSprite);
    }

    public   ImageView getRemoveBackGroundSprite(SpriteService sheet, ImageView single, Pane root) {
        // Sprite sans fond
        Image transparentImg = sheet.removeBackground(single, Color.WHITE);
        ImageView transparentSprite = new ImageView(transparentImg);
        transparentSprite.setViewport(single.getViewport());
        transparentSprite.setX(300);
        transparentSprite.setY(50);
        root.getChildren().add(transparentSprite);
        return transparentSprite;
    }

    public   void getAllSprite(SpriteService sheet, Pane root) {
        // Tous les sprites
        List<ImageView> allSprites = sheet.getAllSprites();
        double x = 100;
        for (ImageView v : allSprites) {
            v.setX(x);
            v.setY(250);
            root.getChildren().add(v);
            x += 35;
        }
    }

    public  void showAllSPrite(SpriteService sheet, Pane root) {
        // Afficher tous les sprites de la colonne 0
        List<ImageView> colSprites = sheet.getSpriteCol(0);
        double y = 120;
        for (ImageView v : colSprites) {
            v.setX(50); v.setY(y);
            root.getChildren().add(v);
            y += 50;
        }
    }
}