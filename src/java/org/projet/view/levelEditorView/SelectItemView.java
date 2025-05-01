package src.java.org.projet.view.levelEditorView;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**Vue de Selection des items
 * */
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import src.java.org.projet.controler.levelEditorController.MatrixLvlEditorController;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;

import java.util.function.Consumer;
import java.util.logging.Logger;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.modelMap.Exit;

public class SelectItemView extends ScrollPane {
    private  final MyLogger logger = new MyLogger(SelectItemView.class);
    VBox vbox;

    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public Consumer<CaseMatrix> getCallback() {
        return callback;
    }

    public void setCallback(Consumer<CaseMatrix> callback) {
        this.callback = callback;
    }

    //  SelectItemSectionController controller;
    private Consumer<CaseMatrix> callback;
/*
    public SelectItemSectionController getController() {
        return controller;
    }

    public void setController(SelectItemSectionController controller) {
        this.controller = controller;
    }
*/
    public SelectItemView() {
        super();
        vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        this.setContent(vbox);
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        this.setFitToWidth(true);

    }
    /**Ajouter un item dans le menu de selection des items */
    public void addItem(String urlImage, Object className) {
        VBox itemContainer = new VBox(5);
        itemContainer.setAlignment(Pos.CENTER);
        itemContainer.setPadding(new Insets(8));
        itemContainer.setStyle("-fx-background-color: #f4f4f4; -fx-border-color: #ccc; -fx-border-radius: 5; -fx-background-radius: 5;");

        ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        try {
            Image image = new Image(urlImage, true);
            imageView.setImage(image);
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur chargement image : " + urlImage);
            imageView.setImage(new Image("file:path/to/default_image.png", true));
        }

        Label descriptionLabel;
        if (className instanceof Exit) {
            descriptionLabel = new Label(((Exit) className).getEntrance().getName());
        } else {
            descriptionLabel = new Label(className.getClass().getSimpleName());
        }

        descriptionLabel.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        descriptionLabel.setWrapText(true);
        descriptionLabel.setTextAlignment(TextAlignment.CENTER);

        itemContainer.getChildren().addAll(imageView, descriptionLabel);
        vbox.getChildren().add(itemContainer);
    }

}