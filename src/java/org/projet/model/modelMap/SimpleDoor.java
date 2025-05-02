package src.java.org.projet.model.modelMap;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import src.java.org.projet.interfaces.EffectOnHero;
import src.java.org.projet.model.modelCharacter.Hero;
import src.java.org.projet.model.modelGame.GameModel;

/**
 * Porte
 */
public class SimpleDoor extends Exit implements EffectOnHero {

	//public SimpleDoor() {}
	@JsonCreator
	public SimpleDoor(@JsonProperty("entrance") Location entrance,@JsonProperty("wayOut") Location wayOut, @JsonProperty("name")String name) {

		super(entrance, wayOut, name);
	}

	@Override
	public boolean hero_unlock(Hero hero) { return true; }

	@Override
	public Boolean canBeCrossed() {
		return true; //Is always open
	}
	
	@Override
	public void isCrossing() {
		//TODO make controller interact with model for this
		//System.out.println("You are crossing a simple door");
	}

	@Override
	public Location cross() {
		return getWayOut();
	}

	@Override
	public void use(GameModel model) {
		model.getCurrentLevel().changeLocation(this.cross());
	}
}