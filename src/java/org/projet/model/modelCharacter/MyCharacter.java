package src.java.org.projet.model.modelCharacter;


import src.java.org.projet.services.SpriteService;
import src.java.org.projet.interfaces.Movable;
import src.java.org.projet.interfaces.MoveRangeOnSprite;
import src.java.org.projet.interfaces.Views4OrientationImgCharacter;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public abstract class MyCharacter extends Views4OrientationImgCharacter implements Movable {


    private final String name;
    private int HP;
    Coord coord;


    public MyCharacter(String name, int hp, MoveRangeOnSprite moveRangeOnSprite, SpriteService spriteService) {
        super(spriteService,moveRangeOnSprite);
        this.name = name;
        this.HP = hp;
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

//    public abstract void printCharacter();
    public abstract void BeAttacked(String weapon, String arg, Hero hero);

    public int getHP() {
        return HP;
    }

    public String getName() {
        return name;
    }

    public abstract void display_help();

    public abstract void mission(Hero hero);
    public abstract void attack(MyCharacter hero);

    @Override
    public String toString() {
        return "MyCharacter{" +
                "name='" + name + '\'' +
                ", HP=" + HP +
                ", coord=" + coord +
                '}';
    }
}