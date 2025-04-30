
package src.java.org.projet.model.modelCharacter;

import src.java.org.projet.services.SpriteService;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.MoveRangeOnSprite;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

public class Agressor extends MyCharacter implements Ennemy {

    private final MyLogger logger = new MyLogger(Agressor.class);


    public Agressor(String name, int hp) {
        super(name, hp,
                new MoveRangeOnSprite(new Coord(0,-1), new Coord(3,-1),new Coord(1,-1),new Coord(2,-1)),
                new SpriteService("src/java/org/projet/assets/ennemy1.png",64,64,9,4),
                "src/java/org/projet/model/modelCharacter/Agressor.java:19"
        );

    }

    /*@Override
    public void describe() {
        System.out.println("He is your enemy, he decreases your health points by attacking you.\nNB HP : " + getHP());
    }

    public void printCharacter(){
        System.out.println(getName());
    }*/

    public void attaqueAgressorWithAr(Hero hero) {
        if (hero.CanAttaqueWithAr()){
            decreaseHP(3);
            hero.loseArrow();
        }
        else {//System.out.println("No ARROWS for the bow")
        ;}
    }
    public void attaqueAgressorWithBAr(Hero hero) {
        if (hero.CanAttaqueWithBAr()){
            decreaseHP(6);
            hero.loseBurningArrows();
        }
        else{//System.out.println("No BURNING ARROWS for the bow")
        ;}
    }

    @Override
    public void BeAttacked(String weapon, String arg, Hero hero) {
        if ((this.getHP() > 0) && weapon.equals("BOW")) {
            if (arg.equals("ARROW")) {
                attaqueAgressorWithAr(hero);
            }
            if (arg.equals("BURNINGARROW")) {
                attaqueAgressorWithBAr(hero);
            }
        }else{
            if ((weapon.equals("SABRE")) && (this.getHP() > 0)){
                this.decreaseHP(3);
            }
            else {
                //System.out.println("the agressor has no more health points.");
            }
        }
    }


    public void display_help(){}

    public void attack(MyCharacter hero){
        if (this.getHP() > 0) {
            hero.decreaseHP(2);
            //System.out.println("You've been attacked by an agressor" );
        }
        else {//System.out.println("agressor's out")
        ;}
    }

    public void mission(Hero hero){}


    @Override
    public int getSpeed() {
        return 1;
    }

    @Override
    public Coord getMoveDirection() {
        return directionToCoord(getLastDirection());
    }

    @Override
    public void setMoveDirection(int deltaRow, int deltaCol) {
       // setLastDirection(new Coord(deltaRow, deltaCol));

    }

    @Override
    public  boolean isNearOfHero(Coord c){
        int row = c.getRow();
        int col = c.getCol();
        boolean isNear = Math.sqrt(Math.pow(this.getCoord().getRow() - row, 2) + Math.pow(this.getCoord().getCol() - col, 2)) <= getPorteeAtk();
        logger.info("isNearOfHero : " + isNear);
        if(isNear) return true;
        return false;
    }
    @Override
    public boolean attackHero(Hero hero) {
        boolean sameLine = (getCoord().getRow() == hero.getCoord().getRow()) || (getCoord().getCol() == hero.getCoord().getCol());

        if(isNearOfHero(hero.getCoord()) && sameLine && isFacingHero(hero)) {
            logger.info("Agressor is attacking hero");
                        attack(hero);
                        logger.info("new hero HP : " + hero.getHP());
                return true;
            }
        logger.info("Agressor is moving");
        return false;
    }

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
    public int getPorteeAtk(){
        return 6;
    }
}