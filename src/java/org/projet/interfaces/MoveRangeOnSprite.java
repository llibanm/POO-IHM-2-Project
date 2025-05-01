package src.java.org.projet.interfaces;

import src.java.org.projet.model.modelLevelEditor.base.Coord;

/**
 * Classe representant les coordonnées des
 * images des séquences de mouvements pour chaque direction (haut, bas, gauche,
 * droite) de l'entité sur la map.
 *
 */
public class MoveRangeOnSprite {

    Coord spriteMoveUpRange, spriteMoveRightRange,spriteMoveLeftRange,spriteMoveDownRange;

    /**
     * Constructeur séquences de mouvement sur la sprite d'une image
     * @param spriteMoveUpRange    Coordonnées de la séquence de mouvement vers le haut
     * @param spriteMoveRightRange Coordonnées de la séquence de mouvement vers la droite
     * @param spriteMoveLeftRange  Coordonnées de la séquence de mouvement vers la gauche
     * @param spriteMoveDownRange Coordonnées de la séquence de mouvement vers le bas
     */
    public MoveRangeOnSprite(Coord spriteMoveUpRange, Coord spriteMoveRightRange, Coord spriteMoveLeftRange, Coord spriteMoveDownRange) {
        this.spriteMoveUpRange = spriteMoveUpRange;
        this.spriteMoveRightRange = spriteMoveRightRange;
        this.spriteMoveLeftRange = spriteMoveLeftRange;
        this.spriteMoveDownRange = spriteMoveDownRange;
    }

    /**
     *
     * @return Coordonnées de la séquence de mouvement vers le haut
     */
    public Coord getSpriteMoveUpRange() {
        return spriteMoveUpRange;
    }

    public void setSpriteMoveUpRange(Coord spriteMoveUpRange) {
        this.spriteMoveUpRange = spriteMoveUpRange;
    }

    /**
     *
     * @return Coordonnées de la séquence de mouvement vers la  droite
     */
    public Coord getSpriteMoveRightRange() {
        return spriteMoveRightRange;
    }

    public void setSpriteMoveRightRange(Coord spriteMoveRightRange) {
        this.spriteMoveRightRange = spriteMoveRightRange;
    }

    /**
     *
     * @return Coordonnées de la séquence de mouvement vers la gauche
     */
    public Coord getSpriteMoveLeftRange() {
        return spriteMoveLeftRange;
    }

    public void setSpriteMoveLeftRange(Coord spriteMoveLeftRange) {
        this.spriteMoveLeftRange = spriteMoveLeftRange;
    }

    /**
     *
     * @return Coordonnées de la séquence de mouvement vers le bas
     */
    public Coord getSpriteMoveDownRange() {
        return spriteMoveDownRange;
    }

    public void setSpriteMoveDownRange(Coord spriteMoveDownRange) {
        this.spriteMoveDownRange = spriteMoveDownRange;
    }
}