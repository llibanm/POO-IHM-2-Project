
package src.java.org.projet.model.modelCharacter;

import src.java.org.projet.model.Dataset;
import src.java.org.projet.services.SpriteService;
import src.java.org.projet.interfaces.Ennemy;
import src.java.org.projet.interfaces.MoveRangeOnSprite;
import src.java.org.projet.interfaces.MyLogger;
import src.java.org.projet.model.modelLevelEditor.base.Coord;

/**
 * Classe representant l' ennemi secondaire
 */
public class Agressor extends MyCharacter implements Ennemy {

    private final MyLogger logger = new MyLogger(Agressor.class);
    Dataset dataset = Dataset.getInstance();


    /**
     * Constructeur
     * @param name nom
     * @param hp points de vie
     */
    public Agressor(String name, int hp) {
        super(name, hp,
                new MoveRangeOnSprite(new Coord(0,-1), new Coord(3,-1),new Coord(1,-1),new Coord(2,-1)),
                new SpriteService("src/java/org/projet/assets/ennemy1.png",64,64,9,4),
                "src/java/org/projet/model/modelCharacter/Agressor.java:19"
        );

    }

    /**
     * Attaque le héros
     * @param hero
     */
    public void attack(MyCharacter hero){
        if (this.getHP() > 0) {
            hero.decreaseHP(dataset.getMesure("DEFAULT_AGRESSOR_REMOVE_HP_HERO"));
            //System.out.println("You've been attacked by an agressor" );
        }
        else {//System.out.println("agressor's out")
        }
    }


    /**
     * Vitesse de déplacement de l'agresseur
     * @return
     */
    @Override
    public int getSpeed() {
        return dataset.getMesure("DEFAULT_AGRESSOR_SPEED");
    }

    /**
     * Obtenir l'orientation de l'ennemi
     * @return
     */
    @Override
    public Coord getMoveDirection() {
        return directionToCoord(getLastDirection());
    }

    @Override
    public void setMoveDirection(int deltaRow, int deltaCol) {
       // setLastDirection(new Coord(deltaRow, deltaCol));

    }

    /**
     * Proximité avec le le héros
     * @param c Coordonnées du héro
     * @return
     */
    @Override
    public  boolean isNearOfHero(Coord c){
        int row = c.getRow();
        int col = c.getCol();
        boolean isNear = Math.sqrt(Math.pow(this.getCoord().getRow() - row, 2) + Math.pow(this.getCoord().getCol() - col, 2)) <= getPorteeAtk();
        logger.info("isNearOfHero : " + isNear);
        if(isNear) return true;
        return false;
    }

    /**
     * Détecte si l'agresseur est entrain d'attaquer le héros ou de se déplacer
     * @param hero hero
     * @return
     */
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


    /**
     * Portée d'attaque de l'ennemi
     * @return
     */
    @Override
    public int getPorteeAtk(){
        return dataset.getMesure("DEFAULT_AGRESSOR_PORTEE_ATK");
    }
}