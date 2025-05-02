package src.java.org.projet.model.modelItems;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelGame.GameModel;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public class Bow extends Item implements Movable {
    Dataset dataset = Dataset.getInstance();
    private int Arrows = 10;
    Coord moveDirection;
    String bowUrlImage= "src/java/org/projet/assets/bule.png";


    public Bow(Coord coord) {
        super("Bow", "It is the bow of your hero, you can use it with the normal arrows or the burning ones " +
                "to fight terrestrial and aerial ennemies.\n", coord);

    }

    /**
     *
     * @return path de l'image du projectile
     */
    public String getBowUrlImage() {
        return bowUrlImage;
    }

    /**
     * Fixer path vers l'image du projectile
     * @param bowUrlImage
     */
    public void setBowUrlImage(String bowUrlImage) {
        this.bowUrlImage = bowUrlImage;
    }


    /**
     * Ajouter des projectiles
     * @param a nombre de projectiles
     */
    public void add_arrows(int a) {
        Arrows += a;
    }

    /**
     * Retirer des projectiles
     */
    public void remove_arrows() {
        Arrows -= 1;
    }

    /**
     * @return Obtenir le nombre de projectiles
     */

    public int getNbArrows() {
        return Arrows;
    }


    /**
     * Image actuelle du projectile
     * @param rowX déplacement en Row
     * @param rowY déplacement en Col
     * @return
     */
    @Override
    public ImageView nextImage(int rowX, int rowY) {
        Image image = new Image(getBowUrlImage());
        var imgView = new ImageView(image);
        imgView.setFitHeight(10);
        imgView.setFitWidth(10);
        return  imgView ;

    }

    /**
     * Vitesse du projectile
     * @return
     */
    @Override
    public int getSpeed() {
        return dataset.getMesure("DEFAULT_ARROW_SPEED");
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

    @Override
    public void use(GameModel model) {

    }
}