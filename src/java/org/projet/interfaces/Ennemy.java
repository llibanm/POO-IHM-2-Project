package src.java.org.projet.interfaces;

import src.java.org.projet.model.modelLevelEditor.base.Coord;

public interface Ennemy {

    public Coord getPosition();
    public void setPosition(Coord position);
    public int getSpeed();
    public void attack();

}
