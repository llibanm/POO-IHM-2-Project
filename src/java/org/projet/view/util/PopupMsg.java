package src.java.org.projet.view.util;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import src.java.org.projet.model.modelLevelEditor.base.Score;

import java.util.List;

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
}
