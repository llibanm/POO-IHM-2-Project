
package src.java.org.projet.model.modelCharacter;

import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.MoveRangeOnSprite;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelLevelEditor.base.Coord;
import src.java.org.projet.services.SpriteService;

/**
 * Boss du jeu
 */
public class Boss extends MyCharacter implements Ennemy {

    private final MyLogger logger = new MyLogger(Boss.class);
    Dataset dataset = Dataset.getInstance();


    public Boss(String name, int hp) {
        super(name, hp,
                new MoveRangeOnSprite(new Coord(0,-1), new Coord(2,-1),new Coord(1,-1),new Coord(2,-1)),
                new SpriteService("src/java/org/projet/assets/boss.png",57,88,8,3),
                "src/java/org/projet/model/modelCharacter/boss.png"
        );

    }



    public void attack(MyCharacter hero){
        if (getHP() > 0) {
            hero.decreaseHP(dataset.getMesure("DEFAULT_BOSS_REMOVE_HP_HERO"));
        }
        else {
        }
    }


    @Override
    public int getSpeed() {
        return dataset.getMesure("DEFAULT_BOSS_SPEED");
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
    public boolean attackHero(Hero hero) {
        boolean sameLine = (getCoord().getRow() == hero.getCoord().getRow()) || (getCoord().getCol() == hero.getCoord().getCol());

        if(isNearOfHero(hero.getCoord()) && sameLine && isFacingHero(hero)) {
            logger.info("Agressor is attacking hero");
            // attack(hero);
            logger.info("new hero HP : " + hero.getHP());
                return true;
            }
        logger.info("Agressor is moving");
        return false;
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
    public int getPorteeAtk(){
        return dataset.getMesure("DEFAULT_BOSS_PORTEE_ATK");
    }
}