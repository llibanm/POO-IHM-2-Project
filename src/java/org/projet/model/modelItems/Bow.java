package src.java.org.projet.model.modelItems;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public class Bow extends Item implements Movable {
    private int Arrows = 10;
    private int BurningArrows = 2;
    Coord moveDirection;

    public Bow(Coord coord) {
        super("Bow", "It is the bow of your hero, you can use it with the normal arrows or the burning ones " +
                "to fight terrestrial and aerial ennemies.\n", coord);
       /* setDescription("It is the bow of your hero, you can use it with the normal arrows or the burning ones " +
                "to fight terrestrial and aerial ennemies.\n" + "NB arrows : " + Arrows + "\nNB burning arrows : "
                        + BurningArrows);*/
        //TODO find a way to bring the Arrow / burning arrow in a another way
    }

    public void add_arrows(int a) {
        Arrows += a;
    }

    public void remove_arrows() {
        Arrows -= 1;
    }

    public void add_burning_arrows(int b) {
        BurningArrows += b;
    }

    public void remove_burning_arrows() {
        BurningArrows -= 1;
    }

    public int getNbArrows() {
        return Arrows;
    }

    public int getNbBurningArrows() {
        return BurningArrows;
    }

    //TODO make controller interact with model for this
    /*public void describe() {
        System.out.println("It is the bow of your hero, you can use it with the normal arrows or the burning ones " +
                            "to fight terrestrial and aerial ennemies.\n" + "NB arrows : " + Arrows + "\nNB burning arrows : "
                            + BurningArrows);

    }*/

    @Override
    public ImageView nextImage(int rowX, int rowY) {
        Image image = new Image("src/java/org/projet/assets/bule.png");
        var imgView = new ImageView(image);
        imgView.setFitHeight(10);
        imgView.setFitWidth(10);
        return  imgView ;

    }

    @Override
    public int getSpeed() {
        return 0;
    }


    @Override
    public Coord getCoord() {
        return  getCoords();
    }

    @Override
    public void setCoord(Coord position) {
        setCoords(position);
    }

    @Override
    public Coord getMoveDirection() {
        return moveDirection;
    }

    @Override
    public void setMoveDirection(int deltaRow, int deltaCol) {
        if (deltaRow >= -1 && deltaRow <= 1 && deltaCol >= -1 && deltaCol <= 1) {
            moveDirection = new Coord(deltaRow, deltaCol);
        } else {
            throw new IllegalArgumentException("Les valeurs de deltaRow et deltaCol doivent être dans l'intervalle [-1, 1].");
        }
    }

    public void taken() {}
}