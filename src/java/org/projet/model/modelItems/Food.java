package src.java.org.projet.model.modelItems;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;

public class Food extends Item {
    @JsonCreator
    public Food(@JsonProperty("name") String name){
        super(name,"-It is food that you can use to increase your health points");

    }

    public void taken(){
        //System.out.println("FOOD TAKEN");
    }

    public void fullHpAndLaser(Hero hero){
        hero.setHP(hero.getHP()+2);
        hero.getH_bow().add_arrows(5);
    }

    @Override
    public void use(GameModel model) {
        fullHpAndLaser(model.getCurrentLevel().getHero());
    }
}