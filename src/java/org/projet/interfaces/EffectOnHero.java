package src.java.org.projet.interfaces;

import src.java.org.projet.model.modelGame.GameModel;

/**
 * Effet concret d'une Entité lorsqu'elle intéragit avec le héro
 */
public interface EffectOnHero {
    void use(GameModel model);
}