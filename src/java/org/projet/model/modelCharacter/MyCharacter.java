package src.java.org.projet.model.modelCharacter;


import src.java.org.projet.services.SpriteService;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveRangeOnSprite;
import src.java.org.projet.interfaces.Views4OrientationImgCharacter;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public abstract class MyCharacter extends Views4OrientationImgCharacter implements Movable {


    private  String name;
    private int HP;
    Coord coord;
    String defaultImgPath = "src/java/org/projet/assets/character/hero/d.png";

    public MyCharacter(){
        super();
    }

    public MyCharacter(String name, int hp, MoveRangeOnSprite moveRangeOnSprite, SpriteService spriteService, String defaultImgPath) {
        super(spriteService,moveRangeOnSprite);
        this.name = name;
        this.HP = hp;
        this.defaultImgPath = defaultImgPath;
    }

    public String getDefaultImgPath() {
        return defaultImgPath;
    }

    public void setDefaultImgPath(String defaultImgPath) {
        this.defaultImgPath = defaultImgPath;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }



    public void decreaseHP(int nbp){
        this.HP -= nbp;
    }

    public void increaseHP(int nbp){
        this.HP += nbp;
    }

    public int getHP() {
        return HP;
    }

    public String getName() {
        return name;
    }


    public abstract void mission(Hero hero);
    public abstract void attack(MyCharacter hero);

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