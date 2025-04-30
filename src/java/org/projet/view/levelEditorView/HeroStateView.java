package src.java.org.projet.view.levelEditorView;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import src.java.org.projet.model.modelCharacter.Hero;


public class HeroStateView extends VBox {

    private Label name;
    private Label hpLabel;
    private Label ammoLabel;

    public HeroStateView() {
        super();
        this.name = new Label("Hero");
        this.hpLabel = new Label("HP: 100");
        this.ammoLabel = new Label("Ammo: 10");
       // this.setStyle("-fx-background-color: #000000;");
       // this.setPrefWidth(200);
        // this.setPrefHeight(200);
        this.getChildren().addAll(name,hpLabel,ammoLabel);
    }

    public void updateHeroState(Hero hero) {
        this.name.setText("Hero: " + hero.getName());
        this.hpLabel.setText("HP: " + hero.getHP());
        this.ammoLabel.setText("Ammo: " + hero.getH_bow().getNbArrows());

    }
}