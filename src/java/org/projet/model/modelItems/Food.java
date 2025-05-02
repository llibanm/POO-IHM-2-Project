package src.java.org.projet.model.modelItems;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import src.java.org.projet.model.Dataset;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;



/**
 * Item pour charger les pdv et les projectiles du héro
 */
public class Food extends Item {
    Dataset dataset = Dataset.getInstance();
    @JsonCreator
    public Food(@JsonProperty("name") String name){
        super(name,"-It is food that you can use to increase your health points");

    }

    public void taken(){
        //System.out.println("FOOD TAKEN");
    }

    /**
     *Charger les pdv et les projectiles du héro
     * @param hero
     */
    public void fullHpAndLaser(Hero hero){
        hero.setHP(hero.getHP()+ dataset.getMesure("DEFAULT_INCREASEMENT_LASER"));
        hero.getH_bow().add_arrows(dataset.getMesure("DEFAULT_INCREASEMENT_ARROW"));
    }

    @Override
    public void use(GameModel model) {
        fullHpAndLaser(model.getCurrentLevel().getHero());
    }
}