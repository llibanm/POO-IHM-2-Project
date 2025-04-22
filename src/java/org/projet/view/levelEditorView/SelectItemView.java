package src.java.org.projet.view.levelEditorView;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**Vue de Selection des items
 * */
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import src.java.org.projet.controler.levelEditorController.SelectItemSectionController;
import src.java.org.projet.model.modelLevelEditor.base.CaseMatrix;

import java.util.function.Consumer;

public class SelectItemView extends ScrollPane {

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
        this.setFitToWidth(true);
    }
    /**Ajouter un item dans le menu de selection des items */
    public void addItem(String urlImage, Class<?> className) {
        HBox itemContainer = new HBox(10);
        itemContainer.setAlignment(Pos.CENTER_LEFT);

        Label descriptionLabel = new Label(className.getSimpleName());
        ImageView imageView = new ImageView();
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        imageView.setPreserveRatio(true);

        try {
            Image image = new Image(urlImage, true);
            imageView.setImage(image);
        } catch (IllegalArgumentException e) {
            System.err.println("Erreur lors du chargement de l'image : " + urlImage + " - " + e.getMessage());
            imageView.setImage(new Image("file:path/to/default_image.png", true));
        }

        itemContainer.getChildren().addAll(descriptionLabel, imageView);
       // itemContainer.setOnMouseClicked(e->controller.onItemClicked(caseMatrix));
        vbox.getChildren().add(itemContainer);
    }
}