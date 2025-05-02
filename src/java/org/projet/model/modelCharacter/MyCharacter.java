package src.java.org.projet.model.modelCharacter;


import src.java.org.projet.services.SpriteService;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveRangeOnSprite;
import src.java.org.projet.interfaces.Views4OrientationImgCharacter;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

/**
 *Classe implémentant les caractéristiques propres aux personnages du jeu
 */
public abstract class MyCharacter extends Views4OrientationImgCharacter implements Movable {


    private  String name;
    private int HP;
    Coord coord;
    String defaultImgPath = "src/java/org/projet/assets/character/hero/d.png";



    public MyCharacter(String name, int hp, MoveRangeOnSprite moveRangeOnSprite, SpriteService spriteService, String defaultImgPath) {
        super(spriteService,moveRangeOnSprite);
        this.name = name;
        this.HP = hp;
        this.defaultImgPath = defaultImgPath;
    }

    public MyCharacter(){
        super();
    }

    /**
     * @return url vers la photo initiale du personnage
     */
    public String getDefaultImgPath() {
        return defaultImgPath;
    }

    /**
     * Fixer url vers la photo initiale du personnage
     * @param defaultImgPath chemin vers l'image
     */
    public void setDefaultImgPath(String defaultImgPath) {
        this.defaultImgPath = defaultImgPath;
    }

    /**
     * @return Coordonnées du personnage sur la map
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Fixer coordonnées du personnage sur la map
     * @param coord nouvelle coordonnées
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    /**
     * Fixer les points de vie du héro
     * @param HP nouveau pdv
     */
    public void setHP(int HP) {
        this.HP = HP;
    }


    /**
     * Réduire les points de vie du héro
     * @param nbp réduction
     */
    public void decreaseHP(int nbp){
        this.HP -= nbp;
    }

    public void increaseHP(int nbp){
        this.HP += nbp;
    }

    public int getHP() {
        return HP;
    }

    /**
     * @return nome du héro
     */
    public String getName() {
        return name;
    }


    public abstract void attack(MyCharacter hero);

    /**
     * Détecte si le personnage est face au héro
     * @param hero
     * @return
     */
    public boolean isFacingHero(Hero hero) {
        Coord enemyPos =getCoord();
        Coord heroPos = hero.getCoord();
        Coord direction = getMoveDirection();

        // Vérifie si l'ennemi regarde dans la direction du héros
        if (direction.getRow() > 0 && heroPos.getRow() > enemyPos.getRow()) return true;
        if (direction.getRow() < 0 && heroPos.getRow() < enemyPos.getRow()) return true;
        if (direction.getCol() > 0 && heroPos.getCol() > enemyPos.getCol()) return true;
        if (direction.getCol() < 0 && heroPos.getCol() < enemyPos.getCol()) return true;

        return false;
    }
    @Override
    public String toString() {
        return "MyCharacter{" +
                "name='" + name + '\'' +
                ", HP=" + HP +
                ", coord=" + coord +
                '}';
    }
}