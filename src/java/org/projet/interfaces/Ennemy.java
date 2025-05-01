package src.java.org.projet.interfaces;

import javafx.scene.image.ImageView;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

/**
 * Ennemy
 */
public interface Ennemy extends Movable {
    /**
     * Detecte la proximité avec le héro
     * @param c Coordonnées du héro
     * @return true si l'ennemi est proche du héro
     */
    public  boolean isNearOfHero(Coord c);

    /**
     * Portée d'attaque  de l'ennemi sur le héros
     * @return distance
     */
    public  int getPorteeAtk();

    /**
     * Attaque du héro
     * @param hero hero
     * @return
     */
    public boolean attackHero(Hero hero);


}