package src.java.org.projet.view.util;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.java.org.projet.model.modelLevelEditor.base.Score;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PopupMsg {

    public PopupMsg() {
    }

    public static  void showMessage(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);

            TextArea textArea = new TextArea(message);
            textArea.setWrapText(true);
            textArea.setEditable(false);
            textArea.setPrefWidth(400);
            textArea.setPrefHeight(200);

            alert.getDialogPane().setContent(textArea);
            alert.showAndWait();
        });
    }

    public  static void showScores(List<Score> scores) {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Classement");

        VBox content = new VBox(10);
        content.setPadding(new Insets(10));

        for (Score s : scores) {
            Label label = new Label(s.getName() + " : " + s.getScore());
            content.getChildren().add(label);
        }

        dialog.getDialogPane().setContent(content);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dialog.showAndWait();
    }

    /**
     * Obtenir les infos de configuration
     * @return
     */
    public static Map<String, String> demanderInfosConfig() {
        Dialog<Map<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Configuration");

        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();

        TextField droite = new TextField("d");
        TextField gauche = new TextField("q");
        TextField haut = new TextField("z");
        TextField bas = new TextField("s");
        TextField largeur = new TextField("800");
        TextField hauteur = new TextField("600");
        TextField langue = new TextField("fr");
        TextField interact = new TextField("r");
        TextField shoot = new TextField("f");

        grid.add(new Label("Droite :"), 0, 0); grid.add(droite, 1, 0);
        grid.add(new Label("Gauche :"), 0, 1); grid.add(gauche, 1, 1);
        grid.add(new Label("Haut :"),   0, 2); grid.add(haut,   1, 2);
        grid.add(new Label("Bas :"),    0, 3); grid.add(bas,    1, 3);
        grid.add(new Label("Largeur :"),0, 4); grid.add(largeur,1, 4);
        grid.add(new Label("Hauteur :"),0, 5); grid.add(hauteur,1, 5);
        grid.add(new Label("Langue(fr|ang) :"), 0, 6); grid.add(langue, 1, 6);
        grid.add(new Label("Interagir :"),0, 7); grid.add(interact, 1, 7);
        grid.add(new Label("Tir :"),      0, 8); grid.add(shoot,    1, 8);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                Map<String, String> res = new HashMap<>();
                res.put("droite", droite.getText().toUpperCase());
                res.put("gauche", gauche.getText().toUpperCase());
                res.put("haut", haut.getText().toUpperCase());
                res.put("bas", bas.getText().toUpperCase());
                res.put("largeur", largeur.getText());
                res.put("hauteur", hauteur.getText());
                res.put("langue", langue.getText());
                res.put("interagir", interact.getText().toUpperCase());
                res.put("tir", shoot.getText().toUpperCase());
                return res;
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}
