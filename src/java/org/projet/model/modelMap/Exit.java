package src.java.org.projet.model.modelMap;


import src.java.org.projet.model.modelCharacter.Hero;

/**
 * Classe representant une sortie d'un lieu vers un autre
 */
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

	public Exit(){

	}

	/**
	 * @return obtenir la location initiale
	 */
	public Location getEntrance() {
		return entrance;
	}

	/**
	 * @return Obtenir la destination
	 */
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
//	public Location getneighbor() {
//		return wayOut;
//	}

	public abstract boolean hero_unlock(Hero hero);
}