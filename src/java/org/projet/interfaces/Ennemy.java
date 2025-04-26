package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public interface Ennemy extends Movable {



    public  boolean isNearOfHero(Coord c);
    public  int getPorteeAtk();
    public boolean attackHero(Hero hero);


}