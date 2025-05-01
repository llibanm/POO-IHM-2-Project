package src.java.org.projet.view.levelEditorView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import src.java.org.projet.model.modelCharacter.Hero;

public class HeroStateView extends VBox {

    private Label name;
    private Label hpLabel;
    private Label ammoLabel;

    public HeroStateView() {
        super(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));

        // Fond dégradé clair
        Background background = new Background(new BackgroundFill(
                Color.web("#fdfdfd"), new CornerRadii(5), Insets.EMPTY));
        this.setBackground(background);

        // Bordure grise
        this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,
                BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));

        this.name = new Label("Hero");
        this.name.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        this.hpLabel = new Label("HP: 100");
        this.hpLabel.setFont(Font.font(14));

        this.ammoLabel = new Label("Laser: 10");
        this.ammoLabel.setFont(Font.font(14));

        this.getChildren().addAll(name, hpLabel, ammoLabel);
    }

    public void updateHeroState(Hero hero) {
        this.name.setText("Hero: " + hero.getName());

        int hp = hero.getHP();
        int ammo = hero.getH_bow().getNbArrows();

        this.hpLabel.setText("HP: " + hp);
        this.ammoLabel.setText("Laser: " + ammo);

        this.hpLabel.setTextFill(hp < 5 ? Color.RED : Color.GREEN);
        this.ammoLabel.setTextFill(ammo < 5 ? Color.RED : Color.GREEN);
    }
}