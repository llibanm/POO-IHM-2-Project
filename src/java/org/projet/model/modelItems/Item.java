package src.java.org.projet.model.modelItems;


import src.java.org.projet.interfaces.EffectOnHero;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

/**
 * Un item quelconque du jeu
 */
public abstract class Item implements EffectOnHero {
    private final String name;
    private String description;
    private Coord coord;

    public Item(String name, String d){
        this.name = name;
        this.description = d;
    }

    /**
     * Constructeur
     * @param name nom items
     * @param d description
     * @param c Coordonnées sur la map
     */
    public Item(String name, String d, Coord c){
        this.name = name;
        this.description = d;
        this.coord = c;
    }


    public void setDescription(String d){
        description = d;
    }

    public void printItem(){
        System.out.println(this.name);
    }

    /**
     * @return nom de l'item
     */
    public String getName(){
        return name;
    }

    public abstract void taken();

    /**
     * @return Coordonnées de l'item sur la map
     */
    public Coord getCoords() {
        return coord;
    }

    public void setCoords(Coord coord) {
        this.coord = coord;
    }
}