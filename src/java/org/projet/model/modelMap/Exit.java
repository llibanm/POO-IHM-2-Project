package src.java.org.projet.model.modelMap;


import src.java.org.projet.model.modelCharacter.Hero;

public abstract class Exit {
	private String name;
	protected Boolean isOpened;
	private Location entrance;
	private Location wayOut;
	
	public Exit(Location entrance, Location wayOut, String name) {
		this.entrance = entrance;
		this.wayOut = wayOut;
		this.name = name;
	}
	
	public Location getEntrance() {
		return entrance;
	}
	
	public Location getWayOut() {
		return wayOut;
	}
	
	public void close() {
		isOpened = false;
	}

	public String getName(){
		return name;
	}
	
	public abstract Boolean canBeCrossed();
	
	public abstract void isCrossing();
	public abstract Location cross();
	public Location getneighbor() {
		return wayOut;
	}

	public abstract boolean hero_unlock(Hero hero);
}