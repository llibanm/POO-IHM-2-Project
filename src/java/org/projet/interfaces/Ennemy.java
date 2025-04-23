package src.java.org.projet.interfaces;

import src.java.org.projet.model.modelLevelEditor.base.Coord;

public interface Ennemy {
    Coord coord = new Coord(0,0);
    public default void setCoord(Coord coord){
        coord = coord;
    }

}
