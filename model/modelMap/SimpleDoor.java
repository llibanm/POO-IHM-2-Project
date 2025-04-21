package model.modelMap;


import model.modelCharacter.Hero;

public class SimpleDoor extends Exit {
	
	public SimpleDoor(Location entrance, Location wayOut, String name) {

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
}
